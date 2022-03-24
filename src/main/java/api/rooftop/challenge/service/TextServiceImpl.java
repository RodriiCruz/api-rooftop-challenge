package api.rooftop.challenge.service;

import api.rooftop.challenge.dto.RequestDTO;
import api.rooftop.challenge.dto.ResponseDTO;
import api.rooftop.challenge.dto.TextDTO;
import api.rooftop.challenge.entity.Text;
import api.rooftop.challenge.exceptions.NotFoundException;
import api.rooftop.challenge.repository.ITextRepository;
import api.rooftop.challenge.repository.specifications.TextSpecification;
import api.rooftop.challenge.util.Mapper;
import api.rooftop.challenge.util.Validator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Service
public class TextServiceImpl implements ITextService {
    
    private ITextRepository repository;
    private Mapper mapper;
    private TextSpecification specifications;
    private Validator validator;
    
    @Autowired
    public TextServiceImpl(ITextRepository repository, Mapper mapper, TextSpecification specifications, Validator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.specifications = specifications;
        this.validator = validator;
    }
    
    @Override
    @Transactional
    public ResponseDTO save(RequestDTO requestDTO) throws Exception {
        
        requestDTO.setChars(validator.verifyCharsOfDTO(requestDTO.getChars()));

        Text entitySaved = null;
        ResponseDTO response = null;
        
        if (repository.findByHash(requestDTO.getSHA256()).isEmpty()) {
            Text entity = mapper.requestToTextEntity(requestDTO);
            entity.setResult(mapper.mapToString(this.getResultAsMap(requestDTO)));
            
            entitySaved = repository.save(entity);
        } else {
            entitySaved = repository.findByHash(requestDTO.getSHA256()).get();
        }
        
        response = mapper.textToResponse(entitySaved);
        return response;
    }
    
    @Override
    @Transactional(readOnly = true)
    public TextDTO getById(Long id) {
        TextDTO response = null;
        Optional<Text> result = repository.findById(id);
        
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        
        response = mapper.textToTextDTO(result.get());
        return response;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Stream<TextDTO> getByFilters(Integer chars, Integer pageN, Integer rpp) {
        
        Pageable pageRequest = PageRequest.of(validator.verifyPage(pageN), validator.verifyRpp(rpp));
        Page<Text> page = repository.findAll(specifications.getByFilters(validator.verifyChars(chars)), pageRequest);
        
        return mapper.pageTextToPageDto(page).get();
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        
        Optional<Text> result = repository.findById(id);
        
        if (result.isEmpty() || result.get().isDeleted() == true) {
            throw new NotFoundException();
        }
        
        repository.deleteById(id);
    }
    
    private Map<String, Integer> getResultAsMap(RequestDTO request) {
        
        String key = "";
        int beginIndex = 0;
        int endIndex = validator.verifyEndIndex(Integer.valueOf(request.getChars()), request.getText());
        
        Map<String, Integer> result = new LinkedHashMap();
        
        while (endIndex <= request.getText().length()) {
            key = request.getText().toLowerCase().substring(beginIndex, endIndex);
            
            if (result.containsKey(key)) {
                int value = result.get(key);
                value++;
                result.replace(key, value);
            } else {
                result.put(key, 1);
            }
            
            beginIndex++;
            endIndex++;
        }
        
        return result;
    }
}

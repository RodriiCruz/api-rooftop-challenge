package api.rooftop.challenge.service;

import api.rooftop.challenge.dto.RequestDTO;
import api.rooftop.challenge.dto.ResponseDTO;
import api.rooftop.challenge.dto.TextDTO;
import api.rooftop.challenge.entity.Text;
import api.rooftop.challenge.repository.ITextRepository;
import api.rooftop.challenge.util.Mapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Service
public class TextServiceImpl implements ITextService {

    @Autowired
    private ITextRepository repository;
    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public ResponseDTO save(RequestDTO requestDTO) throws Exception {
        requestDTO.setChars(this.verifyChars(requestDTO.getChars()));

        Text entitySaved = null;
        ResponseDTO response = null;

        if (repository.findByHash(requestDTO.getSHA256()).isEmpty()) {
            Text entity = mapper.requestToText(requestDTO);
            entity.setResult(this.getResult(requestDTO));

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

        if (result.isPresent()) {
            response = mapper.textToTextDTO(result.get());
        }

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TextDTO> getAll() {

        List<TextDTO> response = mapper.textToListTextDTO(repository.findAll());
        return response;
    }

    private Map<String, Integer> getResult(RequestDTO request) {

        String key = "";
        int beginIndex = 0;
        int endIndex = this.verifyEndIndex(request.getChars(), request.getText());

        Map<String, Integer> result = new LinkedHashMap();

        while (endIndex <= request.getText().length()) {
            key = request.getText().substring(beginIndex, endIndex);

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

    private Integer verifyChars(Integer chars) {
        if (chars < 2) {
            chars = 2;
        }
        return chars;
    }

    private Integer verifyEndIndex(Integer index, String text) {
        if (index > text.length()) {
            index = text.length();
        }
        return index;
    }

}

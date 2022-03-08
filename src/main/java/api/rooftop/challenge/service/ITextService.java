package api.rooftop.challenge.service;

import api.rooftop.challenge.dto.RequestDTO;
import api.rooftop.challenge.dto.ResponseDTO;
import api.rooftop.challenge.dto.TextDTO;
import java.util.List;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
public interface ITextService {

    ResponseDTO save(RequestDTO requestDTO) throws Exception;

    TextDTO getById(Long id);
    
    List<TextDTO> getAll();
}

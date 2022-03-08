package api.rooftop.challenge.service;

import api.rooftop.challenge.dto.RequestDTO;
import api.rooftop.challenge.dto.ResponseDTO;
import api.rooftop.challenge.dto.TextDTO;
import api.rooftop.challenge.entity.Text;
import org.springframework.data.domain.Page;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
public interface ITextService {

    ResponseDTO save(RequestDTO requestDTO) throws Exception;

    TextDTO getById(Long id);

    Page<Text> getByFilters(Integer chars, Integer page, Integer rpp);
}

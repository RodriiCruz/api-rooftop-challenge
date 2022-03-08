package api.rooftop.challenge.util;

import api.rooftop.challenge.dto.RequestDTO;
import api.rooftop.challenge.dto.ResponseDTO;
import api.rooftop.challenge.dto.TextDTO;
import api.rooftop.challenge.entity.Text;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Component
public class Mapper {

    public Text requestToText(RequestDTO dto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (dto.getChars() < 2) {
            dto.setChars(2);
        }

        return Text.builder()
                .hash(dto.getSHA256())
                .chars(dto.getChars())
                .build();
    }

    public ResponseDTO textToResponse(Text entity) {
        return ResponseDTO.builder()
                .id(entity.getId())
                .url("/text/" + entity.getId())
                .build();
    }

    public TextDTO textToTextDTO(Text entity) {
        return TextDTO.builder()
                .id(entity.getId())
                .hash(entity.getHash())
                .chars(entity.getChars())
                .result(entity.getResult())
                .build();
    }

    public List<TextDTO> textToListTextDTO(List<Text> entities) {
        List<TextDTO> response = new ArrayList();

        for (Text entity : entities) {
            response.add(this.textToTextDTO(entity));
        }

        return response;
    }
}

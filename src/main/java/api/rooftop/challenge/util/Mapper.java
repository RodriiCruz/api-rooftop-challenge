package api.rooftop.challenge.util;

import api.rooftop.challenge.dto.RequestDTO;
import api.rooftop.challenge.dto.ResponseDTO;
import api.rooftop.challenge.dto.TextDTO;
import api.rooftop.challenge.entity.Text;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Component
public class Mapper {

    public Text requestToTextEntity(RequestDTO dto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Text.builder()
                .hash(dto.getSHA256())
                .chars((Integer) dto.getChars())
                .deleted(false)
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
                .result(this.stringToMap(entity.getResult()))
                .build();
    }

    public List<TextDTO> textToListTextDTO(List<Text> entities) {
        List<TextDTO> response = new ArrayList();

        for (Text entity : entities) {
            response.add(this.textToTextDTO(entity));
        }

        return response;
    }

    public Page<TextDTO> pageTextToPageDto(Page<Text> page) {
        Page<TextDTO> pageInDTO = new PageImpl(
                this.textToListTextDTO(page.getContent()),
                PageRequest.of(page.getNumber(), page.getSize()),
                page.getTotalElements());

        return pageInDTO;
    }

    public String mapToString(Map<String, Integer> map) {
        StringBuilder mapAsString = new StringBuilder();
        for (String key : map.keySet()) {
            mapAsString.append(key + "=" + map.get(key) + ",");
        }
        mapAsString.delete(mapAsString.length() - 1, mapAsString.length());

        return mapAsString.toString();
    }

    private Map<String, Integer> stringToMap(String string) {

        String[] keyValuePairs = string.split(",");
        Map<String, Integer> map = new LinkedHashMap<>();

        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            map.put(entry[0], Integer.valueOf(entry[1]));
        }

        return map;
    }
}

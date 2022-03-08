package api.rooftop.challenge.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Data
@Builder
public class TextDTO {

    private Long id;
    private String hash;
    private Integer chars;
    private Map<String, Integer> result;

}

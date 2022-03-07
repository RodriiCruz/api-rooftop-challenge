package api.rooftop.challenge.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Data
@Builder
public class ResponseDTO {

    private Long id;
    private String url;
}

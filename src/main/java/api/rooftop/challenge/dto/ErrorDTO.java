package api.rooftop.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {

    private boolean error;
    private String message;
    private Integer code;
}

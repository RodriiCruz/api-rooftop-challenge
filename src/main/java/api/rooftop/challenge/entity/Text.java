package api.rooftop.challenge.entity;

import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Entity
@Data
@Table(name = "texts")
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hash;
    private Integer chars;
    private Map<String, Integer> result;

}

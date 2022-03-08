package api.rooftop.challenge.repository.specifications;

import api.rooftop.challenge.dto.TextFiltersDTO;
import api.rooftop.challenge.entity.Text;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Component
public class TextSpecification {

    public Specification<Text> getByFilters(TextFiltersDTO filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getChars() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("chars"), filters.getChars())
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

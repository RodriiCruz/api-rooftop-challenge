package api.rooftop.challenge.repository;

import api.rooftop.challenge.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Repository
public interface ITextRepository extends JpaRepository<Text, Long> {

}

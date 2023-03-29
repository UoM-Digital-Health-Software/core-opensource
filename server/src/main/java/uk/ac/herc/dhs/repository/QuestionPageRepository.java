package uk.ac.herc.dhs.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import uk.ac.herc.dhs.domain.QuestionPage;

/**
 * Spring Data SQL repository for the QuestionPage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionPageRepository extends JpaRepository<QuestionPage, Long> {
    Set<QuestionPage> findByActiveTrue();
}

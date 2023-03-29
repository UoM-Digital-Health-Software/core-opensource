package uk.ac.herc.dhs.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import uk.ac.herc.dhs.domain.Answer;

/**
 * Spring Data SQL repository for the Answer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {}

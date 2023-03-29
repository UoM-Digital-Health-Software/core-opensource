package uk.ac.herc.dhs.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import uk.ac.herc.dhs.domain.AnswerPage;

/**
 * Spring Data SQL repository for the AnswerPage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerPageRepository extends JpaRepository<AnswerPage, Long> {}

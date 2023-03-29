package uk.ac.herc.dhs.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import uk.ac.herc.dhs.domain.QuestionItem;

/**
 * Spring Data SQL repository for the QuestionItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionItemRepository extends JpaRepository<QuestionItem, Long> {}

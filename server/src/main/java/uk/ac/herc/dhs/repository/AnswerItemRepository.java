package uk.ac.herc.dhs.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import uk.ac.herc.dhs.domain.AnswerItem;
import uk.ac.herc.dhs.domain.Identity;

/**
 * Spring Data SQL repository for the AnswerItem entity.
 */
@Repository
public interface AnswerItemRepository extends JpaRepository<AnswerItem, Long> {
    Set<AnswerItem> findByAnswerAnswerPageIdentity(Identity identity);
}

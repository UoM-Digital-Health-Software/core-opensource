package uk.ac.herc.dhs.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.herc.dhs.domain.Question;

/**
 * Spring Data SQL repository for the Question entity.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(
        value = "select distinct question from Question question left join fetch question.questionItems",
        countQuery = "select count(distinct question) from Question question"
    )
    Page<Question> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct question from Question question left join fetch question.questionItems")
    List<Question> findAllWithEagerRelationships();

    @Query("select question from Question question left join fetch question.questionItems where question.id =:id")
    Optional<Question> findOneWithEagerRelationships(@Param("id") Long id);
}

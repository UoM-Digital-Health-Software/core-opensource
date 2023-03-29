package uk.ac.herc.dhs.service.mapper;

import java.util.Set;

import org.mapstruct.*;
import uk.ac.herc.dhs.domain.*;
import uk.ac.herc.dhs.service.dto.QuestionDTO;
import uk.ac.herc.dhs.service.dto.QuestionPageDTO;

@Mapper(componentModel = "spring", uses = {})
public interface QuestionPageMapper extends EntityMapper<QuestionPageDTO, QuestionPage> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionPageDTO toDtoId(QuestionPage questionPage);

    Set<QuestionPageDTO> toDtos(Set<QuestionPage> questionPages);

    Set<QuestionDTO> questionsToQuestionDTOs(Set<Question> questions);
}

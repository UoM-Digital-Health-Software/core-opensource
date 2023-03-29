package uk.ac.herc.dhs.service.mapper;

import org.mapstruct.*;
import uk.ac.herc.dhs.domain.*;
import uk.ac.herc.dhs.service.dto.AnswerPageDTO;

/**
 * Mapper for the entity {@link AnswerPage} and its DTO {@link AnswerPageDTO}.
 */
@Mapper(componentModel = "spring", uses = { QuestionPageMapper.class, AnswerMapper.class })
public interface AnswerPageMapper extends EntityMapper<AnswerPageDTO, AnswerPage> {
    @Mapping(source = "questionPage.id", target = "questionPageId")
    AnswerPageDTO toDto(AnswerPage answerPage);

    @Mapping(source = "questionPageId", target = "questionPage.id")
    @Mapping(source = "answers", target = "answers")
    @Mapping(target = "removeAnswer", ignore = true)
    AnswerPage toEntity(AnswerPageDTO answerPageDTO);
}

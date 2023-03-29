package uk.ac.herc.dhs.service.mapper;

import org.mapstruct.*;
import uk.ac.herc.dhs.domain.*;
import uk.ac.herc.dhs.service.dto.AnswerDTO;

/**
 * Mapper for the entity {@link Answer} and its DTO {@link AnswerDTO}.
 */
@Mapper(componentModel = "spring", uses = { AnswerPageMapper.class, AnswerItemMapper.class })
public interface AnswerMapper extends EntityMapper<AnswerDTO, Answer> {
    @Mapping(source = "question.id", target = "questionId")
    AnswerDTO toDto(Answer answer);

    @Mapping(source = "questionId", target = "question.id")
    @Mapping(source = "answerItems", target = "answerItems")
    @Mapping(target = "removeAnswerItem", ignore = true)
    Answer toEntity(AnswerDTO answerDTO);
}

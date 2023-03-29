package uk.ac.herc.dhs.service.mapper;

import org.mapstruct.*;
import uk.ac.herc.dhs.domain.*;
import uk.ac.herc.dhs.service.dto.AnswerItemDTO;

/**
 * Mapper for the entity {@link AnswerItem} and its DTO {@link AnswerItemDTO}.
 */
@Mapper(componentModel = "spring", uses = { AnswerMapper.class })
public interface AnswerItemMapper extends EntityMapper<AnswerItemDTO, AnswerItem> {
    @Mapping(source = "questionItem.id", target = "questionItemId")
    AnswerItemDTO toDto(AnswerItem answerItem);

    @Mapping(source = "questionItemId", target = "questionItem.id")
    AnswerItem toEntity(AnswerItemDTO answerItemDTO);
}

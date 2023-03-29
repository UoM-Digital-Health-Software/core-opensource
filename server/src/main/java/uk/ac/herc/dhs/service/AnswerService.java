package uk.ac.herc.dhs.service;

import java.util.Map;

import uk.ac.herc.dhs.domain.AnswerItem;
import uk.ac.herc.dhs.domain.Identity;
import uk.ac.herc.dhs.domain.QuestionItem;
import uk.ac.herc.dhs.service.dto.AnswerPageDTO;

public interface AnswerService {
    AnswerPageDTO savePage(AnswerPageDTO answerPageDTO);
    Map<QuestionItem, AnswerItem> getAnswerItemsMappedByQuestionItem(Identity identity);
}

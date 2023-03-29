package uk.ac.herc.dhs.service;

import uk.ac.herc.dhs.service.dto.QuestionPageDTO;

/**
 * Service Interface for managing {@link uk.ac.herc.dhs.domain.QuestionPage}.
 */
public interface QuestionService {
    QuestionPageDTO getCurrentPage(String responseId);
}

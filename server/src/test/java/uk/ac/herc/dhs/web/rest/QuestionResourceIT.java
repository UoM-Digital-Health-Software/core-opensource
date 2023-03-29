package uk.ac.herc.dhs.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.herc.dhs.IntegrationTest;
import uk.ac.herc.dhs.domain.QuestionPage;
import uk.ac.herc.dhs.service.IdentityService;
import uk.ac.herc.dhs.service.dto.IdentityDTO;
import uk.ac.herc.dhs.repository.QuestionPageRepository;

/**
 * Integration tests for the {@link QuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
class QuestionResourceIT {
    private static final String QUESTION_PAGE_NAME = "Page 1";
    private static final Integer QUESTION_PAGE_ORDER = 1;
    private static final String QUESTION_PAGE_TITLE = "Initial Questions";

    private static final String ENTITY_API_URL = "/api/questions";

    @Autowired
    private QuestionPageRepository questionPageRepository;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private MockMvc restQuestionSetMockMvc;

    @Test
    @Transactional
    void getNextQuestion() throws Exception {

        // Create Question Page
        QuestionPage questionPage = new QuestionPage();
        questionPage.setName(QUESTION_PAGE_NAME);
        questionPage.setOrder(QUESTION_PAGE_ORDER);
        questionPage.setTitle(QUESTION_PAGE_TITLE);
        questionPage.setActive(true);
        questionPage.setShowSkip(false);
        questionPageRepository.save(questionPage);

        // Create Identity & AnswerSet
        IdentityDTO identityDTO = new IdentityDTO();
        identityDTO.setMachineId("xyz321");
        identityDTO.setStudyId("hryws");
        identityDTO.setParticipantId("user20");
        String responseId = identityService.create(identityDTO);

        // Get the questionSet
        restQuestionSetMockMvc
            .perform(
                get(ENTITY_API_URL)
                .param("responseId", responseId)
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name").value(QUESTION_PAGE_NAME))
            .andExpect(jsonPath("$.title").value(QUESTION_PAGE_TITLE))
            .andExpect(jsonPath("$.order").value(QUESTION_PAGE_ORDER));
    }
}

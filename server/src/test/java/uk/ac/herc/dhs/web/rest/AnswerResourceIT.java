package uk.ac.herc.dhs.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.herc.dhs.IntegrationTest;
import uk.ac.herc.dhs.domain.Answer;
import uk.ac.herc.dhs.domain.AnswerItem;
import uk.ac.herc.dhs.domain.AnswerPage;
import uk.ac.herc.dhs.domain.Question;
import uk.ac.herc.dhs.domain.QuestionItem;
import uk.ac.herc.dhs.domain.QuestionPage;
import uk.ac.herc.dhs.domain.enumeration.QuestionType;
import uk.ac.herc.dhs.repository.AnswerItemRepository;
import uk.ac.herc.dhs.repository.AnswerPageRepository;
import uk.ac.herc.dhs.repository.AnswerRepository;
import uk.ac.herc.dhs.repository.QuestionItemRepository;
import uk.ac.herc.dhs.repository.QuestionPageRepository;
import uk.ac.herc.dhs.repository.QuestionRepository;
import uk.ac.herc.dhs.service.IdentityService;
import uk.ac.herc.dhs.service.dto.AnswerDTO;
import uk.ac.herc.dhs.service.dto.AnswerItemDTO;
import uk.ac.herc.dhs.service.dto.AnswerPageDTO;
import uk.ac.herc.dhs.service.dto.IdentityDTO;

/**
 * Integration tests for the {@link AnswerSetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerResourceIT {

    private static final String ENTITY_API_URL = "/api/answers";

    @Autowired
    private MockMvc restAnswerSetMockMvc;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private QuestionPageRepository questionPageRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionItemRepository questionItemRepository;

    @Autowired
    private AnswerPageRepository answerPageRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerItemRepository answerItemRepository;

    @Test
    @Transactional
    void saveAnswerPage() throws Exception {

        AnswerPageDTO answerPageDTO = prepareDatabaseAndAnswerPage();

        // Initial Database
        int initialAnswerPageCount = answerPageRepository.findAll().size();
        int initialAnswerCount = answerRepository.findAll().size();
        int initialAnswerItemCount = answerItemRepository.findAll().size();

        // POST
        restAnswerSetMockMvc.perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(answerPageDTO))).andExpect(status().isCreated());

        // Validate Page
        List<AnswerPage> allAnswerPages = answerPageRepository.findAll();
        assertThat(allAnswerPages).hasSize(initialAnswerPageCount + 1);

        // Validate Answer
        List<Answer> allAnswers = answerRepository.findAll();
        assertThat(allAnswers).hasSize(initialAnswerCount + 1);

        // Validate Answer
        List<AnswerItem> allAnswersItems = answerItemRepository.findAll();
        assertThat(allAnswersItems).hasSize(initialAnswerItemCount + 1);
    }

    private AnswerPageDTO prepareDatabaseAndAnswerPage() {

        // Create Question Page
        QuestionPage questionPage = new QuestionPage();
        questionPage.setName("p1");
        questionPage.setOrder(1);
        questionPage.setTitle("Page 1");
        questionPage.setActive(true);
        questionPage.setShowSkip(false);
        questionPageRepository.save(questionPage);

        // Create a Question
        Question question = new Question();
        question.setQuestionPage(questionPage);
        question.setType(QuestionType.CHECKBOXES);
        question.setName("q1");
        question.setText("Question 1");
        question.setOrder(1);
        questionRepository.save(question);

        // Create a Question Item
        QuestionItem questionItem = new QuestionItem();
        questionItem.addQuestion(question);
        questionItem.setName("i1");
        questionItem.setLabel("Item 1");
        questionItem.setOrder(1);
        questionItem.setMandatory(false);
        questionItemRepository.save(questionItem);

        // Create Identity & AnswerSet
        IdentityDTO identityDTO = new IdentityDTO();
        identityDTO.setMachineId("xyz321");
        identityDTO.setStudyId("hryws");
        identityDTO.setParticipantId("user20");
        String responseId = identityService.create(identityDTO);

        // Prepare AnswerPage
        AnswerPageDTO answerPageDTO = new AnswerPageDTO();
        answerPageDTO.setResponseId(responseId);
        answerPageDTO.setSkipChosen(false);
        answerPageDTO.setQuestionPageId(questionPage.getId());

        // Prepare Answer
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setQuestionId(question.getId());

        // Add Answer to Page
        Set<AnswerDTO> answerDTOs = new HashSet<AnswerDTO>();
        answerDTOs.add(answerDTO);
        answerPageDTO.setAnswers(answerDTOs);

        // Prepare AnswerItem
        AnswerItemDTO answerItemDTO = new AnswerItemDTO();
        answerItemDTO.setQuestionItemId(questionItem.getId());
        answerItemDTO.setSelected(true);

        // Add Item to Answer
        Set<AnswerItemDTO> answerItemDTOs = new HashSet<AnswerItemDTO>();
        answerItemDTOs.add(answerItemDTO);
        answerDTO.setAnswerItems(answerItemDTOs);

        return answerPageDTO;
    }
}

package uk.ac.herc.dhs.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.herc.dhs.service.AnswerService;
import uk.ac.herc.dhs.service.QuestionService;
import uk.ac.herc.dhs.service.dto.AnswerPageDTO;
import uk.ac.herc.dhs.service.dto.QuestionPageDTO;

/**
 * REST controller for managing {@link uk.ac.herc.dhs.domain.AnswerSet}.
 */
@RestController
@RequestMapping("/api")
public class AnswerResource {

    private final Logger log = LoggerFactory.getLogger(AnswerResource.class);

    private final AnswerService answerService;
    private final QuestionService questionService;

    public AnswerResource(
        AnswerService answerService,
        QuestionService questionService
    ) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @PostMapping("/answers")
    public ResponseEntity<QuestionPageDTO> saveAnswerPage(@Valid @RequestBody AnswerPageDTO answerPageDTO) throws URISyntaxException {
        log.debug("REST request to save AnswerPage : {}", answerPageDTO);

        answerService.savePage(answerPageDTO);

        String responseId = answerPageDTO.getResponseId();
        QuestionPageDTO questionPageDTO = questionService.getCurrentPage(responseId);

        return ResponseEntity
            .created(new URI("/api/answers/"))
            .body(questionPageDTO);
    }
}

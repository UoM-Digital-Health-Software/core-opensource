package uk.ac.herc.dhs.web.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import uk.ac.herc.dhs.service.QuestionService;
import uk.ac.herc.dhs.service.dto.QuestionPageDTO;

@RestController
@RequestMapping("/api")
public class QuestionResource {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionService questionService;

    public QuestionResource(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/questions")
    public QuestionPageDTO getNextQuestionPage(@RequestParam String responseId) {
        return questionService.getCurrentPage(responseId);
    }
}

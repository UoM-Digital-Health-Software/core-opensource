package uk.ac.herc.dhs.service.dto;

import java.io.Serializable;
import java.util.Set;

public class AnswerPageDTO implements Serializable {

    private String responseId;

    private Long questionPageId;

    private Set<AnswerDTO> answers;

    private Boolean skipChosen;

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public Long getQuestionPageId() {
        return questionPageId;
    }

    public void setQuestionPageId(Long questionPageId) {
        this.questionPageId = questionPageId;
    }

    public Set<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerDTO> answers) {
        this.answers = answers;
    }

    public Boolean getSkipChosen() {
        return skipChosen;
    }

    public void setSkipChosen(Boolean skipChosen) {
        this.skipChosen = skipChosen;
    }

    @Override
    public String toString() {
        return "AnswerPageDTO [" + "answers=" + answers + ", questionPageId=" + questionPageId + ", responseId="
                + responseId + ", skipChosen=" + skipChosen + "]";
    }
}

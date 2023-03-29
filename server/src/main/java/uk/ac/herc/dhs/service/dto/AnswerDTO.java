package uk.ac.herc.dhs.service.dto;

import java.io.Serializable;
import java.util.Set;

public class AnswerDTO implements Serializable {

    private Long questionId;

    private Set<AnswerItemDTO> answerItems;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Set<AnswerItemDTO> getAnswerItems() {
        return answerItems;
    }

    public void setAnswerItems(Set<AnswerItemDTO> answerItems) {
        this.answerItems = answerItems;
    }

    @Override
    public String toString() {
        return "AnswerDTO [" +
            "questionId=" + questionId +
            "answerItems=" + answerItems +
        "]";
    }

}

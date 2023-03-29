package uk.ac.herc.dhs.service.dto;

import java.io.Serializable;

public class AnswerItemDTO implements Serializable {

    private Long questionItemId;

    private Boolean selected;

    public Long getQuestionItemId() {
        return questionItemId;
    }

    public void setQuestionItemId(Long questionItemId) {
        this.questionItemId = questionItemId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "AnswerItemDTO [questionItemId=" + questionItemId + ", selected=" + selected + "]";
    }
}

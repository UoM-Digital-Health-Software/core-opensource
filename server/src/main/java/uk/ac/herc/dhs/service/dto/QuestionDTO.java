package uk.ac.herc.dhs.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class QuestionDTO implements Serializable {

    private Long id;

    public Integer order;

    private String name;

    private String type;

    private String text;

    private String context;

    private Set<QuestionItemDTO> questionItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Set<QuestionItemDTO> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(Set<QuestionItemDTO> questionItems) {
        this.questionItems = questionItems;
    }

    @Override
    public String toString() {
        return "QuestionDTO [" +
            "context=" + context +
            ", id=" + id +
            ", order=" + order +
            ", name=" + name +
            ", questionItems=" + questionItems +
            ", text=" + text +
            ", type=" + type +
        "]";
    }
}

package uk.ac.herc.dhs.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class QuestionPageDTO implements Serializable {

    private Long id;

    public Integer order;

    private String name;

    private Boolean showSkip;

    private String title;

    private String text;

    private Set<QuestionDTO> questions = new HashSet<>();

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

    public Boolean getShowSkip() {
        return showSkip;
    }

    public void setShowSkip(Boolean showSkip) {
        this.showSkip = showSkip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "QuestionPageDTO [" +
            ", id=" + id +
            ", order=" + order +
            ", name=" + name +
            ", showSkip=" + showSkip +
            ", questions=" + questions +
            ", title=" + title +
            "text=" + text +
        "]";
    }
}

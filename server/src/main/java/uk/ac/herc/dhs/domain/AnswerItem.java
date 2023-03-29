package uk.ac.herc.dhs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AnswerItem.
 */
@Entity
@Table(name = "answer_item")
public class AnswerItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "selected", nullable = false)
    private Boolean selected;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "questions" }, allowSetters = true)
    private QuestionItem questionItem;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "answerItems", "question", "answerPage" }, allowSetters = true)
    private Answer answer;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerItem id(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public AnswerItem selected(Boolean selected) {
        this.selected = selected;
        return this;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public QuestionItem getQuestionItem() {
        return this.questionItem;
    }

    public AnswerItem questionItem(QuestionItem questionItem) {
        this.setQuestionItem(questionItem);
        return this;
    }

    public void setQuestionItem(QuestionItem questionItem) {
        this.questionItem = questionItem;
    }

    public Answer getAnswer() {
        return this.answer;
    }

    public AnswerItem answer(Answer answer) {
        this.setAnswer(answer);
        return this;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerItem)) {
            return false;
        }
        return id != null && id.equals(((AnswerItem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerItem{" +
            "id=" + getId() +
            ", selected='" + getSelected() + "'" +
            "}";
    }
}

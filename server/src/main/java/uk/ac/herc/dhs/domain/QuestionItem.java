package uk.ac.herc.dhs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A QuestionItem.
 */
@Entity
@Table(name = "question_item")
public class QuestionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "jhi_order", nullable = false)
    private Integer order;

    @NotNull
    @Column(name = "label", nullable = false)
    private String label;

    @NotNull
    @Column(name = "mandatory", nullable = false)
    private Boolean mandatory;

    @ManyToMany(mappedBy = "questionItems")
    @JsonIgnoreProperties(value = { "questionItems", "questionPage" }, allowSetters = true)
    private Set<Question> questions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionItem id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public QuestionItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return this.order;
    }

    public QuestionItem order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getLabel() {
        return this.label;
    }

    public QuestionItem label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getMandatory() {
        return this.mandatory;
    }

    public QuestionItem mandatory(Boolean mandatory) {
        this.mandatory = mandatory;
        return this;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Set<Question> getQuestions() {
        return this.questions;
    }

    public QuestionItem questions(Set<Question> questions) {
        this.setQuestions(questions);
        return this;
    }

    public QuestionItem addQuestion(Question question) {
        this.questions.add(question);
        question.getQuestionItems().add(this);
        return this;
    }

    public QuestionItem removeQuestion(Question question) {
        this.questions.remove(question);
        question.getQuestionItems().remove(this);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        if (this.questions != null) {
            this.questions.forEach(i -> i.removeQuestionItem(this));
        }
        if (questions != null) {
            questions.forEach(i -> i.addQuestionItem(this));
        }
        this.questions = questions;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionItem)) {
            return false;
        }
        return id != null && id.equals(((QuestionItem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionItem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", order=" + getOrder() +
            ", label='" + getLabel() + "'" +
            ", mandatory='" + getMandatory() + "'" +
            "}";
    }
}

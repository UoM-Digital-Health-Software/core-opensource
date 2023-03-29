package uk.ac.herc.dhs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import uk.ac.herc.dhs.domain.enumeration.QuestionType;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private QuestionType type;

    @NotNull
    @Column(name = "jhi_order", nullable = false)
    private Integer order;

    @NotNull
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "context")
    private String context;

    @ManyToMany
    @JoinTable(
        name = "rel_question__question_item",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "question_item_id")
    )
    @JsonIgnoreProperties(value = { "questions" }, allowSetters = true)
    private Set<QuestionItem> questionItems = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "questions", "skipPage", "showItem" }, allowSetters = true)
    private QuestionPage questionPage;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Question name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionType getType() {
        return this.type;
    }

    public Question type(QuestionType type) {
        this.type = type;
        return this;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Integer getOrder() {
        return this.order;
    }

    public Question order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getText() {
        return this.text;
    }

    public Question text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContext() {
        return this.context;
    }

    public Question context(String context) {
        this.context = context;
        return this;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Set<QuestionItem> getQuestionItems() {
        return this.questionItems;
    }

    public Question questionItems(Set<QuestionItem> questionItems) {
        this.setQuestionItems(questionItems);
        return this;
    }

    public Question addQuestionItem(QuestionItem questionItem) {
        this.questionItems.add(questionItem);
        questionItem.getQuestions().add(this);
        return this;
    }

    public Question removeQuestionItem(QuestionItem questionItem) {
        this.questionItems.remove(questionItem);
        questionItem.getQuestions().remove(this);
        return this;
    }

    public void setQuestionItems(Set<QuestionItem> questionItems) {
        this.questionItems = questionItems;
    }

    public QuestionPage getQuestionPage() {
        return this.questionPage;
    }

    public Question questionPage(QuestionPage questionPage) {
        this.setQuestionPage(questionPage);
        return this;
    }

    public void setQuestionPage(QuestionPage questionPage) {
        this.questionPage = questionPage;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        return id != null && id.equals(((Question) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", order=" + getOrder() +
            ", text='" + getText() + "'" +
            ", context='" + getContext() + "'" +
            "}";
    }
}

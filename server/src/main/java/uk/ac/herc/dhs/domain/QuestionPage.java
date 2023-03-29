package uk.ac.herc.dhs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A QuestionPage.
 */
@Entity
@Table(name = "question_page")
public class QuestionPage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "jhi_order", nullable = false)
    private Integer order;

    @NotNull
    @Column(name = "show_skip", nullable = false)
    private Boolean showSkip;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "questionPage")
    @JsonIgnoreProperties(value = { "questionItems", "questionPage" }, allowSetters = true)
    private Set<Question> questions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "questions", "skipPage", "showItem" }, allowSetters = true)
    private QuestionPage skipPage;

    @ManyToOne
    @JsonIgnoreProperties(value = { "questions" }, allowSetters = true)
    private QuestionItem showItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionPage id(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getActive() {
        return this.active;
    }

    public QuestionPage active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return this.name;
    }

    public QuestionPage name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return this.order;
    }

    public QuestionPage order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getShowSkip() {
        return this.showSkip;
    }

    public QuestionPage showSkip(Boolean showSkip) {
        this.showSkip = showSkip;
        return this;
    }

    public void setShowSkip(Boolean showSkip) {
        this.showSkip = showSkip;
    }

    public String getTitle() {
        return this.title;
    }

    public QuestionPage title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return this.text;
    }

    public QuestionPage text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Question> getQuestions() {
        return this.questions;
    }

    public QuestionPage questions(Set<Question> questions) {
        this.setQuestions(questions);
        return this;
    }

    public QuestionPage addQuestion(Question question) {
        this.questions.add(question);
        question.setQuestionPage(this);
        return this;
    }

    public QuestionPage removeQuestion(Question question) {
        this.questions.remove(question);
        question.setQuestionPage(null);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        if (this.questions != null) {
            this.questions.forEach(i -> i.setQuestionPage(null));
        }
        if (questions != null) {
            questions.forEach(i -> i.setQuestionPage(this));
        }
        this.questions = questions;
    }

    public QuestionPage getSkipPage() {
        return this.skipPage;
    }

    public QuestionPage skipPage(QuestionPage questionPage) {
        this.setSkipPage(questionPage);
        return this;
    }

    public void setSkipPage(QuestionPage questionPage) {
        this.skipPage = questionPage;
    }

    public QuestionItem getShowItem() {
        return this.showItem;
    }

    public QuestionPage showItem(QuestionItem questionItem) {
        this.setShowItem(questionItem);
        return this;
    }

    public void setShowItem(QuestionItem questionItem) {
        this.showItem = questionItem;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionPage)) {
            return false;
        }
        return id != null && id.equals(((QuestionPage) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionPage{" +
            "id=" + getId() +
            ", active='" + getActive() + "'" +
            ", name='" + getName() + "'" +
            ", order=" + getOrder() +
            ", showSkip='" + getShowSkip() + "'" +
            ", title='" + getTitle() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }
}

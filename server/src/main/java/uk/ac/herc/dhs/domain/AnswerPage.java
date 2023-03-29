package uk.ac.herc.dhs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AnswerPage.
 */
@Entity
@Table(name = "answer_page")
public class AnswerPage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "skip_chosen", nullable = false)
    private Boolean skipChosen;

    @NotNull
    @Column(name = "submitted", nullable = false)
    private Instant submitted;

    @NotNull
    @Column(name = "software_version", nullable = false)
    private String softwareVersion;

    @OneToMany(mappedBy = "answerPage", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = { "answerItems", "question", "answerPage" }, allowSetters = true)
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "questions", "skipPage", "showItem" }, allowSetters = true)
    private QuestionPage questionPage;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "answerPages" }, allowSetters = true)
    private Identity identity;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerPage id(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getSkipChosen() {
        return this.skipChosen;
    }

    public AnswerPage skipChosen(Boolean skipChosen) {
        this.skipChosen = skipChosen;
        return this;
    }

    public void setSkipChosen(Boolean skipChosen) {
        this.skipChosen = skipChosen;
    }

    public Instant getSubmitted() {
        return this.submitted;
    }

    public AnswerPage submitted(Instant submitted) {
        this.submitted = submitted;
        return this;
    }

    public void setSubmitted(Instant submitted) {
        this.submitted = submitted;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public AnswerPage softwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
        return this;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public Set<Answer> getAnswers() {
        return this.answers;
    }

    public AnswerPage answers(Set<Answer> answers) {
        this.setAnswers(answers);
        return this;
    }

    public AnswerPage addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setAnswerPage(this);
        return this;
    }

    public AnswerPage removeAnswer(Answer answer) {
        this.answers.remove(answer);
        answer.setAnswerPage(null);
        return this;
    }

    public void setAnswers(Set<Answer> answers) {
        if (this.answers != null) {
            this.answers.forEach(i -> i.setAnswerPage(null));
        }
        if (answers != null) {
            answers.forEach(i -> i.setAnswerPage(this));
        }
        this.answers = answers;
    }

    public QuestionPage getQuestionPage() {
        return this.questionPage;
    }

    public AnswerPage questionPage(QuestionPage questionPage) {
        this.setQuestionPage(questionPage);
        return this;
    }

    public void setQuestionPage(QuestionPage questionPage) {
        this.questionPage = questionPage;
    }

    public Identity getIdentity() {
        return this.identity;
    }

    public AnswerPage identity(Identity identity) {
        this.setIdentity(identity);
        return this;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerPage)) {
            return false;
        }
        return id != null && id.equals(((AnswerPage) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerPage{" +
            "id=" + getId() +
            ", skipChosen='" + getSkipChosen() + "'" +
            ", submitted='" + getSubmitted() + "'" +
            ", softwareVersion='" + getSoftwareVersion() + "'" +
            "}";
    }
}

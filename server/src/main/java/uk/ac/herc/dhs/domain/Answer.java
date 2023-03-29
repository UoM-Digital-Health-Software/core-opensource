package uk.ac.herc.dhs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Answer.
 */
@Entity
@Table(name = "answer")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = { "questionItem", "answer" }, allowSetters = true)
    private Set<AnswerItem> answerItems = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "questionItems", "questionPage" }, allowSetters = true)
    private Question question;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "answers", "questionPage", "identity" }, allowSetters = true)
    private AnswerPage answerPage;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Answer id(Long id) {
        this.id = id;
        return this;
    }

    public Set<AnswerItem> getAnswerItems() {
        return this.answerItems;
    }

    public Answer answerItems(Set<AnswerItem> answerItems) {
        this.setAnswerItems(answerItems);
        return this;
    }

    public Answer addAnswerItem(AnswerItem answerItem) {
        this.answerItems.add(answerItem);
        answerItem.setAnswer(this);
        return this;
    }

    public Answer removeAnswerItem(AnswerItem answerItem) {
        this.answerItems.remove(answerItem);
        answerItem.setAnswer(null);
        return this;
    }

    public void setAnswerItems(Set<AnswerItem> answerItems) {
        if (this.answerItems != null) {
            this.answerItems.forEach(i -> i.setAnswer(null));
        }
        if (answerItems != null) {
            answerItems.forEach(i -> i.setAnswer(this));
        }
        this.answerItems = answerItems;
    }

    public Question getQuestion() {
        return this.question;
    }

    public Answer question(Question question) {
        this.setQuestion(question);
        return this;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerPage getAnswerPage() {
        return this.answerPage;
    }

    public Answer answerPage(AnswerPage answerPage) {
        this.setAnswerPage(answerPage);
        return this;
    }

    public void setAnswerPage(AnswerPage answerPage) {
        this.answerPage = answerPage;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Answer)) {
            return false;
        }
        return id != null && id.equals(((Answer) o).id);
    }

    @Override
    public int hashCode() {
        // see
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Answer{" + "id=" + getId() +  "'" + "}";
    }
}

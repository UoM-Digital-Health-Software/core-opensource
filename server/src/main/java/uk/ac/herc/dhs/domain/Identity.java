package uk.ac.herc.dhs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Identity.
 */
@Entity
@Table(name = "identity")
public class Identity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "study_id", nullable = false)
    private String studyId;

    @NotNull
    @Column(name = "participant_id", nullable = false)
    private String participantId;

    @NotNull
    @Column(name = "response_id", nullable = false, unique = true)
    private String responseId;

    @NotNull
    @Column(name = "machine_id", nullable = false)
    private String machineId;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "chose_exit", nullable = false)
    private Boolean choseExit;

    @OneToMany(mappedBy = "identity")
    @JsonIgnoreProperties(value = { "answers", "questionPage", "identity" }, allowSetters = true)
    private Set<AnswerPage> answerPages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Identity id(Long id) {
        this.id = id;
        return this;
    }

    public String getStudyId() {
        return this.studyId;
    }

    public Identity studyId(String studyId) {
        this.studyId = studyId;
        return this;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getParticipantId() {
        return this.participantId;
    }

    public Identity participantId(String participantId) {
        this.participantId = participantId;
        return this;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getResponseId() {
        return this.responseId;
    }

    public Identity responseId(String responseId) {
        this.responseId = responseId;
        return this;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getMachineId() {
        return this.machineId;
    }

    public Identity machineId(String machineId) {
        this.machineId = machineId;
        return this;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public Instant getCreated() {
        return this.created;
    }

    public Identity created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Boolean getChoseExit() {
        return this.choseExit;
    }

    public Identity choseExit(Boolean choseExit) {
        this.choseExit = choseExit;
        return this;
    }

    public void setChoseExit(Boolean choseExit) {
        this.choseExit = choseExit;
    }

    public Set<AnswerPage> getAnswerPages() {
        return this.answerPages;
    }

    public Identity answerPages(Set<AnswerPage> answerPages) {
        this.setAnswerPages(answerPages);
        return this;
    }

    public Identity addAnswerPage(AnswerPage answerPage) {
        this.answerPages.add(answerPage);
        answerPage.setIdentity(this);
        return this;
    }

    public Identity removeAnswerPage(AnswerPage answerPage) {
        this.answerPages.remove(answerPage);
        answerPage.setIdentity(null);
        return this;
    }

    public void setAnswerPages(Set<AnswerPage> answerPages) {
        if (this.answerPages != null) {
            this.answerPages.forEach(i -> i.setIdentity(null));
        }
        if (answerPages != null) {
            answerPages.forEach(i -> i.setIdentity(this));
        }
        this.answerPages = answerPages;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Identity)) {
            return false;
        }
        return id != null && id.equals(((Identity) o).id);
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
        return "Identity{" + "id=" + getId() + ", studyId='" + getStudyId() + "'" + ", participantId='"
                + getParticipantId() + "'" + ", responseId='" + getResponseId() + "'" + ", machineId='" + getMachineId()
                + "'" + ", created='" + getCreated() + "'" + ", choseExit='" + getChoseExit() + "'" + "}";
    }
}

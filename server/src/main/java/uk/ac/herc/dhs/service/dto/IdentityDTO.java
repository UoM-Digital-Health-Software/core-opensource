package uk.ac.herc.dhs.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link uk.ac.herc.dhs.domain.Identity} entity.
 */
public class IdentityDTO implements Serializable {

    private String studyId;

    private String participantId;

    private String responseId;

    private String machineId;

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IdentityDTO{" +
                ", studyId='" + getStudyId() + "'" +
                ", participantId='" + getParticipantId() + "'" +
                ", responseId='" + getResponseId() + "'" +
                ", machineId='" + getMachineId() + "'" +
                "}";
    }
}

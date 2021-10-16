package com.titulacion.tdah.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.titulacion.tdah.domain.TestEdah} entity.
 */
public class TestEdahDTO implements Serializable {

    private Integer id;

    private String teacherEmail;

    private Boolean answered;

    private String key;

    private String teacherName;

    private String instructions;


    private Integer patientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public Boolean isAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestEdahDTO)) {
            return false;
        }

        return id != null && id.equals(((TestEdahDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TestEdahDTO{" +
            "id=" + getId() +
            ", teacherEmail='" + getTeacherEmail() + "'" +
            ", answered='" + isAnswered() + "'" +
            ", key='" + getKey() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            ", instructions='" + getInstructions() + "'" +
            ", patientId=" + getPatientId() +
            "}";
    }
}

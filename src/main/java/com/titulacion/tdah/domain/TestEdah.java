package com.titulacion.tdah.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TestEdah.
 */
@Entity
@Table(name = "test_edah")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestEdah implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Integer id;

    @Column(name = "teacher_email")
    private String teacherEmail;

    @Column(name = "answered")
    private Boolean answered;

    @Column(name = "key")
    private String key;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "instructions")
    private String instructions;

    @ManyToOne
    @JsonIgnoreProperties(value = "testEdahs", allowSetters = true)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public TestEdah teacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
        return this;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public Boolean isAnswered() {
        return answered;
    }

    public TestEdah answered(Boolean answered) {
        this.answered = answered;
        return this;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public String getKey() {
        return key;
    }

    public TestEdah key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public TestEdah teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getInstructions() {
        return instructions;
    }

    public TestEdah instructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Patient getPatient() {
        return patient;
    }

    public TestEdah patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestEdah)) {
            return false;
        }
        return id != null && id.equals(((TestEdah) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TestEdah{" +
            "id=" + getId() +
            ", teacherEmail='" + getTeacherEmail() + "'" +
            ", answered='" + isAnswered() + "'" +
            ", key='" + getKey() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            ", instructions='" + getInstructions() + "'" +
            "}";
    }
}

package com.titulacion.tdah.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.titulacion.tdah.domain.TestEdah} entity. This class is used
 * in {@link com.titulacion.tdah.web.rest.TestEdahResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /test-edahs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TestEdahCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private IntegerFilter id;

    private StringFilter teacherEmail;

    private BooleanFilter answered;

    private StringFilter key;

    private StringFilter teacherName;

    private StringFilter instructions;

    private IntegerFilter patientId;

    public TestEdahCriteria() {
    }

    public TestEdahCriteria(TestEdahCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.teacherEmail = other.teacherEmail == null ? null : other.teacherEmail.copy();
        this.answered = other.answered == null ? null : other.answered.copy();
        this.key = other.key == null ? null : other.key.copy();
        this.teacherName = other.teacherName == null ? null : other.teacherName.copy();
        this.instructions = other.instructions == null ? null : other.instructions.copy();
        this.patientId = other.patientId == null ? null : other.patientId.copy();
    }

    @Override
    public TestEdahCriteria copy() {
        return new TestEdahCriteria(this);
    }

    public IntegerFilter getId() {
        return id;
    }

    public void setId(IntegerFilter id) {
        this.id = id;
    }

    public StringFilter getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(StringFilter teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public BooleanFilter getAnswered() {
        return answered;
    }

    public void setAnswered(BooleanFilter answered) {
        this.answered = answered;
    }

    public StringFilter getKey() {
        return key;
    }

    public void setKey(StringFilter key) {
        this.key = key;
    }

    public StringFilter getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(StringFilter teacherName) {
        this.teacherName = teacherName;
    }

    public StringFilter getInstructions() {
        return instructions;
    }

    public void setInstructions(StringFilter instructions) {
        this.instructions = instructions;
    }

    public IntegerFilter getPatientId() {
        return patientId;
    }

    public void setPatientId(IntegerFilter patientId) {
        this.patientId = patientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TestEdahCriteria that = (TestEdahCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(teacherEmail, that.teacherEmail) &&
            Objects.equals(answered, that.answered) &&
            Objects.equals(key, that.key) &&
            Objects.equals(teacherName, that.teacherName) &&
            Objects.equals(instructions, that.instructions) &&
            Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        teacherEmail,
        answered,
        key,
        teacherName,
        instructions,
        patientId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TestEdahCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (teacherEmail != null ? "teacherEmail=" + teacherEmail + ", " : "") +
                (answered != null ? "answered=" + answered + ", " : "") +
                (key != null ? "key=" + key + ", " : "") +
                (teacherName != null ? "teacherName=" + teacherName + ", " : "") +
                (instructions != null ? "instructions=" + instructions + ", " : "") +
                (patientId != null ? "patientId=" + patientId + ", " : "") +
            "}";
    }

}

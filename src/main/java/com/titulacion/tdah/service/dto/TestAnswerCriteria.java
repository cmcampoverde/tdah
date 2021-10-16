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
 * Criteria class for the {@link com.titulacion.tdah.domain.TestAnswer} entity. This class is used
 * in {@link com.titulacion.tdah.web.rest.TestAnswerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /test-answers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TestAnswerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter value;

    private IntegerFilter testEdahId;

    private LongFilter questionId;

    public TestAnswerCriteria() {
    }

    public TestAnswerCriteria(TestAnswerCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.testEdahId = other.testEdahId == null ? null : other.testEdahId.copy();
        this.questionId = other.questionId == null ? null : other.questionId.copy();
    }

    @Override
    public TestAnswerCriteria copy() {
        return new TestAnswerCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getValue() {
        return value;
    }

    public void setValue(IntegerFilter value) {
        this.value = value;
    }

    public IntegerFilter getTestEdahId() {
        return testEdahId;
    }

    public void setTestEdahId(IntegerFilter testEdahId) {
        this.testEdahId = testEdahId;
    }

    public LongFilter getQuestionId() {
        return questionId;
    }

    public void setQuestionId(LongFilter questionId) {
        this.questionId = questionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TestAnswerCriteria that = (TestAnswerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(value, that.value) &&
            Objects.equals(testEdahId, that.testEdahId) &&
            Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        value,
        testEdahId,
        questionId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TestAnswerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (value != null ? "value=" + value + ", " : "") +
                (testEdahId != null ? "testEdahId=" + testEdahId + ", " : "") +
                (questionId != null ? "questionId=" + questionId + ", " : "") +
            "}";
    }

}

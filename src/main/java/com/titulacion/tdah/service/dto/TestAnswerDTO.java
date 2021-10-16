package com.titulacion.tdah.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.titulacion.tdah.domain.TestAnswer} entity.
 */
public class TestAnswerDTO implements Serializable {
    
    private Long id;

    private Integer value;


    private Long testEdahId;

    private Long questionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getTestEdahId() {
        return testEdahId;
    }

    public void setTestEdahId(Long testEdahId) {
        this.testEdahId = testEdahId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestAnswerDTO)) {
            return false;
        }

        return id != null && id.equals(((TestAnswerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TestAnswerDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", testEdahId=" + getTestEdahId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}

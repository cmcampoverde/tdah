package com.titulacion.tdah.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TestAnswer.
 */
@Entity
@Table(name = "test_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JsonIgnoreProperties(value = "testAnswers", allowSetters = true)
    private TestEdah testEdah;

    @ManyToOne
    @JsonIgnoreProperties(value = "testAnswers", allowSetters = true)
    @JoinColumn(name = "question_id")
    private Question question;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public TestAnswer value(Integer value) {
        this.value = value;
        return this;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TestEdah getTestEdah() {
        return testEdah;
    }

    public TestAnswer testEdah(TestEdah testEdah) {
        this.testEdah = testEdah;
        return this;
    }

    public void setTestEdah(TestEdah testEdah) {
        this.testEdah = testEdah;
    }

    public Question getQuestion() {
        return question;
    }

    public TestAnswer question(Question question) {
        this.question = question;
        return this;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestAnswer)) {
            return false;
        }
        return id != null && id.equals(((TestAnswer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TestAnswer{" +
            "id=" + getId() +
            ", value=" + getValue() +
            "}";
    }
}

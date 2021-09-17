package com.titulacion.tdah.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Score.
 */
@Entity
@Table(name = "score")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "time")
    private Double time;

    @Column(name = "level")
    private Integer level;

    @Column(name = "creation_date")
    private Instant creation_date;

    @ManyToOne
    @JsonIgnoreProperties(value = "scores", allowSetters = true)
    private Patient patient;

    @ManyToOne
    @JsonIgnoreProperties(value = "scores", allowSetters = true)
    private Game game;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTime() {
        return time;
    }

    public Score time(Double time) {
        this.time = time;
        return this;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getLevel() {
        return level;
    }

    public Score level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Instant getCreation_date() {
        return creation_date;
    }

    public Score creation_date(Instant creation_date) {
        this.creation_date = creation_date;
        return this;
    }

    public void setCreation_date(Instant creation_date) {
        this.creation_date = creation_date;
    }

    public Patient getPatient() {
        return patient;
    }

    public Score patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Game getGame() {
        return game;
    }

    public Score game(Game game) {
        this.game = game;
        return this;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
            return false;
        }
        return id != null && id.equals(((Score) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Score{" +
            "id=" + getId() +
            ", time=" + getTime() +
            ", level=" + getLevel() +
            ", creation_date='" + getCreation_date() + "'" +
            "}";
    }
}

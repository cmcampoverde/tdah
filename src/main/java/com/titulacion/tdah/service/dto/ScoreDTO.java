package com.titulacion.tdah.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.titulacion.tdah.domain.Score} entity.
 */
public class ScoreDTO implements Serializable {
    
    private Long id;

    private Double time;

    private Integer level;

    private Instant creation_date;


    private Long patientId;

    private Long gameId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Instant getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Instant creation_date) {
        this.creation_date = creation_date;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScoreDTO)) {
            return false;
        }

        return id != null && id.equals(((ScoreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScoreDTO{" +
            "id=" + getId() +
            ", time=" + getTime() +
            ", level=" + getLevel() +
            ", creation_date='" + getCreation_date() + "'" +
            ", patientId=" + getPatientId() +
            ", gameId=" + getGameId() +
            "}";
    }
}

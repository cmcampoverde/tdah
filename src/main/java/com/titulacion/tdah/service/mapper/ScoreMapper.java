package com.titulacion.tdah.service.mapper;


import com.titulacion.tdah.domain.*;
import com.titulacion.tdah.service.dto.ScoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Score} and its DTO {@link ScoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class, GameMapper.class})
public interface ScoreMapper extends EntityMapper<ScoreDTO, Score> {

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "game.id", target = "gameId")
    ScoreDTO toDto(Score score);

    @Mapping(source = "patientId", target = "patient")
    @Mapping(source = "gameId", target = "game")
    Score toEntity(ScoreDTO scoreDTO);

    default Score fromId(Long id) {
        if (id == null) {
            return null;
        }
        Score score = new Score();
        score.setId(id);
        return score;
    }
}

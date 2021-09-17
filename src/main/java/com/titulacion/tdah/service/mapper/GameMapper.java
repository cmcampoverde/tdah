package com.titulacion.tdah.service.mapper;


import com.titulacion.tdah.domain.*;
import com.titulacion.tdah.service.dto.GameDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Game} and its DTO {@link GameDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GameMapper extends EntityMapper<GameDTO, Game> {



    default Game fromId(Long id) {
        if (id == null) {
            return null;
        }
        Game game = new Game();
        game.setId(id);
        return game;
    }
}

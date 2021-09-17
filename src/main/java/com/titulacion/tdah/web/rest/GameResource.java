package com.titulacion.tdah.web.rest;

import com.titulacion.tdah.service.GameService;
import com.titulacion.tdah.web.rest.errors.BadRequestAlertException;
import com.titulacion.tdah.service.dto.GameDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.titulacion.tdah.domain.Game}.
 */
@RestController
@RequestMapping("/api")
public class GameResource {

    private final Logger log = LoggerFactory.getLogger(GameResource.class);

    private final GameService gameService;

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * {@code GET  /games} : get all the games.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of games in body.
     */
    @GetMapping("/games")
    public ResponseEntity<List<GameDTO>> getAllGames(Pageable pageable) {
        log.debug("REST request to get a page of Games");
        Page<GameDTO> page = gameService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /games/:id} : get the "id" game.
     *
     * @param id the id of the gameDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gameDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/games/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long id) {
        log.debug("REST request to get Game : {}", id);
        Optional<GameDTO> gameDTO = gameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gameDTO);
    }
}

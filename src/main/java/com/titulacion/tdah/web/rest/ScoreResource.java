package com.titulacion.tdah.web.rest;

import com.titulacion.tdah.service.ScoreService;
import com.titulacion.tdah.service.UserService;
import com.titulacion.tdah.service.dto.GameDTO;
import com.titulacion.tdah.service.dto.UserDTO;
import com.titulacion.tdah.web.rest.errors.BadRequestAlertException;
import com.titulacion.tdah.service.dto.ScoreDTO;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.titulacion.tdah.domain.Score}.
 */
@RestController
@RequestMapping("/api")
public class ScoreResource {

    private final Logger log = LoggerFactory.getLogger(ScoreResource.class);

    private static final String ENTITY_NAME = "score";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScoreService scoreService;
    private final UserService userService;

    public ScoreResource(ScoreService scoreService, UserService userService) {
        this.userService = userService;
        this.scoreService = scoreService;
    }

    /**
     * {@code POST  /scores} : Create a new score.
     *
     * @param scoreDTO the scoreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scoreDTO, or with status {@code 400 (Bad Request)} if the score has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/scores")
    public ResponseEntity<ScoreDTO> createScore(@RequestBody ScoreDTO scoreDTO) throws URISyntaxException {
        log.debug("REST request to save Score : {}", scoreDTO);
        if (scoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new score cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScoreDTO result = scoreService.save(scoreDTO);
        return ResponseEntity.created(new URI("/api/scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /scores} : Updates an existing score.
     *
     * @param scoreDTO the scoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scoreDTO,
     * or with status {@code 400 (Bad Request)} if the scoreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/scores")
    public ResponseEntity<ScoreDTO> updateScore(@RequestBody ScoreDTO scoreDTO) throws URISyntaxException {
        log.debug("REST request to update Score : {}", scoreDTO);
        if (scoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScoreDTO result = scoreService.save(scoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /scores} : get all the scores.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scores in body.
     */
    @GetMapping("/scores")
    public ResponseEntity<List<ScoreDTO>> getAllScores(Pageable pageable) {
        log.debug("REST request to get a page of Scores");
        Page<ScoreDTO> page = scoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /scores/:id} : get the "id" score.
     *
     * @param id the id of the scoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/scores/{id}")
    public ResponseEntity<ScoreDTO> getScore(@PathVariable Long id) {
        log.debug("REST request to get Score : {}", id);
        Optional<ScoreDTO> scoreDTO = scoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scoreDTO);
    }

    /**
     * {@code DELETE  /scores/:id} : delete the "id" score.
     *
     * @param id the id of the scoreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/scores/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        log.debug("REST request to delete Score : {}", id);
        scoreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /scores/lowers} : get the list of lowers scores by level
     *
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scores in body.
     */
    @GetMapping("/scores/lowers")
    public ResponseEntity<List<ScoreService.ResultLower>> getLowersScores() {
        log.debug("REST request to get a page of Scores");
        UserDTO userDTO = userService.getUserWithAuthorities()
            .map(UserDTO::new)
            .orElseThrow(() -> new AccountResource.AccountResourceException("User could not be found"));
        Integer patientId = userService.getPatientIdByLogin(userDTO.getLogin());
        List<ScoreService.ResultLower> scores = scoreService.findLowersScores(patientId);
        return ResponseEntity.ok().body(scores);
    }

    @GetMapping("/scores/lowers/{idPatient}")
    public ResponseEntity<List<ScoreService.ResultLower>> getLowersScores(@PathVariable Integer idPatient) {
        log.debug("REST request to get a page of Scores");
        List<ScoreService.ResultLower> scores = scoreService.findLowersScores(idPatient);
        return ResponseEntity.ok().body(scores);
    }

    /**
     * {@code GET  /scores/last-levels} : get last levels by games.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of last levels by games in body.
     */
    @GetMapping("/scores/last-levels")
    public ResponseEntity<List<ScoreService.ResultLastLevel>> getALastLevelsByGames() {
        log.debug("REST request to get a page of Games");
        UserDTO userDTO = userService.getUserWithAuthorities()
            .map(UserDTO::new)
            .orElseThrow(() -> new AccountResource.AccountResourceException("User could not be found"));
        Integer patientId = userService.getPatientIdByLogin(userDTO.getLogin());
        List<ScoreService.ResultLastLevel> scoreServiceLastLevelsByGame= scoreService.getLastLevelsByGame(patientId);
        return ResponseEntity.ok().body(scoreServiceLastLevelsByGame);
    }
}

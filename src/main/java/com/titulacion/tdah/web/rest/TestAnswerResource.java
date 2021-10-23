package com.titulacion.tdah.web.rest;

import com.titulacion.tdah.service.TestAnswerService;
import com.titulacion.tdah.web.rest.errors.BadRequestAlertException;
import com.titulacion.tdah.service.dto.TestAnswerDTO;
import com.titulacion.tdah.service.dto.TestAnswerCriteria;
import com.titulacion.tdah.service.TestAnswerQueryService;

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
 * REST controller for managing {@link com.titulacion.tdah.domain.TestAnswer}.
 */
@RestController
@RequestMapping("/api")
public class TestAnswerResource {

    private final Logger log = LoggerFactory.getLogger(TestAnswerResource.class);

    private static final String ENTITY_NAME = "testAnswer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestAnswerService testAnswerService;

    private final TestAnswerQueryService testAnswerQueryService;

    public TestAnswerResource(TestAnswerService testAnswerService, TestAnswerQueryService testAnswerQueryService) {
        this.testAnswerService = testAnswerService;
        this.testAnswerQueryService = testAnswerQueryService;
    }

    /**
     * {@code POST  /test-answers} : Create a new testAnswer.
     *
     * @param testAnswerDTO the testAnswerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testAnswerDTO, or with status {@code 400 (Bad Request)} if the testAnswer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/test-answers")
    public ResponseEntity<TestAnswerDTO> createTestAnswer(@RequestBody TestAnswerDTO testAnswerDTO) throws URISyntaxException {
        log.debug("REST request to save TestAnswer : {}", testAnswerDTO);
        if (testAnswerDTO.getId() != null) {
            throw new BadRequestAlertException("A new testAnswer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestAnswerDTO result = testAnswerService.save(testAnswerDTO);
        return ResponseEntity.created(new URI("/api/test-answers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/insert/test-answers")
    public ResponseEntity<TestAnswerDTO> insertTestAnswer(@RequestBody TestAnswerDTO testAnswerDTO) throws URISyntaxException {
        log.debug("REST request to save TestAnswer : {}", testAnswerDTO);
        if (testAnswerDTO.getId() != null) {
            throw new BadRequestAlertException("A new testAnswer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestAnswerDTO result = testAnswerService.save(testAnswerDTO);
        return ResponseEntity.created(new URI("/api/test-answers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /test-answers} : Updates an existing testAnswer.
     *
     * @param testAnswerDTO the testAnswerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testAnswerDTO,
     * or with status {@code 400 (Bad Request)} if the testAnswerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testAnswerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/test-answers")
    public ResponseEntity<TestAnswerDTO> updateTestAnswer(@RequestBody TestAnswerDTO testAnswerDTO) throws URISyntaxException {
        log.debug("REST request to update TestAnswer : {}", testAnswerDTO);
        if (testAnswerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestAnswerDTO result = testAnswerService.save(testAnswerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testAnswerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /test-answers} : get all the testAnswers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testAnswers in body.
     */
    @GetMapping("/test-answers")
    public ResponseEntity<List<TestAnswerDTO>> getAllTestAnswers(TestAnswerCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TestAnswers by criteria: {}", criteria);
        Page<TestAnswerDTO> page = testAnswerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /test-answers/count} : count all the testAnswers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/test-answers/count")
    public ResponseEntity<Long> countTestAnswers(TestAnswerCriteria criteria) {
        log.debug("REST request to count TestAnswers by criteria: {}", criteria);
        return ResponseEntity.ok().body(testAnswerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /test-answers/:id} : get the "id" testAnswer.
     *
     * @param id the id of the testAnswerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testAnswerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/test-answers/{id}")
    public ResponseEntity<TestAnswerDTO> getTestAnswer(@PathVariable Long id) {
        log.debug("REST request to get TestAnswer : {}", id);
        Optional<TestAnswerDTO> testAnswerDTO = testAnswerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testAnswerDTO);
    }

    /**
     * {@code DELETE  /test-answers/:id} : delete the "id" testAnswer.
     *
     * @param id the id of the testAnswerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/test-answers/{id}")
    public ResponseEntity<Void> deleteTestAnswer(@PathVariable Long id) {
        log.debug("REST request to delete TestAnswer : {}", id);
        testAnswerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

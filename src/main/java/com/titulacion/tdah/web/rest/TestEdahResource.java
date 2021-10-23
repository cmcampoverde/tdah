package com.titulacion.tdah.web.rest;

import com.titulacion.tdah.service.TestEdahService;
import com.titulacion.tdah.web.rest.errors.BadRequestAlertException;
import com.titulacion.tdah.service.dto.TestEdahDTO;
import com.titulacion.tdah.service.dto.TestEdahCriteria;
import com.titulacion.tdah.service.TestEdahQueryService;

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
 * REST controller for managing {@link com.titulacion.tdah.domain.TestEdah}.
 */
@RestController
@RequestMapping("/api")
public class TestEdahResource {

    private final Logger log = LoggerFactory.getLogger(TestEdahResource.class);

    private static final String ENTITY_NAME = "testEdah";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestEdahService testEdahService;

    private final TestEdahQueryService testEdahQueryService;

    public TestEdahResource(TestEdahService testEdahService, TestEdahQueryService testEdahQueryService) {
        this.testEdahService = testEdahService;
        this.testEdahQueryService = testEdahQueryService;
    }

    /**
     * {@code POST  /test-edahs} : Create a new testEdah.
     *
     * @param testEdahDTO the testEdahDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testEdahDTO, or with status {@code 400 (Bad Request)} if the testEdah has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/test-edahs")
    public ResponseEntity<TestEdahDTO> createTestEdah(@RequestBody TestEdahDTO testEdahDTO) throws URISyntaxException {
        log.debug("REST request to save TestEdah : {}", testEdahDTO);
        if (testEdahDTO.getId() != null) {
            throw new BadRequestAlertException("A new testEdah cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestEdahDTO result = testEdahService.save(testEdahDTO);
        return ResponseEntity.created(new URI("/api/test-edahs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /test-edahs} : Updates an existing testEdah.
     *
     * @param testEdahDTO the testEdahDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testEdahDTO,
     * or with status {@code 400 (Bad Request)} if the testEdahDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testEdahDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/test-edahs")
    public ResponseEntity<TestEdahDTO> updateTestEdah(@RequestBody TestEdahDTO testEdahDTO) throws URISyntaxException {
        log.debug("REST request to update TestEdah : {}", testEdahDTO);
        if (testEdahDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestEdahDTO result = testEdahService.save(testEdahDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testEdahDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/update/test-edahs")
    public ResponseEntity<TestEdahDTO> updatePublicTestEdah(@RequestBody TestEdahDTO testEdahDTO) throws URISyntaxException {
        log.debug("REST request to update TestEdah : {}", testEdahDTO);
        if (testEdahDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestEdahDTO result = testEdahService.save(testEdahDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testEdahDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /test-edahs} : get all the testEdahs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testEdahs in body.
     */
    @GetMapping("/test-edahs")
    public ResponseEntity<List<TestEdahDTO>> getAllTestEdahs(TestEdahCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TestEdahs by criteria: {}", criteria);
        Page<TestEdahDTO> page = testEdahQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/read/test-edahs")
    public ResponseEntity<List<TestEdahDTO>> readAllTestEdahs(TestEdahCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TestEdahs by criteria: {}", criteria);
        Page<TestEdahDTO> page = testEdahQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /test-edahs/count} : count all the testEdahs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/test-edahs/count")
    public ResponseEntity<Long> countTestEdahs(TestEdahCriteria criteria) {
        log.debug("REST request to count TestEdahs by criteria: {}", criteria);
        return ResponseEntity.ok().body(testEdahQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /test-edahs/:id} : get the "id" testEdah.
     *
     * @param id the id of the testEdahDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testEdahDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/test-edahs/{id}")
    public ResponseEntity<TestEdahDTO> getTestEdah(@PathVariable Integer id) {
        log.debug("REST request to get TestEdah : {}", id);
        Optional<TestEdahDTO> testEdahDTO = testEdahService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testEdahDTO);
    }

    /**
     * {@code DELETE  /test-edahs/:id} : delete the "id" testEdah.
     *
     * @param id the id of the testEdahDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/test-edahs/{id}")
    public ResponseEntity<Void> deleteTestEdah(@PathVariable Integer id) {
        log.debug("REST request to delete TestEdah : {}", id);
        testEdahService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/test-edahs/send-edah")
    public ResponseEntity<TestEdahDTO> sendTestEdah(@RequestBody TestEdahDTO testEdahDTO) throws URISyntaxException {
        log.debug("REST request to save and send TestEdah : {}", testEdahDTO);
        if (testEdahDTO.getId() != null) {
            throw new BadRequestAlertException("A new testEdah cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestEdahDTO result = testEdahService.saveToSendEmail(testEdahDTO);
        return ResponseEntity.created(new URI("/api/test-edahs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}

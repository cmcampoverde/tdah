package com.titulacion.tdah.service;

import com.titulacion.tdah.domain.TestEdah;
import com.titulacion.tdah.repository.TestEdahRepository;
import com.titulacion.tdah.service.dto.TestEdahDTO;
import com.titulacion.tdah.service.mapper.TestEdahMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TestEdah}.
 */
@Service
@Transactional
public class TestEdahService {

    private final Logger log = LoggerFactory.getLogger(TestEdahService.class);

    private final TestEdahRepository testEdahRepository;

    private final TestEdahMapper testEdahMapper;

    public TestEdahService(TestEdahRepository testEdahRepository, TestEdahMapper testEdahMapper) {
        this.testEdahRepository = testEdahRepository;
        this.testEdahMapper = testEdahMapper;
    }

    /**
     * Save a testEdah.
     *
     * @param testEdahDTO the entity to save.
     * @return the persisted entity.
     */
    public TestEdahDTO save(TestEdahDTO testEdahDTO) {
        log.debug("Request to save TestEdah : {}", testEdahDTO);
        TestEdah testEdah = testEdahMapper.toEntity(testEdahDTO);
        testEdah = testEdahRepository.save(testEdah);
        return testEdahMapper.toDto(testEdah);
    }

    /**
     * Get all the testEdahs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TestEdahDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TestEdahs");
        return testEdahRepository.findAll(pageable)
            .map(testEdahMapper::toDto);
    }


    /**
     * Get one testEdah by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TestEdahDTO> findOne(Integer id) {
        log.debug("Request to get TestEdah : {}", id);
        return testEdahRepository.findById(id)
            .map(testEdahMapper::toDto);
    }

    /**
     * Delete the testEdah by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Integer id) {
        log.debug("Request to delete TestEdah : {}", id);
        testEdahRepository.deleteById(id);
    }
}

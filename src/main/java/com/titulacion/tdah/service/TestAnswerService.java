package com.titulacion.tdah.service;

import com.titulacion.tdah.domain.TestAnswer;
import com.titulacion.tdah.repository.TestAnswerRepository;
import com.titulacion.tdah.service.dto.TestAnswerDTO;
import com.titulacion.tdah.service.mapper.TestAnswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TestAnswer}.
 */
@Service
@Transactional
public class TestAnswerService {

    private final Logger log = LoggerFactory.getLogger(TestAnswerService.class);

    private final TestAnswerRepository testAnswerRepository;

    private final TestAnswerMapper testAnswerMapper;

    public TestAnswerService(TestAnswerRepository testAnswerRepository, TestAnswerMapper testAnswerMapper) {
        this.testAnswerRepository = testAnswerRepository;
        this.testAnswerMapper = testAnswerMapper;
    }

    /**
     * Save a testAnswer.
     *
     * @param testAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    public TestAnswerDTO save(TestAnswerDTO testAnswerDTO) {
        log.debug("Request to save TestAnswer : {}", testAnswerDTO);
        TestAnswer testAnswer = testAnswerMapper.toEntity(testAnswerDTO);
        testAnswer = testAnswerRepository.save(testAnswer);
        return testAnswerMapper.toDto(testAnswer);
    }

    /**
     * Get all the testAnswers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TestAnswerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TestAnswers");
        return testAnswerRepository.findAll(pageable)
            .map(testAnswerMapper::toDto);
    }


    /**
     * Get one testAnswer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TestAnswerDTO> findOne(Long id) {
        log.debug("Request to get TestAnswer : {}", id);
        return testAnswerRepository.findById(id)
            .map(testAnswerMapper::toDto);
    }

    /**
     * Delete the testAnswer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TestAnswer : {}", id);
        testAnswerRepository.deleteById(id);
    }
}

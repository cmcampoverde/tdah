package com.titulacion.tdah.service;

import com.titulacion.tdah.Util.RandomString;
import com.titulacion.tdah.domain.TestEdah;
import com.titulacion.tdah.repository.TestEdahRepository;
import com.titulacion.tdah.service.dto.TestEdahDTO;
import com.titulacion.tdah.service.mapper.TestEdahMapper;
import io.github.jhipster.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Service Implementation for managing {@link TestEdah}.
 */
@Service
@Transactional
public class TestEdahService {

    private final Logger log = LoggerFactory.getLogger(TestEdahService.class);

    private final TestEdahRepository testEdahRepository;

    private final TestEdahMapper testEdahMapper;

    private final MailService mailService;

    private final JHipsterProperties jHipsterProperties;


    public TestEdahService(TestEdahRepository testEdahRepository, TestEdahMapper testEdahMapper, MailService mailService,
                           JHipsterProperties jHipsterProperties) {
        this.testEdahRepository = testEdahRepository;
        this.testEdahMapper = testEdahMapper;
        this.mailService = mailService;
        this.jHipsterProperties = jHipsterProperties;
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


    public TestEdahDTO saveToSendEmail(TestEdahDTO testEdahDTO) {
        log.debug("Request to save TestEdah : {}", testEdahDTO);
        testEdahDTO.setAnswered(false);
        RandomString randomString = new RandomString(8, ThreadLocalRandom.current());
        String keyEdah = randomString.nextString();
        testEdahDTO.setKey(keyEdah);
        TestEdah testEdah = testEdahMapper.toEntity(testEdahDTO);
        testEdah = testEdahRepository.save(testEdah);
        mailService.sendEmail(
            testEdahDTO.getTeacherEmail(),
            "Test Edah",
            "Porfavor llena el Test: " + jHipsterProperties.getMail().getBaseUrl() +"/edah/test-edah/?key=" + keyEdah,
            false,
            false);

        return testEdahMapper.toDto(testEdah);
    }
}

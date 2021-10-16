package com.titulacion.tdah.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.titulacion.tdah.domain.TestAnswer;
import com.titulacion.tdah.domain.*; // for static metamodels
import com.titulacion.tdah.repository.TestAnswerRepository;
import com.titulacion.tdah.service.dto.TestAnswerCriteria;
import com.titulacion.tdah.service.dto.TestAnswerDTO;
import com.titulacion.tdah.service.mapper.TestAnswerMapper;

/**
 * Service for executing complex queries for {@link TestAnswer} entities in the database.
 * The main input is a {@link TestAnswerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TestAnswerDTO} or a {@link Page} of {@link TestAnswerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TestAnswerQueryService extends QueryService<TestAnswer> {

    private final Logger log = LoggerFactory.getLogger(TestAnswerQueryService.class);

    private final TestAnswerRepository testAnswerRepository;

    private final TestAnswerMapper testAnswerMapper;

    public TestAnswerQueryService(TestAnswerRepository testAnswerRepository, TestAnswerMapper testAnswerMapper) {
        this.testAnswerRepository = testAnswerRepository;
        this.testAnswerMapper = testAnswerMapper;
    }

    /**
     * Return a {@link List} of {@link TestAnswerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TestAnswerDTO> findByCriteria(TestAnswerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TestAnswer> specification = createSpecification(criteria);
        return testAnswerMapper.toDto(testAnswerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TestAnswerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TestAnswerDTO> findByCriteria(TestAnswerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TestAnswer> specification = createSpecification(criteria);
        return testAnswerRepository.findAll(specification, page)
            .map(testAnswerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TestAnswerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TestAnswer> specification = createSpecification(criteria);
        return testAnswerRepository.count(specification);
    }

    /**
     * Function to convert {@link TestAnswerCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TestAnswer> createSpecification(TestAnswerCriteria criteria) {
        Specification<TestAnswer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TestAnswer_.id));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValue(), TestAnswer_.value));
            }
            if (criteria.getTestEdahId() != null) {
                specification = specification.and(buildSpecification(criteria.getTestEdahId(),
                    root -> root.join(TestAnswer_.testEdah, JoinType.LEFT).get(TestEdah_.id)));
            }
            if (criteria.getQuestionId() != null) {
                specification = specification.and(buildSpecification(criteria.getQuestionId(),
                    root -> root.join(TestAnswer_.question, JoinType.LEFT).get(Question_.id)));
            }
        }
        return specification;
    }
}

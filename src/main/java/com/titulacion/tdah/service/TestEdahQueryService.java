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

import com.titulacion.tdah.domain.TestEdah;
import com.titulacion.tdah.domain.*; // for static metamodels
import com.titulacion.tdah.repository.TestEdahRepository;
import com.titulacion.tdah.service.dto.TestEdahCriteria;
import com.titulacion.tdah.service.dto.TestEdahDTO;
import com.titulacion.tdah.service.mapper.TestEdahMapper;

/**
 * Service for executing complex queries for {@link TestEdah} entities in the database.
 * The main input is a {@link TestEdahCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TestEdahDTO} or a {@link Page} of {@link TestEdahDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TestEdahQueryService extends QueryService<TestEdah> {

    private final Logger log = LoggerFactory.getLogger(TestEdahQueryService.class);

    private final TestEdahRepository testEdahRepository;

    private final TestEdahMapper testEdahMapper;

    public TestEdahQueryService(TestEdahRepository testEdahRepository, TestEdahMapper testEdahMapper) {
        this.testEdahRepository = testEdahRepository;
        this.testEdahMapper = testEdahMapper;
    }

    /**
     * Return a {@link List} of {@link TestEdahDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TestEdahDTO> findByCriteria(TestEdahCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TestEdah> specification = createSpecification(criteria);
        return testEdahMapper.toDto(testEdahRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TestEdahDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TestEdahDTO> findByCriteria(TestEdahCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TestEdah> specification = createSpecification(criteria);
        return testEdahRepository.findAll(specification, page)
            .map(testEdahMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TestEdahCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TestEdah> specification = createSpecification(criteria);
        return testEdahRepository.count(specification);
    }

    /**
     * Function to convert {@link TestEdahCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TestEdah> createSpecification(TestEdahCriteria criteria) {
        Specification<TestEdah> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TestEdah_.id));
            }
            if (criteria.getTeacherEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeacherEmail(), TestEdah_.teacherEmail));
            }
            if (criteria.getAnswered() != null) {
                specification = specification.and(buildSpecification(criteria.getAnswered(), TestEdah_.answered));
            }
            if (criteria.getKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKey(), TestEdah_.key));
            }
            if (criteria.getTeacherName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeacherName(), TestEdah_.teacherName));
            }
            if (criteria.getInstructions() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstructions(), TestEdah_.instructions));
            }
            if (criteria.getPatientId() != null) {
                specification = specification.and(buildSpecification(criteria.getPatientId(),
                    root -> root.join(TestEdah_.patient, JoinType.LEFT).get(Patient_.id)));
            }
        }
        return specification;
    }
}

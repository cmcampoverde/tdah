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

import com.titulacion.tdah.domain.Patient;
import com.titulacion.tdah.domain.*; // for static metamodels
import com.titulacion.tdah.repository.PatientRepository;
import com.titulacion.tdah.service.dto.PatientCriteria;
import com.titulacion.tdah.service.dto.PatientDTO;
import com.titulacion.tdah.service.mapper.PatientMapper;

/**
 * Service for executing complex queries for {@link Patient} entities in the database.
 * The main input is a {@link PatientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PatientDTO} or a {@link Page} of {@link PatientDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PatientQueryService extends QueryService<Patient> {

    private final Logger log = LoggerFactory.getLogger(PatientQueryService.class);

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public PatientQueryService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    /**
     * Return a {@link List} of {@link PatientDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PatientDTO> findByCriteria(PatientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Patient> specification = createSpecification(criteria);
        return patientMapper.toDto(patientRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PatientDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PatientDTO> findByCriteria(PatientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Patient> specification = createSpecification(criteria);
        return patientRepository.findAll(specification, page)
            .map(patientMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PatientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Patient> specification = createSpecification(criteria);
        return patientRepository.count(specification);
    }

    /**
     * Function to convert {@link PatientCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Patient> createSpecification(PatientCriteria criteria) {
        Specification<Patient> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Patient_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Patient_.name));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), Patient_.lastName));
            }
            if (criteria.getAge() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAge(), Patient_.age));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Patient_.description));
            }
            if (criteria.getDiagnostic() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiagnostic(), Patient_.diagnostic));
            }
            if (criteria.getSex() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSex(), Patient_.sex));
            }
            if (criteria.getParent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParent(), Patient_.parent));
            }
            if (criteria.getBirthday() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthday(), Patient_.birthday));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Patient_.address));
            }
            if (criteria.getPhoneParent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneParent(), Patient_.phoneParent));
            }
        }
        return specification;
    }
}

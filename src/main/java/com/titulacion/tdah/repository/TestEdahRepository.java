package com.titulacion.tdah.repository;

import com.titulacion.tdah.domain.TestEdah;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TestEdah entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestEdahRepository extends JpaRepository<TestEdah, Integer>, JpaSpecificationExecutor<TestEdah> {
}

package com.titulacion.tdah.repository;

import com.titulacion.tdah.domain.TestAnswer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TestAnswer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long>, JpaSpecificationExecutor<TestAnswer> {
}

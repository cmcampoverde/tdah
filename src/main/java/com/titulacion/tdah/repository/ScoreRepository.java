package com.titulacion.tdah.repository;

import com.titulacion.tdah.domain.PersistentAuditEvent;
import com.titulacion.tdah.domain.Score;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Score entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findAllByPatientId(Integer patientId);
}

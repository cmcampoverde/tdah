package com.titulacion.tdah.repository;

import com.titulacion.tdah.domain.Score;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Score entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

}

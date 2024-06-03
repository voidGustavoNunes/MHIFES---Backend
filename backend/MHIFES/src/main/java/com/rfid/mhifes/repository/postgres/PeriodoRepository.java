package com.rfid.mhifes.repository.postgres;

import java.time.Year;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {

	Page<Periodo> findAll(Pageable pageable);

	Optional<Periodo> findByAnoAndSemestre(Year ano, Long semestre);
}
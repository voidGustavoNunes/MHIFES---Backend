package com.rfid.mhifes.repository.postgres;

import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.postgres.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {

	Page<Periodo> findAll(Pageable pageable);

	Optional<Periodo> findByAnoAndSemestre(Year ano, Long semestre);
    
	@Query("SELECT p FROM Periodo p WHERE p.dataInicio = TO_DATE(:dia, 'DD/MM/YYYY')")
	Page<Periodo> findByDiaContaining(@Param("dia") String dia, Pageable pageable);
    
	@Query("SELECT p FROM Periodo p WHERE p.ano = :ano")
	Page<Periodo> findByAnoContaining(@Param("ano") Year ano, Pageable pageable);
}

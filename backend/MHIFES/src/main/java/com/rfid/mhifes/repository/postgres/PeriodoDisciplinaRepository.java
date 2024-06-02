package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.PeriodoDisciplina;

public interface PeriodoDisciplinaRepository extends JpaRepository<PeriodoDisciplina, Long> {

    Page<PeriodoDisciplina> findAll(Pageable pageable);
}

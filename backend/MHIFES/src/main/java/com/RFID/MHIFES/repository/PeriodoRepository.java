package com.rfid.mhifes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {

    Page<Periodo> findAll(Pageable pageable);
}

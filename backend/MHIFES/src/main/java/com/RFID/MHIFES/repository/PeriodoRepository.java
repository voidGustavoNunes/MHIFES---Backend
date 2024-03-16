package com.rfid.mhifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
    
}

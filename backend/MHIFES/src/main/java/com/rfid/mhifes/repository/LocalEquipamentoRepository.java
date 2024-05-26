package com.rfid.mhifes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.LocalEquipamento;

public interface LocalEquipamentoRepository extends JpaRepository<LocalEquipamento, Long> {

    Page<LocalEquipamento> findAll(Pageable pageable);
}

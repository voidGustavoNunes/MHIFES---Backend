package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.LocalEquipamento;

public interface LocalEquipamentoRepository extends JpaRepository<LocalEquipamento, Long> {

    Page<LocalEquipamento> findAll(Pageable pageable);
}

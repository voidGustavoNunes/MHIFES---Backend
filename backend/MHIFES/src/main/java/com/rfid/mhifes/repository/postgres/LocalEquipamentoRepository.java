package com.rfid.mhifes.repository.postgres;

import com.rfid.mhifes.model.postgres.Equipamento;
import com.rfid.mhifes.model.postgres.LocalEquipamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalEquipamentoRepository extends JpaRepository<LocalEquipamento, Long> {

    Page<LocalEquipamento> findAll(Pageable pageable);

    List<LocalEquipamento> findByEquipamento(Equipamento equipamento);
}

package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.postgres.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    Page<Equipamento> findAll(Pageable pageable);
    
	@Query("SELECT e FROM Equipamento e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Equipamento> findByNomeContaining(@Param("substring") String substring, Pageable pageable);
    
}

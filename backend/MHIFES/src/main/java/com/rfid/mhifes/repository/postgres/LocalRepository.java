package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.postgres.Local;

public interface LocalRepository extends JpaRepository<Local, Long> {

    Page<Local> findAll(Pageable pageable);
    
	@Query("SELECT l FROM Local l WHERE LOWER(l.nome) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Local> findByNomeContaining(@Param("substring") String substring, Pageable pageable);
    
	@Query("SELECT l FROM Local l WHERE l.capacidade = :capa")
	Page<Local> findByCapacidadeContaining(@Param("capa") Integer capa, Pageable pageable);
}

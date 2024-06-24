package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.postgres.Coordenadoria;

public interface CoordenadoriaRepository extends JpaRepository<Coordenadoria, Long> {

    Page<Coordenadoria> findAll(Pageable pageable);

    Coordenadoria findByNome(String nome);
    
	@Query("SELECT c FROM Coordenadoria c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Coordenadoria> findByNomeContaining(@Param("substring") String substring, Pageable pageable);
}

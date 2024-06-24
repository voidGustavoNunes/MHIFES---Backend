package com.rfid.mhifes.repository.postgres;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.postgres.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Page<Disciplina> findAll(Pageable pageable);

    Optional<Disciplina> findByNomeAndSigla(String nome, String sigla);
    
	@Query("SELECT d FROM Disciplina d WHERE LOWER(d.nome) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Disciplina> findByNomeContaining(@Param("substring") String substring, Pageable pageable);
    
	@Query("SELECT d FROM Disciplina d WHERE LOWER(d.sigla) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Disciplina> findBySiglaContaining(@Param("substring") String substring, Pageable pageable);
}

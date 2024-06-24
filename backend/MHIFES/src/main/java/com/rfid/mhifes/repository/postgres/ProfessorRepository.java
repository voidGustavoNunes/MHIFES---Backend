package com.rfid.mhifes.repository.postgres;

import com.rfid.mhifes.model.postgres.Coordenadoria;
import com.rfid.mhifes.model.postgres.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    boolean existsByMatricula(String matricula);

    Page<Professor> findAll(Pageable pageable);

	Professor findByMatricula(String matricula);
    
	@Query("SELECT p FROM Professor p, Pessoa ps WHERE p.id = ps.id AND LOWER(ps.nome) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Professor> findByNomeContaining(@Param("substring") String substring, Pageable pageable);
    
	@Query("SELECT p FROM Professor p, Pessoa ps WHERE p.id = ps.id AND LOWER(ps.sigla) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Professor> findBySiglaContaining(@Param("substring") String substring, Pageable pageable);
    
	@Query("SELECT p FROM Professor p, Pessoa ps WHERE p.id = ps.id AND LOWER(ps.matricula) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Professor> findByMatriculaContaining(@Param("substring") String substring, Pageable pageable);

    List<Professor> findByCoordenadoria(Coordenadoria coordenadoria);
}

package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.postgres.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByMatricula(String matricula);

    Page<Aluno> findAll(Pageable pageable);

    
	@Query("SELECT a FROM Aluno a, Pessoa ps WHERE a.id = ps.id AND LOWER(ps.nome) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Aluno> findByNomeContaining(@Param("substring") String substring, Pageable pageable);
    
	@Query("SELECT a FROM Aluno a, Pessoa ps WHERE a.id = ps.id AND LOWER(ps.matricula) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Aluno> findByMatriculaContaining(@Param("substring") String substring, Pageable pageable);
}

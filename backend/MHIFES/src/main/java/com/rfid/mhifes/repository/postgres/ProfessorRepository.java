package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    boolean existsByMatricula(String matricula);

    Page<Professor> findAll(Pageable pageable);
}

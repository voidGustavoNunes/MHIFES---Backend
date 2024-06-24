package com.rfid.mhifes.repository.postgres;

import com.rfid.mhifes.model.postgres.Coordenadoria;
import com.rfid.mhifes.model.postgres.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    boolean existsByMatricula(String matricula);

    Page<Professor> findAll(Pageable pageable);

    Professor findByMatricula(String matricula);

    List<Professor> findByCoordenadoria(Coordenadoria coordenadoria);
}

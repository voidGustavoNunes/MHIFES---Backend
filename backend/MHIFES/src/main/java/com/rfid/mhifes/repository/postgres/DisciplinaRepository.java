package com.rfid.mhifes.repository.postgres;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Page<Disciplina> findAll(Pageable pageable);

    Optional<Disciplina> findByNomeAndSigla(String nome, String sigla);
}

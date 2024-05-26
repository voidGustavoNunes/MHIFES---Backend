package com.rfid.mhifes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Page<Disciplina> findAll(Pageable pageable);
}

package com.rfid.mhifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}

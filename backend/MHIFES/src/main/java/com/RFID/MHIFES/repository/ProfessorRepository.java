package com.rfid.mhifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}

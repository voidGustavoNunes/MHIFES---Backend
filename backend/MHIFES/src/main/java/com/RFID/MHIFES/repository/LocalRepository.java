package com.rfid.mhifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Local;

public interface LocalRepository extends JpaRepository<Local, Long>{
    
}

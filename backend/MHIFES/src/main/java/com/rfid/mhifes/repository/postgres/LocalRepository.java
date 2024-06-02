package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Local;

public interface LocalRepository extends JpaRepository<Local, Long> {

    Page<Local> findAll(Pageable pageable);
}

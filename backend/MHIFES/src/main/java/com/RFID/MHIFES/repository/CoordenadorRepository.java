package com.rfid.mhifes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Coordenador;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {

    Page<Coordenador> findAll(Pageable pageable);
}

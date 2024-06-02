package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Coordenadoria;

public interface CoordenadoriaRepository extends JpaRepository<Coordenadoria, Long> {

    Page<Coordenadoria> findAll(Pageable pageable);

    Coordenadoria findByNome(String nome);
}

package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findAll(Pageable pageable);
}

package com.rfid.mhifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}

package com.RFID.MHIFES.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RFID.MHIFES.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}

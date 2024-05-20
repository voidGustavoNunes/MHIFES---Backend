package com.RFID.MHIFES.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

import com.RFID.MHIFES.model.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    boolean existsByHoraInicioAndHoraFim(LocalTime horaInicio, LocalTime horaFim);
}

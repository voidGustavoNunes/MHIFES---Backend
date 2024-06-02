package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Horario;

import java.time.LocalTime;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    boolean existsByHoraInicioAndHoraFim(LocalTime horaInicio, LocalTime horaFim);

    Page<Horario> findAll(Pageable pageable);
}

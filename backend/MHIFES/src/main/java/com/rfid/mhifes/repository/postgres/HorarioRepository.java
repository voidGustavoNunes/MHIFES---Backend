package com.rfid.mhifes.repository.postgres;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    boolean existsByHoraInicioAndHoraFim(LocalTime horaInicio, LocalTime horaFim);

    Page<Horario> findAll(Pageable pageable);

    Optional<Horario> findByHoraInicioAndHoraFim(LocalTime inicio, LocalTime fim);
}

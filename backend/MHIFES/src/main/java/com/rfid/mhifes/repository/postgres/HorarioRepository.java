package com.rfid.mhifes.repository.postgres;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.postgres.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    boolean existsByHoraInicioAndHoraFim(LocalTime horaInicio, LocalTime horaFim);

    Page<Horario> findAll(Pageable pageable);

    Optional<Horario> findByHoraInicioAndHoraFim(LocalTime inicio, LocalTime fim);
    
    @Query("SELECT h FROM Horario h WHERE h.horaInicio = :time")
    Page<Horario> findByTimeInicio(@Param("time") LocalTime time, Pageable pageable);
    
    @Query("SELECT h FROM Horario h WHERE h.horaFim = :time")
    Page<Horario> findByTimeFim(@Param("time") LocalTime time, Pageable pageable);
}

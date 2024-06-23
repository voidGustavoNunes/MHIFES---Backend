package com.rfid.mhifes.repository.postgres;

import com.rfid.mhifes.model.postgres.Evento;
import com.rfid.mhifes.model.postgres.Horario;
import com.rfid.mhifes.model.postgres.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findAll(Pageable pageable);

    Optional<Evento> findByHorarioAndDataEventoAndLocal(Horario horario, LocalDate dataEvento, Local local);
}

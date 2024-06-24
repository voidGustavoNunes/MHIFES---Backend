package com.rfid.mhifes.repository.postgres;

import java.time.LocalDate;
import java.time.LocalTime;

import com.rfid.mhifes.model.postgres.Evento;
import com.rfid.mhifes.model.postgres.Horario;
import com.rfid.mhifes.model.postgres.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findAll(Pageable pageable);
    
	@Query("SELECT e FROM Evento e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :substring, '%'))")
	Page<Evento> findByNomeContaining(@Param("substring") String substring, Pageable pageable);
    
	@Query("SELECT e FROM Evento e WHERE e.dataEvento = TO_DATE(:dia, 'DD/MM/YYYY')")
    Page<Evento> findByDiaContaining(@Param("dia") String dia, Pageable pageable);
    
    @Query("SELECT e FROM Evento e JOIN e.horario h WHERE h.horaInicio = :time")
    Page<Evento> findByTimeInicio(@Param("time") LocalTime time, Pageable pageable);

    Optional<Evento> findByHorarioAndDataEventoAndLocal(Horario horario, LocalDate dataEvento, Local local);

    List<Evento> findByLocal(Local local);

    List<Evento> findByHorario(Horario horario);
}

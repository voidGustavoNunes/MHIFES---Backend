package com.rfid.mhifes.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Evento;
import com.rfid.mhifes.repository.postgres.EventoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class EventoService extends GenericServiceImpl<Evento, EventoRepository> {

    public EventoService(EventoRepository eventoRepository) {
        super(eventoRepository);
    }

    @Override
    public Evento atualizar(@NotNull @Positive Long id, @Valid @NotNull Evento evento) {
        return repository.findById(id)
                .map(eventoEditado -> {
                    eventoEditado.setNome(evento.getNome());
                    eventoEditado.setDataEvento(evento.getDataEvento());
                    eventoEditado.setDescricao(evento.getDescricao());
                    eventoEditado.setHorario(evento.getHorario());
                    // eventoEditado.setHorarioInicio(evento.getHorarioInicio());
                    // eventoEditado.setHorarioFim(evento.getHorarioFim());
                    eventoEditado.setLocal(evento.getLocal());
                    return repository.save(eventoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
    
	public Page<Evento> acharNome(String substring, Pageable pageable) {
		return repository.findByNomeContaining(substring, pageable);
	}
	public Page<Evento> acharDia(String substring, Pageable pageable) {
		return repository.findByDiaContaining(substring, pageable);
	}
	public Page<Evento> acharTimeInicio(LocalTime time, Pageable pageable) {
		return repository.findByTimeInicio(time, pageable);
	}
}

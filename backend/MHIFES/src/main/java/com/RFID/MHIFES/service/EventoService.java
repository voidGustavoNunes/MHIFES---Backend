package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Evento;
import com.rfid.mhifes.repository.EventoRepository;

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
                    eventoEditado.setHorarioInicio(evento.getHorarioInicio());
                    eventoEditado.setHorarioFim(evento.getHorarioFim());
                    eventoEditado.setLocal(evento.getLocal());
                    return repository.save(eventoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

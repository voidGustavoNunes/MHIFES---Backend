package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Evento;
import com.rfid.mhifes.repository.EventoRepository;

@Service
public class EventoService extends GenericServiceImpl<Evento, EventoRepository> {

    public EventoService(EventoRepository eventoRepository) {
        super(eventoRepository);
    }

    @Override
    public Evento atualizar(Long id, Evento evento) {
        return repository.findById(id)
                .map(eventoEditado -> {
                    eventoEditado.setDataEvento(evento.getDataEvento());
                    eventoEditado.setDescricao(evento.getDescricao());
                    eventoEditado.setHorarioInicio(evento.getHorarioInicio());
                    eventoEditado.setHorarioFim(evento.getHorarioFim());
                    eventoEditado.setLocal(evento.getLocal());
                    return eventoRepository.save(eventoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

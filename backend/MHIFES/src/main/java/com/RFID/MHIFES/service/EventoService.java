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
                    eventoEditado.setIntervaloData(evento.getIntervaloData());
                    eventoEditado.setLocal(evento.getLocal());
                    eventoEditado.setDescricao(evento.getDescricao());
                    return repository.save(eventoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id)); 
    }
}

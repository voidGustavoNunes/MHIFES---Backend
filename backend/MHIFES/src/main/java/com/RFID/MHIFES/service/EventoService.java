package com.RFID.MHIFES.service;

import org.springframework.stereotype.Service;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Evento;
import com.RFID.MHIFES.repository.EventoRepository;

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

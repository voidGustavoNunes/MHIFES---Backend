package com.RFID.MHIFES.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Coordenadoria;
import com.RFID.MHIFES.model.Evento;
import com.RFID.MHIFES.repository.CoordenadoriaRepository;
import com.RFID.MHIFES.repository.EventoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
@Service
public class EventoService {
    
    private EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(@NotNull @Positive Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Evento criar(@Valid @NotNull Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento atualizar(@NotNull @Positive Long id, @Valid @NotNull Evento evento) {
        return eventoRepository.findById(id)
                .map(eventoEditado -> {
                    eventoEditado.setIntervaloData(evento.getIntervaloData());
                    eventoEditado.setLocal(evento.getLocal());
                    eventoEditado.setDescricao(evento.getDescricao());
                    return eventoRepository.save(eventoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        eventoRepository.delete(eventoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }


}

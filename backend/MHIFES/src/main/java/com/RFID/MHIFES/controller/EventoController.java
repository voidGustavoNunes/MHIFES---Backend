package com.RFID.MHIFES.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Evento;
import com.RFID.MHIFES.service.EventoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
@RestController
@RequestMapping("/api/eventos")
public class EventoController {
    
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> listar() {
        return eventoService.listar();
    }

    @GetMapping("/{id}")
    public Evento buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return eventoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Evento criar(@RequestBody @Valid @NotNull Evento evento) {
        return eventoService.criar(evento);
    }

    @PutMapping("/{id}")
    public Evento atualizar(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull Evento evento) {
        return eventoService.atualizar(id, evento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        eventoService.excluir(id);
    }
}

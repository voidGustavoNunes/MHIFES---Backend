package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Evento;
import com.rfid.mhifes.service.EventoService;

@Validated
@RestController
@RequestMapping("/api/eventos")
public class EventoController extends GenericController<Evento> {

    public EventoController(EventoService eventoService) {
        super(eventoService);
    }
}

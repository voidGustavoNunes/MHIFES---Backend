package com.RFID.MHIFES.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Evento;
import com.RFID.MHIFES.service.EventoService;

@Validated
@RestController
@RequestMapping("/api/eventos")
public class EventoController extends GenericController<Evento> {

    public EventoController(EventoService eventoService) {
        super(eventoService);
    }
}

package com.RFID.MHIFES.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Coordenador;
import com.RFID.MHIFES.service.CoordenadorService;

@Validated
@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController extends GenericController<Coordenador> {

    public CoordenadorController(CoordenadorService coordenadorService) {
        super(coordenadorService);
    }
}

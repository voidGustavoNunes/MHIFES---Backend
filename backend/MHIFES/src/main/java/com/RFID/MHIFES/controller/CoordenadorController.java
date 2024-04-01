package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Coordenador;
import com.rfid.mhifes.service.CoordenadorService;

@Validated
@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController extends GenericController<Coordenador>{

    public CoordenadorController(CoordenadorService coordenadorService) {
        super(coordenadorService);
    }
}

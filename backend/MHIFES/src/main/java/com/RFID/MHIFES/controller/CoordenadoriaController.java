package com.RFID.MHIFES.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Coordenadoria;
import com.RFID.MHIFES.service.CoordenadoriaService;

@Validated
@RestController
@RequestMapping("/api/coordenadorias")
public class CoordenadoriaController extends GenericController<Coordenadoria> {

    public CoordenadoriaController(CoordenadoriaService coordenadoriaService) {
        super(coordenadoriaService);
    }
}

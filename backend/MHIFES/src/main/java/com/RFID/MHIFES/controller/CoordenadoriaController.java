package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Coordenadoria;
import com.rfid.mhifes.service.CoordenadoriaService;

@Validated
@RestController
@RequestMapping("/api/coordenadorias")
public class CoordenadoriaController extends GenericController<Coordenadoria> {

    public CoordenadoriaController(CoordenadoriaService coordenadoriaService) {
        super(coordenadoriaService);
    }
}

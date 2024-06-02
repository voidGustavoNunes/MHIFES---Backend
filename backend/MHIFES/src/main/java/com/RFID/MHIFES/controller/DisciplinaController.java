package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Disciplina;
import com.rfid.mhifes.service.DisciplinaService;

@Validated
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController extends GenericController<Disciplina> {

    public DisciplinaController(DisciplinaService disciplinaService) {
        super(disciplinaService);
    }
}

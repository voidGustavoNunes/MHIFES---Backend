package com.RFID.MHIFES.controller;


import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Disciplina;
import com.RFID.MHIFES.service.DisciplinaService;



@Validated
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController extends GenericController<Disciplina>{


    public DisciplinaController(DisciplinaService disciplinaService) {
        super(disciplinaService);
    }

}

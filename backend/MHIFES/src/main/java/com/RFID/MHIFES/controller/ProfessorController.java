package com.RFID.MHIFES.controller;


import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Professor;
import com.RFID.MHIFES.service.ProfessorService;



@Validated
@RestController
@RequestMapping("/api/professores")
public class ProfessorController extends GenericController<Professor>{


    public ProfessorController(ProfessorService professorService) {
        super(professorService);
    }


}

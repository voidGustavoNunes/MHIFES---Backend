package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Professor;
import com.rfid.mhifes.service.ProfessorService;

@Validated
@RestController
@RequestMapping("/api/professores")
public class ProfessorController extends GenericController<Professor>{

    public ProfessorController(ProfessorService professorService) {
        super(professorService);
    }
}

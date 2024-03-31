package com.RFID.MHIFES.controller;


import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Aluno;
import com.RFID.MHIFES.service.AlunoService;

@Validated
@RestController
@RequestMapping("/api/alunos")
public class AlunoController extends GenericController<Aluno>{

    public AlunoController(AlunoService alunoService) {
        super(alunoService);
    }
}

package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Aluno;
import com.rfid.mhifes.service.AlunoService;

@Validated
@RestController
@RequestMapping("/api/alunos")
public class AlunoController extends GenericController<Aluno>{

    public AlunoController(AlunoService alunoService) {
        super(alunoService);
    }
}

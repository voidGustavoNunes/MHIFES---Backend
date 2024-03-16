package com.RFID.MHIFES.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Professor;
import com.RFID.MHIFES.service.ProfessorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> listar() {
        return professorService.listar();
    }

    @GetMapping("/{id}")
    public Professor buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return professorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Professor criar(@RequestBody @Valid @NotNull Professor professor) {
        return professorService.criar(professor);
    }

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull Professor professor) {
        return professorService.atualizar(id, professor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        professorService.excluir(id);
    }
}

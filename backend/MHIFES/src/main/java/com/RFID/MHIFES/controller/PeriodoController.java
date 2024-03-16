package com.rfid.mhifes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Periodo;
import com.rfid.mhifes.service.PeriodoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/api/periodos")
public class PeriodoController {

    private final PeriodoService periodoService;

    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @GetMapping
    public List<Periodo> listar() {
        return periodoService.listar();
    }

    @GetMapping("/{id}")
    public Periodo buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return periodoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Periodo criar(@RequestBody @Valid @NotNull Periodo periodo) {
        return periodoService.criar(periodo);
    }

    @PutMapping("/{id}")
    public Periodo atualizar(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull Periodo periodo) {
        return periodoService.atualizar(id, periodo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        periodoService.excluir(id);
    }
}

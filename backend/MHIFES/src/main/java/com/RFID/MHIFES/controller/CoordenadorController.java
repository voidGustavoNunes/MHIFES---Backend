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

import com.rfid.mhifes.model.Coordenador;
import com.rfid.mhifes.service.CoordenadorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {

    private final CoordenadorService coordenadorService;

    public CoordenadorController(CoordenadorService coordenadorService) {
        this.coordenadorService = coordenadorService;
    }

    @GetMapping
    public List<Coordenador> listar() {
        return coordenadorService.listar();
    }

    @GetMapping("/{id}")
    public Coordenador buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return coordenadorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Coordenador criar(@RequestBody @Valid @NotNull Coordenador coordenador) {
        return coordenadorService.criar(coordenador);
    }

    @PutMapping("/{id}")
    public Coordenador atualizar(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull Coordenador coordenador) {
        return coordenadorService.atualizar(id, coordenador);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        coordenadorService.excluir(id);
    }
}

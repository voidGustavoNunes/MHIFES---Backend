package com.RFID.MHIFES.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Coordenadoria;
import com.RFID.MHIFES.service.CoordenadoriaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
@RestController
@RequestMapping("/api/coordenadoria")
public class CoordenadoriaController {
    
    private final CoordenadoriaService coordenadoriaService;

    public CoordenadorController(CoordenadoriaService coordenadoriaService) {
        this.coordenadoriaService = coordenadoriaService;
    }

    @GetMapping
    public List<Coordenadoria> listar() {
        return coordenadoriaService.listar();
    }

    @GetMapping("/{id}")
    public Coordenadoria buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return coordenadoriaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Coordenadoria criar(@RequestBody @Valid @NotNull Coordenadoria coordenadoria) {
        return coordenadoriaService.criar(coordenadoria);
    }

    @PutMapping("/{id}")
    public Coordenadoria atualizar(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull Coordenadoria coordenadoria) {
        return coordenadoriaService.atualizar(id, coordenadoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        coordenadoriaService.excluir(id);
    }
}

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

import com.RFID.MHIFES.model.Aluno;
import com.RFID.MHIFES.model.Local;
import com.RFID.MHIFES.service.AlunoService;
import com.RFID.MHIFES.service.LocalService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/local")
public class LocalController {
    
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping
    public List<Aluno> listar() {
        return localService.listar();
    }

    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return localService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Aluno criar(@RequestBody @Valid @NotNull Local local) {
        return localService.criar(local);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull Local local) {
        return localService.atualizar(id, local);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        localService.excluir(id);
    }
}

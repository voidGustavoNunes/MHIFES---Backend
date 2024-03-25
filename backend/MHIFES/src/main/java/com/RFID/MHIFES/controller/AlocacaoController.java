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

import com.RFID.MHIFES.model.Alocacao;
import com.RFID.MHIFES.service.AlocacaoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/alocacao")
public class AlocacaoController {
    
    
    private final AlocacaoService alocacaoService;

    public AlocacaoController(AlocacaoService alocacaoService) {
        this.alocacaoService = alocacaoService;
    }

    @GetMapping
    public List<Alocacao> listar() {
        return alocacaoService.listar();
    }

    @GetMapping("/{id}")
    public Alocacao buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return alocacaoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Alocacao criar(@RequestBody @Valid @NotNull Alocacao alocacao) {
        return alocacaoService.criar(alocacao);
    }

    @PutMapping("/{id}")
    public Alocacao atualizar(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull Alocacao alocacao) {
        return alocacaoService.atualizar(id, alocacao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        alocacaoService.excluir(id);
    }
}

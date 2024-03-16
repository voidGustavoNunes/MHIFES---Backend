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

import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.service.EquipamentoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @GetMapping
    public List<Equipamento> listar() {
        return equipamentoService.listar();
    }

    @GetMapping("/{id}")
    public Equipamento buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return equipamentoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Equipamento criar(@RequestBody @Valid @NotNull Equipamento equipamento) {
        return equipamentoService.criar(equipamento);
    }

    @PutMapping("/{id}")
    public Equipamento atualizar(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull Equipamento equipamento) {
        return equipamentoService.atualizar(id, equipamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable @NotNull @Positive Long id) {
        equipamentoService.excluir(id);
    }
}

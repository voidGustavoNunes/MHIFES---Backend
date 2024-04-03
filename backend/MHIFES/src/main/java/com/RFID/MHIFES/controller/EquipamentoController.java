package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.service.EquipamentoService;

@Validated
@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController extends GenericController<Equipamento> {

    public EquipamentoController(EquipamentoService equipamentoService) {
        super(equipamentoService);
    }
}

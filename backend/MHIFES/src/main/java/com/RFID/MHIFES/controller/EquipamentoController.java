package com.RFID.MHIFES.controller;


import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Equipamento;
import com.RFID.MHIFES.service.EquipamentoService;


@Validated
@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController extends GenericController<Equipamento>{


    public EquipamentoController(EquipamentoService equipamentoService) {
        super(equipamentoService);
    }

}

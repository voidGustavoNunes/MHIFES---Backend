package com.RFID.MHIFES.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Equipamento;
import com.RFID.MHIFES.model.Local;
import com.RFID.MHIFES.model.LocalDTO;
import com.RFID.MHIFES.model.LocalEquipamento;
import com.RFID.MHIFES.service.LocalService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping("/api/locais")
public class LocalController extends GenericController<Local> {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        super(localService);
        this.localService = localService;
    }

    @PostMapping("/criar")
    public Local criar(@RequestBody @Valid LocalDTO localDTO
            // @RequestBody @Valid List<LocalEquipamento> localEquipamentos
            ) {
        return localService.criar(localDTO);
    }
}

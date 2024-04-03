package com.rfid.mhifes.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.model.Local;
import com.rfid.mhifes.model.LocalEquipamento;
import com.rfid.mhifes.service.LocalService;

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

    @PostMapping("criar")
    public Local criar(@RequestBody @Valid Local local,
            @RequestBody @Valid List<LocalEquipamento> localEquipamentos) {
        return localService.criar(local, localEquipamentos);
    }
}

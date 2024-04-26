package com.rfid.mhifes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Alocacao;
import com.rfid.mhifes.model.Log;
import com.rfid.mhifes.service.AlocacaoService;
import com.rfid.mhifes.service.LogService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/alocacoes")
public class AlocacaoController extends GenericController<Alocacao> {

    private final LogService logService;

    public AlocacaoController(AlocacaoService alocacaoService, LogService logService) {
        super(alocacaoService);
        this.logService = logService;
    }

    @GetMapping("/log/{id}")
    public List<Log> buscarPorIdRegistro(@PathVariable @NotNull @Positive Long id) {
        return logService.buscarLogPorIdRegistro(id);
    }
}

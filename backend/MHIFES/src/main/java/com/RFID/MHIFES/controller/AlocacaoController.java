package com.RFID.MHIFES.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Alocacao;
import com.RFID.MHIFES.model.Log;
import com.RFID.MHIFES.service.AlocacaoService;
import com.RFID.MHIFES.service.LogService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@RestController
@RequestMapping("/api/alocacoes")
public class AlocacaoController extends GenericController<Alocacao> {

    private final AlocacaoService alocacaoService;

    private final LogService logService;

    public AlocacaoController(AlocacaoService alocacaoService, LogService logService) {
        super(alocacaoService);
        this.alocacaoService = alocacaoService;
        this.logService = logService;
    }

    @GetMapping("/log/{id}")
    public List<Log> buscarPorIdRegistro(@PathVariable @NotNull @Positive Long id) {
        return logService.buscarLogPorIdRegistro(id);
    }

    @GetMapping("/inativos")
    public List<Alocacao> listarInativos() {
        return alocacaoService.listarInativos();
    }
    
}

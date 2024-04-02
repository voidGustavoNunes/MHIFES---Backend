package com.rfid.mhifes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Alocacao;
import com.rfid.mhifes.service.AlocacaoService;

@RestController
@RequestMapping("/api/alocacoes")
public class AlocacaoController extends GenericController<Alocacao> {

    public AlocacaoController(AlocacaoService alocacaoService) {
        super(alocacaoService);
    }
}

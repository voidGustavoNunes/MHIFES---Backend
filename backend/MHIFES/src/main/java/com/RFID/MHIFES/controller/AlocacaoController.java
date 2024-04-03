package com.RFID.MHIFES.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Alocacao;
import com.RFID.MHIFES.service.AlocacaoService;

@RestController
@RequestMapping("/api/alocacoes")
public class AlocacaoController extends GenericController<Alocacao> {

    public AlocacaoController(AlocacaoService alocacaoService) {
        super(alocacaoService);
    }
}

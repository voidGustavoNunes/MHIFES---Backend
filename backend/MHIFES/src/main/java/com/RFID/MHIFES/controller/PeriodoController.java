package com.RFID.MHIFES.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Periodo;
import com.RFID.MHIFES.service.PeriodoService;

@Validated
@RestController
@RequestMapping("/api/periodos")
public class PeriodoController extends GenericController<Periodo> {

    public PeriodoController(PeriodoService periodoService) {
        super(periodoService);
    }
}

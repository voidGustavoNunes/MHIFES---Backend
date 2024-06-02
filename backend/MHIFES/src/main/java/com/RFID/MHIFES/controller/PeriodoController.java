package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Periodo;
import com.rfid.mhifes.service.PeriodoService;

@Validated
@RestController
@RequestMapping("/api/periodos")
public class PeriodoController extends GenericController<Periodo> {

    public PeriodoController(PeriodoService periodoService) {
        super(periodoService);
    }
}

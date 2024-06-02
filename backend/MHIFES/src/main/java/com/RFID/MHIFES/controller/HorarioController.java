package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Horario;
import com.rfid.mhifes.service.HorarioService;

@Validated
@RestController
@RequestMapping("/api/horarios")
public class HorarioController extends GenericController<Horario> {

    protected HorarioController(HorarioService horarioService) {
        super(horarioService);
    }

}

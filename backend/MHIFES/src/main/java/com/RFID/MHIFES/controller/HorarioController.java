package com.RFID.MHIFES.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Horario;
import com.RFID.MHIFES.service.HorarioService;

@Validated
@RestController
@RequestMapping("/api/horarios")
public class HorarioController extends GenericController<Horario> {

    protected HorarioController(HorarioService horarioService) {
        super(horarioService);
    }

}

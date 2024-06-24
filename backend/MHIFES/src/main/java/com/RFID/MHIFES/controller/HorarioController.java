package com.rfid.mhifes.controller;

import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Horario;
import com.rfid.mhifes.service.HorarioService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/horarios")
public class HorarioController extends GenericController<Horario> {

	private final HorarioService horarioService;

    protected HorarioController(HorarioService horarioService) {
        super(horarioService);
		this.horarioService = horarioService;
    }

	@GetMapping("/filter/inicio")
	public Page<Horario> acharTimeInicio(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String time) {
		Pageable pageable = PageRequest.of(page, size);
		LocalTime localTime = LocalTime.parse(time);
		return horarioService.acharTimeInicio(localTime, pageable);
	}
    
	@GetMapping("/filter/fim")
	public Page<Horario> acharTimeFim(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String time) {
		Pageable pageable = PageRequest.of(page, size);
		LocalTime localTime = LocalTime.parse(time);
		return horarioService.acharTimeFim(localTime, pageable);
	}

}

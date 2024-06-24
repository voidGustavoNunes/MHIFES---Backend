package com.rfid.mhifes.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Evento;
import com.rfid.mhifes.service.EventoService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/eventos")
public class EventoController extends GenericController<Evento> {
    
	private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        super(eventoService);
		this.eventoService = eventoService;
    }
    
	@GetMapping("/filter/nome")
	public Page<Evento> acharNome(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return eventoService.acharNome(substring, pageable);
	}
    
	@GetMapping("/filter/dia")
	public Page<Evento> acharDia(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return eventoService.acharDia(substring, pageable);
	}
    
	@GetMapping("/filter/inicio")
	public Page<Evento> acharTimeInicio(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String time) {
		Pageable pageable = PageRequest.of(page, size);
		LocalTime localTime = LocalTime.parse(time);
		return eventoService.acharTimeInicio(localTime, pageable);
	}
}

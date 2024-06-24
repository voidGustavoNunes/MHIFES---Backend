package com.rfid.mhifes.controller;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Periodo;
import com.rfid.mhifes.service.PeriodoService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/periodos")
public class PeriodoController extends GenericController<Periodo> {
    
	private final PeriodoService periodoService;

    public PeriodoController(PeriodoService periodoService) {
        super(periodoService);
		this.periodoService = periodoService;
    }

	@GetMapping("/filter/dia")
	public Page<Periodo> acharDia(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return periodoService.acharDia(substring, pageable);
	}
    
	@GetMapping("/filter/ano")
	public Page<Periodo> acharAno(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull Integer ano) {
		Pageable pageable = PageRequest.of(page, size);

        Year year = Year.of(ano);
		return periodoService.acharAno(year, pageable);
	}
}

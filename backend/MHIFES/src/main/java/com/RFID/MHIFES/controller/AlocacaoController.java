package com.rfid.mhifes.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Log;
import com.rfid.mhifes.service.AlocacaoService;
import com.rfid.mhifes.service.LogService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

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

	@GetMapping("/ativos")
	public Page<Alocacao> listarAtivos(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return alocacaoService.listarAtivos(pageable);
	}

	@GetMapping("/inativos")
	public Page<Alocacao> listarInativos(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return alocacaoService.listarInativos(pageable);
	}

	// FILTER ALOCAÇÃO ATIVO
	@GetMapping("/ativos/filter/professor")
	public Page<Alocacao> acharProfessorAtivo(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return alocacaoService.acharProfessorAtivo(substring, pageable);
	}
	@GetMapping("/ativos/filter/local")
	public Page<Alocacao> acharLocalAtivo(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return alocacaoService.acharLocalAtivo(substring, pageable);
	}
	@GetMapping("/ativos/filter/disciplina")
	public Page<Alocacao> acharDisciplinaAtivo(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println("\n"+substring+"\n");
		return alocacaoService.acharDisciplinaAtivo(substring, pageable);
	}
	@GetMapping("/ativos/filter/horario")
	public Page<Alocacao> acharHorarioAtivo(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String time) {
		Pageable pageable = PageRequest.of(page, size);
		
        // String[] parts = time.split(":");
        // int hour = Integer.parseInt(parts[0]);
        // int minute = Integer.parseInt(parts[1]);
        // LocalTime localTime = LocalTime.of(hour, minute);
		LocalTime localTime = LocalTime.parse(time);
		return alocacaoService.acharHorarioAtivo(localTime, pageable);
	}

	// FILTER ALOCAÇÃO INATIVO
	@GetMapping("/inativos/filter/professor")
	public Page<Alocacao> acharProfessorInativo(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return alocacaoService.acharProfessorInativo(substring, pageable);
	}
	@GetMapping("/inativos/filter/local")
	public Page<Alocacao> acharLocalInativo(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return alocacaoService.acharLocalInativo(substring, pageable);
	}
	@GetMapping("/inativos/filter/disciplina")
	public Page<Alocacao> acharDisciplinaInativo(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return alocacaoService.acharDisciplinaInativo(substring, pageable);
	}
	@GetMapping("/inativos/filter/horario")
	public Page<Alocacao> acharHorarioInativo(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String time) {
		Pageable pageable = PageRequest.of(page, size);
		
        // String[] parts = time.split(":");
        // int hour = Integer.parseInt(parts[0]);
        // int minute = Integer.parseInt(parts[1]);
        // LocalTime localTime = LocalTime.of(hour, minute);
		LocalTime localTime = LocalTime.parse(time);
		return alocacaoService.acharHorarioInativo(localTime, pageable);
	}

}

package com.rfid.mhifes.controller;

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
		return alocacaoService.listarInativos(pageable);
	}

	@GetMapping("/inativos")
	public Page<Alocacao> listarInativos(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return alocacaoService.listarInativos(pageable);
	}

}

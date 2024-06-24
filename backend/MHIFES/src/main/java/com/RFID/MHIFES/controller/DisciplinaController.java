package com.rfid.mhifes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Aluno;
import com.rfid.mhifes.model.postgres.Disciplina;
import com.rfid.mhifes.service.DisciplinaService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController extends GenericController<Disciplina> {
    
	private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        super(disciplinaService);
		this.disciplinaService = disciplinaService;
    }
    
	@GetMapping("/filter/nome")
	public Page<Disciplina> acharNome(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return disciplinaService.acharNome(substring, pageable);
	}
    
	@GetMapping("/filter/sigla")
	public Page<Disciplina> acharMatricula(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return disciplinaService.acharSigla(substring, pageable);
	}
}

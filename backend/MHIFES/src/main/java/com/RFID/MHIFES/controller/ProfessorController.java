package com.rfid.mhifes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Professor;
import com.rfid.mhifes.service.ProfessorService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/professores")
public class ProfessorController extends GenericController<Professor> {
    
	private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        super(professorService);
		this.professorService = professorService;
    }
    
	@GetMapping("/filter/nome")
	public Page<Professor> acharNome(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return professorService.acharNome(substring, pageable);
	}
    
	@GetMapping("/filter/sigla")
	public Page<Professor> acharSigla(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return professorService.acharSigla(substring, pageable);
	}
    
	@GetMapping("/filter/matricula")
	public Page<Professor> acharMatricula(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return professorService.acharMatricula(substring, pageable);
	}
}

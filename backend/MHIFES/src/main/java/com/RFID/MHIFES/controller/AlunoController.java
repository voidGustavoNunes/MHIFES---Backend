package com.rfid.mhifes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Aluno;
import com.rfid.mhifes.service.AlocacaoService;
import com.rfid.mhifes.service.AlunoService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/alunos")
public class AlunoController extends GenericController<Aluno> {

	private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        super(alunoService);
		this.alunoService = alunoService;
    }
    
	@GetMapping("/filter/nome")
	public Page<Aluno> acharNome(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return alunoService.acharNome(substring, pageable);
	}
    
	@GetMapping("/filter/matricula")
	public Page<Aluno> acharMatricula(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return alunoService.acharMatricula(substring, pageable);
	}
}

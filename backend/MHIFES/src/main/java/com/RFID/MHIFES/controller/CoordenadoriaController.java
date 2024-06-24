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
import com.rfid.mhifes.model.postgres.Coordenadoria;
import com.rfid.mhifes.service.AlunoService;
import com.rfid.mhifes.service.CoordenadorService;
import com.rfid.mhifes.service.CoordenadoriaService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/coordenadorias")
public class CoordenadoriaController extends GenericController<Coordenadoria> {

	private final CoordenadoriaService coordenadoriaService;

    public CoordenadoriaController(CoordenadoriaService coordenadoriaService) {
        super(coordenadoriaService);
		this.coordenadoriaService = coordenadoriaService;
    }
    
	@GetMapping("/filter/nome")
	public Page<Coordenadoria> acharNome(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return coordenadoriaService.acharNome(substring, pageable);
	}
}

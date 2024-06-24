package com.rfid.mhifes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Local;
import com.rfid.mhifes.service.LocalService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping("/api/locais")
public class LocalController extends GenericController<Local> {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        super(localService);
        this.localService = localService;
    }

    @Override
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Local criar(@RequestBody @Valid @NotNull Local local) {
        return localService.criar(local);
    }
    
	@GetMapping("/filter/nome")
	public Page<Local> acharNome(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return localService.acharNome(substring, pageable);
	}
    
	@GetMapping("/filter/capacidade")
	public Page<Local> acharCapacidade(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull Integer capa) {
		Pageable pageable = PageRequest.of(page, size);
		return localService.acharCapacidade(capa, pageable);
	}
}

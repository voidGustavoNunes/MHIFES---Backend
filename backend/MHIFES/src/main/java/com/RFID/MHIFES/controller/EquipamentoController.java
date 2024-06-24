package com.rfid.mhifes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Equipamento;
import com.rfid.mhifes.service.EquipamentoService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController extends GenericController<Equipamento> {
    
	private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        super(equipamentoService);
		this.equipamentoService = equipamentoService;
    }

	@GetMapping("/filter/nome")
	public Page<Equipamento> acharNome(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size, @RequestParam @NotNull String substring) {
		Pageable pageable = PageRequest.of(page, size);
		return equipamentoService.acharNome(substring, pageable);
	}
}

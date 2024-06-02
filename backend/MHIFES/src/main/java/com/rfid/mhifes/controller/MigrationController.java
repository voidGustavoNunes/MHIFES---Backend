package com.rfid.mhifes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.mysql.AlocacaoMySQL;
import com.rfid.mhifes.model.mysql.ProfessorMySQL;
import com.rfid.mhifes.repository.mysql.AlocacaoMySQLRepository;
import com.rfid.mhifes.service.DataMigrationService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("api/migrate")
public class MigrationController {

	@Autowired
	private AlocacaoMySQLRepository mysqlRepository;

	@Autowired
	private DataMigrationService dataMigrationService;

	@GetMapping
	public ResponseEntity<String> migrate() {
		dataMigrationService.migrateData();
		return ResponseEntity.ok("Data migration completed.");
	}

	@GetMapping("/alocacoes")
	public Page<AlocacaoMySQL> listarAlocacoes(@RequestParam @NotNull @PositiveOrZero Integer page,
			@RequestParam @NotNull @Positive Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return dataMigrationService.listarAlocacoes(pageable);
	}

	@GetMapping("/professores")
	public List<ProfessorMySQL> listarProfessores() {
		return dataMigrationService.listarProfessores();
	}
}

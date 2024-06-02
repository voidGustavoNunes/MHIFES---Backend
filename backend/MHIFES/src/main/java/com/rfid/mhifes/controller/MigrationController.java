package com.rfid.mhifes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.mysql.AlocacaoMySQL;
import com.rfid.mhifes.service.DataMigrationService;

@RestController
@RequestMapping("api/migrate")
public class MigrationController {

	@Autowired
	private DataMigrationService dataMigrationService;

	@PostMapping
	public ResponseEntity<?> migrateAlocacoes(@RequestBody List<AlocacaoMySQL> mysqlAlocacoes) {
		dataMigrationService.migrateData(mysqlAlocacoes);
		return ResponseEntity.ok().build();
	}
}

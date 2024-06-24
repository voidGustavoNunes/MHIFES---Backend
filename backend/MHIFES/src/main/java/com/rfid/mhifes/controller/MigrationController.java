package com.rfid.mhifes.controller;

import com.rfid.mhifes.model.mysql.AlocacaoMySQL;
import com.rfid.mhifes.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/migrate")
public class MigrationController {

    @Autowired
    private MigrationService migrationService;

    @PostMapping
    public ResponseEntity<?> migrateAlocacoes(@RequestBody List<AlocacaoMySQL> mysqlAlocacoes) {
        migrationService.migrateData(mysqlAlocacoes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("alocacoes-mysql")
    public List<AlocacaoMySQL> listaMysql() {
        return migrationService.listarAlocacaoMySQL();
    }
}

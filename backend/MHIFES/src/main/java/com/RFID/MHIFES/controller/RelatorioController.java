package com.rfid.mhifes.controller;

import com.rfid.mhifes.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final ReportService reportService;

    public RelatorioController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/disciplinas_turma/{ano}/{semestre}")
    public ResponseEntity<Map<String, String>> gerarRelatorioDisciplinaTurma(@PathVariable Integer ano,
                                                                             @PathVariable Long semestre) {
        // return reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
        try {
            String report = reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
            Map<String, String> response = new HashMap<>();
            response.put("message", report);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro ao gerar relatório");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/horarios_turma/{ano}/{semestre}")
    public ResponseEntity<Map<String, String>> gerarRelatorioHorarioTurma(@PathVariable("ano") Integer ano,
                                                                          @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorTurma(null, ano, semestre);
        try {
            String report = reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
            Map<String, String> response = new HashMap<>();
            response.put("message", report);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro ao gerar relatório");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/horarios_turma/{turma}/{ano}/{semestre}")
    public ResponseEntity<Map<String, String>> gerarRelatorioHorarioTurma(@PathVariable("turma") String turma,
                                                                          @PathVariable("ano") Integer ano, @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorTurma(turma, ano, semestre);
        try {
            String report = reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
            Map<String, String> response = new HashMap<>();
            response.put("message", report);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro ao gerar relatório");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/horarios_professor/{ano}/{semestre}")
    public ResponseEntity<Map<String, String>> gerarRelatorioHorarioPorProfessor(@PathVariable("ano") Integer ano,
                                                                                 @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorProfessor(null, null, ano, semestre);
        try {
            String report = reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
            Map<String, String> response = new HashMap<>();
            response.put("message", report);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro ao gerar relatório");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/horarios_professor/{coordenadoria_id}/{ano}/{semestre}")
    public ResponseEntity<Map<String, String>> gerarRelatorioHorarioPorProfessor(
            @PathVariable("coordenadoria_id") Integer coordenariaId, @PathVariable("ano") Integer ano,
            @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorProfessor(coordenariaId, null, ano, semestre);
        try {
            String report = reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
            Map<String, String> response = new HashMap<>();
            response.put("message", report);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro ao gerar relatório");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/horarios_professor/{coordenadoria_id}/{professor_id}/{ano}/{semestre}")
    public ResponseEntity<Map<String, String>> gerarRelatorioHorarioPorProfessor(
            @PathVariable("coordenadoria_id") Integer coordenariaId, @PathVariable("professor_id") Integer professoId,
            @PathVariable("ano") Integer ano, @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorProfessor(coordenariaId, professoId, ano, semestre);
        try {
            String report = reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
            Map<String, String> response = new HashMap<>();
            response.put("message", report);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro ao gerar relatório");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

package com.rfid.mhifes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.service.ReportService;

@Validated
@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final ReportService reportService;

    public HomeController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/relatorio/disciplinas_turma/{ano}/{semestre}")
    public ResponseEntity<String> gerarRelatorioDisciplinaTurma(@PathVariable Integer ano, @PathVariable Long semestre) {
        // return reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
        try {
            String report = reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao gerar o relatório.");
        }
    }

    @GetMapping("/relatorio/horarios_turma/{ano}/{semestre}")
    public ResponseEntity<String> gerarRelatorioHorarioTurma(@PathVariable("ano") Integer ano,
            @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorTurma(null, ano, semestre);
        try {
            String report = reportService.gerarRelatorioHorarioPorTurma(null, ano, semestre);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao gerar o relatório.");
        }
    }

    @GetMapping("/relatorio/horarios_turma/{turma}/{ano}/{semestre}")
    public ResponseEntity<String> gerarRelatorioHorarioTurma(@PathVariable("turma") String turma,
            @PathVariable("ano") Integer ano, @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorTurma(turma, ano, semestre);
        try {
            String report = reportService.gerarRelatorioHorarioPorTurma(turma, ano, semestre);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao gerar o relatório.");
        }
    }

    @GetMapping("/relatorio/horarios_professor/{ano}/{semestre}")
    public ResponseEntity<String> gerarRelatorioHorarioPorProfessor(@PathVariable("ano") Integer ano,
            @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorProfessor(null, null, ano, semestre);
        try {
            String report = reportService.gerarRelatorioHorarioPorProfessor(null, null, ano, semestre);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao gerar o relatório.");
        }
    }

    @GetMapping("/relatorio/horarios_professor/{coordenadoria_id}/{ano}/{semestre}")
    public ResponseEntity<String> gerarRelatorioHorarioPorProfessor(@PathVariable("coordenadoria_id") Integer coordenariaId,
            @PathVariable("ano") Integer ano, @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorProfessor(coordenariaId, null, ano, semestre);
        try {
            String report = reportService.gerarRelatorioHorarioPorProfessor(coordenariaId, null, ano, semestre);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao gerar o relatório.");
        }
    }

    @GetMapping("/relatorio/horarios_professor/{coordenadoria_id}/{professor_id}/{ano}/{semestre}")
    public ResponseEntity<String> gerarRelatorioHorarioPorProfessor(@PathVariable("coordenadoria_id") Integer coordenariaId,
            @PathVariable("professor_id") Integer professoId, @PathVariable("ano") Integer ano,
            @PathVariable("semestre") Long semestre) {
        // return reportService.gerarRelatorioHorarioPorProfessor(coordenariaId, professoId, ano, semestre);
        try {
            String report = reportService.gerarRelatorioHorarioPorProfessor(coordenariaId, professoId, ano, semestre);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao gerar o relatório.");
        }
    }
}

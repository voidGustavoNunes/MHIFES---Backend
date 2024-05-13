package com.rfid.mhifes.controller;

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
    public String gerarRelatorioDisciplinaTurma(@PathVariable Integer ano, @PathVariable Long semestre) {
        return reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
    }

    @GetMapping("/relatorio/horarios_turma/{ano}/{semestre}")
    public String gerarRelatorioHorarioTurma(@PathVariable("ano") Integer ano,
            @PathVariable("semestre") Long semestre) {
        return reportService.gerarRelatorioHorarioPorTurma(null, ano, semestre);
    }

    @GetMapping("/relatorio/horarios_turma/{turma}/{ano}/{semestre}")
    public String gerarRelatorioHorarioTurma(@PathVariable("turma") String turma,
            @PathVariable("ano") Integer ano, @PathVariable("semestre") Long semestre) {
        return reportService.gerarRelatorioHorarioPorTurma(turma, ano, semestre);
    }

    @GetMapping("/relatorio/horarios_professor/{ano}/{semestre}")
    public String gerarRelatorioHorarioPorProfessor(@PathVariable("ano") Integer ano,
            @PathVariable("semestre") Long semestre) {
        return reportService.gerarRelatorioHorarioPorProfessor(null, null, ano, semestre);
    }

    @GetMapping("/relatorio/horarios_professor/{coordenadoria_id}/{ano}/{semestre}")
    public String gerarRelatorioHorarioPorProfessor(@PathVariable("coordenadoria_id") Integer coordenariaId,
            @PathVariable("ano") Integer ano, @PathVariable("semestre") Long semestre) {
        return reportService.gerarRelatorioHorarioPorProfessor(coordenariaId, null, ano, semestre);
    }

    @GetMapping("/relatorio/horarios_professor/{coordenadoria_id}/{professor_id}/{ano}/{semestre}")
    public String gerarRelatorioHorarioPorProfessor(@PathVariable("coordenadoria_id") Integer coordenariaId,
            @PathVariable("professor_id") Integer professoId, @PathVariable("ano") Integer ano,
            @PathVariable("semestre") Long semestre) {
        return reportService.gerarRelatorioHorarioPorProfessor(coordenariaId, professoId, ano, semestre);
    }
}

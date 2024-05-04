package com.rfid.mhifes.controller;

import java.io.FileNotFoundException;
import java.time.Year;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@Validated
@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final ReportService reportService;

    public HomeController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/relatorio/disciplinas_turma/{ano}/{semestre}")
    public String gerarRelatorioDisciplinaTurma(@PathVariable Year ano, @PathVariable Long semestre) throws FileNotFoundException, JRException {
        return reportService.gerarRelatorioDisciplinaTurma(ano, semestre);
    }
}

package com.rfid.mhifes.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.model.Alocacao;
import com.rfid.mhifes.repository.AlocacaoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Validated
@Service
public class ReportService {

    private final AlocacaoRepository alocacaoRepository;

    public ReportService(AlocacaoRepository alocacaoRepository) {
        this.alocacaoRepository = alocacaoRepository;
    }

    public String gerarRelatorioDisciplinaTurma(Year ano, Long semestre) throws FileNotFoundException, JRException {

        String path = System.getProperty("user.home") + File.separator + "Downloads";
        
        List<Alocacao> alocacoes = null;

        // Buscando dados
        if (ano != null && semestre != null) {
            alocacoes = alocacaoRepository.findByAnoAndSemestre(ano, semestre);
        } else if (ano != null) {
            alocacoes = alocacaoRepository.findByAno(ano);
        } else if (semestre != null) {
            alocacoes = alocacaoRepository.findBySemestre(semestre);
        } else {
            alocacoes = alocacaoRepository.findAll();
        }

        // Carregando arquivo e compilando relatório
        InputStream inputStream = getClass().getResourceAsStream("/relatorio_disciplinas_turma.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(alocacoes);
        Map<String, Object> parameters = new HashMap<>();
        // Caso algum parâmetro seja necessário
        // parameters.put("createdBy", "MHIFES");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Relatório de Disciplinas por Turma.pdf");
        return "Relatório gerado com sucesso em: " + path;
    }

}

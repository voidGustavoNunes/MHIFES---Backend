package com.RFID.MHIFES.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Validated
@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    public String gerarRelatorioDisciplinaTurma(Integer ano, Long semestre) {

        try (Connection conn = dataSource.getConnection()) {

            String path = System.getProperty("user.home") + File.separator + "Downloads";

            JasperDesign jasperDesign = JRXmlLoader
                    .load(getClass().getResourceAsStream("/reports/relatorio_disciplinas_turma.jrxml"));

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Adicionar parâmetros ao relatório
            Map<String, Object> params = new HashMap<>();
            params.put("XANO", ano);
            params.put("XSEMESTRE", semestre);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Relatório de Disciplinas por Turma.pdf");
            return "Relatório gerado com sucesso em: " + path;
        } catch (JRException e) {
            return "Erro ao gerar relatório";
        } catch (SQLException e) {
            return "Erro ao conectar ao banco de dados";
        }
    }

    public String gerarRelatorioHorarioPorTurma(String turma,
            Integer ano, Long semestre) {

        try (Connection conn = dataSource.getConnection()) {

            String path = System.getProperty("user.home") + File.separator + "Downloads";

            JasperDesign jasperDesign = JRXmlLoader
                    .load(getClass().getResourceAsStream("/reports/relatorio_horarios_turma.jrxml"));

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Adicionar parâmetros ao relatório
            Map<String, Object> params = new HashMap<>();
            params.put("XTURMA", turma);
            params.put("XANO", ano);
            params.put("XSEMESTRE", semestre);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Relatório de Horario por Turma.pdf");
            return "Relatório gerado com sucesso em: " + path;
        } catch (JRException e) {
            return "Erro ao gerar relatório";
        } catch (SQLException e) {
            return "Erro ao conectar ao banco de dados";
        }
    }

    public String gerarRelatorioHorarioPorProfessor(Integer coordenadoriaId, Integer professorId,
            Integer ano, Long semestre) {

        try (Connection conn = dataSource.getConnection()) {

            String path = System.getProperty("user.home") + File.separator + "Downloads";

            JasperDesign jasperDesign = JRXmlLoader
                    .load(getClass().getResourceAsStream("/reports/relatorio_horarios_professor.jrxml"));

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Adicionar parâmetros ao relatório
            Map<String, Object> params = new HashMap<>();
            params.put("XCOORDENADORIA_ID", coordenadoriaId);
            params.put("XPROFESSOR_ID", professorId);
            params.put("XANO", ano);
            params.put("XSEMESTRE", semestre);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Relatório de Horario por Professor.pdf");
            return "Relatório gerado com sucesso em: " + path;
        } catch (JRException e) {
            return "Erro ao gerar relatório";
        } catch (SQLException e) {
            return "Erro ao conectar ao banco de dados";
        }
    }

}

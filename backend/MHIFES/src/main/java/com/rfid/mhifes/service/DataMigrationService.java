package com.rfid.mhifes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rfid.mhifes.model.mysql.AlocacaoMySQL;
import com.rfid.mhifes.model.mysql.ProfessorMySQL;
import com.rfid.mhifes.repository.mysql.AlocacaoMySQLRepository;
import com.rfid.mhifes.repository.mysql.ProfessoresMySQLRepository;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;

@Service
public class DataMigrationService {

	@Autowired
	private AlocacaoMySQLRepository alocacaoMySQLRepository;

	@Autowired
	private AlocacaoRepository alocacaoRepository;

	@Autowired
	private ProfessoresMySQLRepository professoresMySQLRepository;

	public void migrateData() {
		List<AlocacaoMySQL> mysqlAlocacoes = alocacaoMySQLRepository.findAll();

		// for (AlocacaoMySQL mysqlAlocacao : mysqlAlocacoes) {
		// Alocacao alocacao = new Alocacao();
		// // Transformação dos dados conforme necessário
		// alocacao.setHorario(null); // definir conforme necessário
		// alocacao.setTurma(null); // definir conforme necessário
		// alocacao.setDataAula(null); // definir conforme necessário
		// alocacao.setDiaSemana(null); // definir conforme necessário
		// alocacao.setLocal(null); // definir conforme necessário
		// alocacao.setPeriodoDisciplina(null); // definir conforme necessário
		// alocacao.setProfessor(null); // definir conforme necessário
		// alocacao.setStatus(Status.ATIVO);
		// postgresRepository.save(alocacao);
		// }
	}

	public List<ProfessorMySQL> listarProfessores() {
		return professoresMySQLRepository.findAll();

	}

	public Page<AlocacaoMySQL> listarAlocacoes(Pageable pageable) {
		return alocacaoMySQLRepository.findAll(pageable);
	}
}

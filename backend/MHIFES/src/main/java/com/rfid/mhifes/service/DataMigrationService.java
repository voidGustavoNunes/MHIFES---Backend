package com.rfid.mhifes.service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rfid.mhifes.enums.Status;
import com.rfid.mhifes.model.mysql.AlocacaoMySQL;
import com.rfid.mhifes.model.mysql.AulaMySQL;
import com.rfid.mhifes.model.mysql.DisciplinaMySQL;
import com.rfid.mhifes.model.mysql.LabelMySQL;
import com.rfid.mhifes.model.mysql.ProfessorMySQL;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Disciplina;
import com.rfid.mhifes.model.postgres.Horario;
import com.rfid.mhifes.model.postgres.Periodo;
import com.rfid.mhifes.model.postgres.PeriodoDisciplina;
import com.rfid.mhifes.model.postgres.Professor;
import com.rfid.mhifes.repository.mysql.AlocacaoMySQLRepository;
import com.rfid.mhifes.repository.mysql.LabelMySQLRepository;
import com.rfid.mhifes.repository.mysql.ProfessoresMySQLRepository;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;
import com.rfid.mhifes.repository.postgres.CoordenadoriaRepository;
import com.rfid.mhifes.repository.postgres.DisciplinaRepository;
import com.rfid.mhifes.repository.postgres.HorarioRepository;
import com.rfid.mhifes.repository.postgres.PeriodoDisciplinaRepository;
import com.rfid.mhifes.repository.postgres.PeriodoRepository;
import com.rfid.mhifes.repository.postgres.ProfessorRepository;

@Service
public class DataMigrationService {

	@Autowired
	private AlocacaoMySQLRepository alocacaoMySQLRepository;

	@Autowired
	private AlocacaoRepository alocacaoRepository;

	@Autowired
	private ProfessoresMySQLRepository professoresMySQLRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private CoordenadoriaRepository coordenadoriaRepository;

	@Autowired
	private PeriodoRepository periodoRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private PeriodoDisciplinaRepository periodoDisciplinaRepository;

	@Autowired
	private HorarioRepository horarioRepository;

	@Autowired
	private LabelMySQLRepository labelMySQLRepository;

	public void migrateData(List<AlocacaoMySQL> mysqlAlocacoes) {

		for (AlocacaoMySQL mysqlAlocacao : mysqlAlocacoes) {

			// Verifica e cadastra o Professor1
			Professor professor = verificarEAtualizarProfessor(mysqlAlocacao.getProfessor1());

			// Verifica e cadastra o Periodo, Disciplina e PeriodoDisciplina
			PeriodoDisciplina periodoDisciplina = verificarEAtualizarPeriodoDisciplina(mysqlAlocacao.getAno(), Long.valueOf(mysqlAlocacao.getSemestre()), mysqlAlocacao.getDisciplinaMySQL());

			for (AulaMySQL aulaMySQL : mysqlAlocacao.getAulas()) {

				Horario horario = verificarECriarHorario(aulaMySQL);

				// Gerar alocações para cada data de aula ao longo do período
				LocalDate dataAtual = periodoDisciplina.getPeriodo().getDataInicio();
				while (!dataAtual.isAfter(periodoDisciplina.getPeriodo().getDataFim())) {
					if (dataAtual.getDayOfWeek().getValue() == aulaMySQL.getDia() + 1) {
						// Verifica se já existe uma alocação ativa para esta data, horário e período disciplina
						if (!alocacaoRepository.existsByDataAulaAndHorarioAndPeriodoDisciplinaAndStatus(dataAtual,
								horario, periodoDisciplina, Status.ATIVO)) {
							Alocacao alocacao = new Alocacao();
							// Verifica e cadastra o Horário
							alocacao.setHorario(horario);
							alocacao.setTurma(aulaMySQL.getOferta().getTurma().getNome());
							alocacao.setDataAula(dataAtual);
							alocacao.setDiaSemana(aulaMySQL.getDia() + 1);
							alocacao.setLocal(null);
							alocacao.setPeriodoDisciplina(periodoDisciplina);
							alocacao.setProfessor(professor);
							alocacao.setStatus(Status.ATIVO);
							alocacaoRepository.save(alocacao);
						}
					}
					dataAtual = dataAtual.plusDays(1);
				}
			}
		}
	}

	public Professor verificarEAtualizarProfessor(ProfessorMySQL professorMySQL) {
		// Verifica se o professor existe no banco de dados pelo campo matricula
		Professor professor = professorRepository.findByMatricula(professorMySQL.getMatricula());

		// Se o professor não existir, salva o novo professor
		if (professor == null) {
			professor = new Professor(professorMySQL.getNome(), professorMySQL.getMatricula(),
					gerarSigla(professorMySQL.getNome()));
			professor = professorRepository.save(professor);
		}

		return professor;
	}

	private String gerarSigla(String nome) {
		String[] nomes = nome.split(" ");
		StringBuilder siglaBuilder = new StringBuilder();
		siglaBuilder.append(nomes[0]); // Adiciona o primeiro nome
		for (int i = 1; i < nomes.length; i++) {
			siglaBuilder.append(nomes[i].charAt(0)); // Adiciona a primeira letra dos nomes consecutivos
		}
		return siglaBuilder.toString().toUpperCase();
	}

	private PeriodoDisciplina verificarEAtualizarPeriodoDisciplina(Year ano, Long semestre,
			DisciplinaMySQL disciplinaMySQL) {

		Periodo periodo = verificarECriarPeriodo(ano, semestre);
		Disciplina disciplina = verificarECriarDisciplina(disciplinaMySQL);

		Optional<PeriodoDisciplina> periodoDisciplinaExistente = periodoDisciplinaRepository
				.findByPeriodoAndDisciplina(periodo, disciplina);

		if (periodoDisciplinaExistente.isPresent()) {
			return periodoDisciplinaExistente.get();
		} else {
			PeriodoDisciplina periodoDisciplina = new PeriodoDisciplina();
			periodoDisciplina.setPeriodo(periodo);
			periodoDisciplina.setDisciplina(disciplina);
			return periodoDisciplinaRepository.save(periodoDisciplina);
		}
	}

	private Periodo verificarECriarPeriodo(Year ano, Long semestre) {
		Optional<Periodo> periodoExistente = periodoRepository.findByAnoAndSemestre(ano, semestre);

		if (periodoExistente.isPresent()) {
			return periodoExistente.get();
		} else {
			LocalDate dataInicio = LocalDate.of(ano.getValue(), (semestre == 1) ? 1 : 7, 1);
			LocalDate dataFim = LocalDate.of(ano.getValue(), (semestre == 1) ? 6 : 12, 30);
			Periodo periodo = new Periodo(ano, semestre, dataInicio, dataFim);
			return periodoRepository.save(periodo);
		}
	}

	private Disciplina verificarECriarDisciplina(DisciplinaMySQL disciplinaMySQL) {
		Optional<Disciplina> disciplinaExistente = disciplinaRepository.findByNomeAndSigla(disciplinaMySQL.getNome(),
				disciplinaMySQL.getSigla());

		if (disciplinaExistente.isPresent()) {
			return disciplinaExistente.get();
		} else {
			Disciplina disciplina = new Disciplina(disciplinaMySQL.getNome(), disciplinaMySQL.getSigla());
			return disciplinaRepository.save(disciplina);
		}
	}

	private Horario verificarECriarHorario(AulaMySQL aulaMySQL) {

		// Por enquanto dividido por 6 porque as tabelas do samha são malucas
		LabelMySQL labelMySQL = labelMySQLRepository.findByNumeroAndTurno(aulaMySQL.getNumero(),
				aulaMySQL.getTurno() / 6);

		Optional<Horario> horarioExistente = horarioRepository.findByHoraInicioAndHoraFim(labelMySQL.getInicio(),
				labelMySQL.getFim());

		if (horarioExistente.isPresent()) {
			return horarioExistente.get();
		} else {
			Horario horario = new Horario();
			horario.setHoraInicio(labelMySQL.getInicio());
			horario.setHoraFim(labelMySQL.getFim());
			return horarioRepository.save(horario);
		}
	}
	
	public List<AlocacaoMySQL> listarAlocacaoMySQL() {
		return alocacaoMySQLRepository.findAll();
	}
}

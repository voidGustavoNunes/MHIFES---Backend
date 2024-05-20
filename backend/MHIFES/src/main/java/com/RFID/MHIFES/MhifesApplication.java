package com.RFID.MHIFES;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.RFID.MHIFES.model.Alocacao;
import com.RFID.MHIFES.model.Aluno;
import com.RFID.MHIFES.model.Coordenadoria;
import com.RFID.MHIFES.model.Disciplina;
import com.RFID.MHIFES.model.Equipamento;
import com.RFID.MHIFES.model.Horario;
import com.RFID.MHIFES.model.Local;
import com.RFID.MHIFES.model.LocalEquipamento;
import com.RFID.MHIFES.model.Periodo;
import com.RFID.MHIFES.model.PeriodoDisciplina;
import com.RFID.MHIFES.model.Professor;
import com.RFID.MHIFES.repository.AlocacaoRepository;
import com.RFID.MHIFES.repository.AlunoRepository;
import com.RFID.MHIFES.repository.CoordenadoriaRepository;
import com.RFID.MHIFES.repository.DisciplinaRepository;
import com.RFID.MHIFES.repository.EquipamentoRepository;
import com.RFID.MHIFES.repository.HorarioRepository;
import com.RFID.MHIFES.repository.LocalEquipamentoRepository;
import com.RFID.MHIFES.repository.LocalRepository;
import com.RFID.MHIFES.repository.PeriodoDisciplinaRepository;
import com.RFID.MHIFES.repository.PeriodoRepository;
import com.RFID.MHIFES.repository.ProfessorRepository;

@SpringBootApplication
public class MhifesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MhifesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(EquipamentoRepository equipamentoRepository,
			AlunoRepository alunoRepository, CoordenadoriaRepository coordenadoriaRepository,
			ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository,
			PeriodoRepository periodoRepository, HorarioRepository horarioRepository,
			PeriodoDisciplinaRepository periodoDisciplinaRepository, LocalRepository localRepository, 
			LocalEquipamentoRepository localEquipamentoRepository, AlocacaoRepository alocacaoRepository) {
		return args -> {

			// equipamentoRepository.deleteAll();
			// Equipamento equipamento = new Equipamento();
			// equipamento.setNome("Projetor");
			// equipamentoRepository.save(equipamento);

			// Equipamento equipamento2 = new Equipamento();
			// equipamento2.setNome("Notebook");
			// equipamentoRepository.save(equipamento2);

			// /* */
			// alunoRepository.deleteAll();
			// Aluno aluno = new Aluno();
			// aluno.setNome("João");
			// aluno.setMatricula("123456");
			// aluno.setCurso("Engenharia de Software");
			// alunoRepository.save(aluno);

			// Aluno aluno2 = new Aluno();
			// aluno2.setNome("Maria");
			// aluno2.setMatricula("654321");
			// aluno2.setCurso("Engenharia de Software");
			// alunoRepository.save(aluno2);

			// /* */
			// coordenadoriaRepository.deleteAll();
			// Coordenadoria coordenadoria = new Coordenadoria();
			// coordenadoria.setNome("Coordenadoria de Engenharia de Software");
			// coordenadoriaRepository.save(coordenadoria);

			// Coordenadoria coordenadoria2 = new Coordenadoria();
			// coordenadoria2.setNome("Coordenadoria de Engenharia de Computação");
			// coordenadoriaRepository.save(coordenadoria2);
			// /* */
			// professorRepository.deleteAll();
			// Professor professor = new Professor();
			// professor.setNome("José");
			// professor.setSigla("J");
			// professor.setMatricula("1234567");
			// // professor.setCurso("Engenharia de Software");
			// professor.setEhCoordenador(false);
			// professor.setCoordenadoria(coordenadoria);
			// professorRepository.save(professor);

			// Professor professor2 = new Professor();
			// professor2.setNome("Maria");
			// professor2.setSigla("M");
			// professor2.setMatricula("7654321");
			// // professor2.setCurso("Engenharia de Computação");
			// professor2.setEhCoordenador(false);
			// professor2.setCoordenadoria(coordenadoria2);
			// professorRepository.save(professor2);

			// /* */
			// disciplinaRepository.deleteAll();
			// Disciplina disciplina = new Disciplina();
			// disciplina.setNome("Banco de Dados");
			// disciplina.setSigla("BD");
			// disciplinaRepository.save(disciplina);

			// Disciplina disciplina2 = new Disciplina();
			// disciplina2.setNome("Engenharia de Software");
			// disciplina2.setSigla("ES");
			// disciplinaRepository.save(disciplina2);

			// /* */
			// periodoRepository.deleteAll();
			// Periodo periodo = new Periodo();
			// periodo.setAno(Year.of(2021));
			// periodo.setSemestre(1L);
			// periodo.setDataInicio(LocalDate.parse("2021-01-01"));
			// periodo.setDataFim(LocalDate.parse("2021-06-30"));


			// PeriodoDisciplina periodoDisciplina = new PeriodoDisciplina();
			// periodoDisciplina.setDisciplina(disciplina);
			// periodoDisciplina.setPeriodo(periodo);

			// List<Aluno> alunos = new ArrayList<>();
			// alunos.add(aluno);
			// alunos.add(aluno2);
			// periodoDisciplina.setAlunos(alunos);

			// PeriodoDisciplina periodoDisciplina2 = new PeriodoDisciplina();
			// periodoDisciplina2.setDisciplina(disciplina2);
			// periodoDisciplina2.setPeriodo(periodo);
			// periodoDisciplina2.setAlunos(alunos);

			// List<PeriodoDisciplina> periodoDisciplinas = new ArrayList<>();
			// periodoDisciplinas.add(periodoDisciplina);
			// periodoDisciplinas.add(periodoDisciplina2);
			// periodo.setPeriodoDisciplinas(periodoDisciplinas);
			// periodoRepository.save(periodo);

			// Periodo periodo2 = new Periodo();
			// periodo2.setAno(Year.of(2021));
			// periodo2.setSemestre(2L);
			// periodo2.setDataInicio(LocalDate.parse("2021-07-01"));
			// periodo2.setDataFim(LocalDate.parse("2021-12-31"));

			// PeriodoDisciplina periodoDisciplina3 = new PeriodoDisciplina();
			// periodoDisciplina3.setDisciplina(disciplina);
			// periodoDisciplina3.setPeriodo(periodo2);
			// periodoDisciplina3.setAlunos(alunos);

			// PeriodoDisciplina periodoDisciplina4 = new PeriodoDisciplina();
			// periodoDisciplina4.setDisciplina(disciplina2);
			// periodoDisciplina4.setPeriodo(periodo2);
			// periodoDisciplina4.setAlunos(alunos);

			// List<PeriodoDisciplina> periodoDisciplinas2 = new ArrayList<>();
			// periodoDisciplinas2.add(periodoDisciplina3);
			// periodoDisciplinas2.add(periodoDisciplina4);
			// periodo2.setPeriodoDisciplinas(periodoDisciplinas2);
			// periodoRepository.save(periodo2);

			// /* */
			// Horario horario = new Horario();
			// horario.setHoraInicio(LocalTime.parse("07:00"));
			// horario.setHoraFim(LocalTime.parse("07:50"));
			// horarioRepository.save(horario);

			// Horario horario2 = new Horario();
			// horario2.setHoraInicio(LocalTime.parse("07:50"));
			// horario2.setHoraFim(LocalTime.parse("08:40"));
			// horarioRepository.save(horario2);

			// /* */
			// Local local = new Local();
			// local.setNome("Sala 1");
			// local.setCapacidade(50);
			// local.setLocalEquipamentos(null);
			// localRepository.save(local);

			// Local local2 = new Local();
			// local2.setNome("Sala 2");
			// local2.setCapacidade(50);
			// local2.setLocalEquipamentos(null);
			// localRepository.save(local2);

			// LocalEquipamento localEquipamento = new LocalEquipamento();
			// localEquipamento.setEquipamento(equipamento);
			// localEquipamento.setLocal(local);
			// localEquipamento.setQuantidade(1);
			// localEquipamentoRepository.save(localEquipamento);

			// LocalEquipamento localEquipamento2 = new LocalEquipamento();
			// localEquipamento2.setEquipamento(equipamento2);
			// localEquipamento2.setLocal(local2);
			// localEquipamento2.setQuantidade(2);
			// localEquipamentoRepository.save(localEquipamento2);

			// List<LocalEquipamento> localEquipamentos = new ArrayList<>();
			// localEquipamentos.add(localEquipamento);
			// local.setLocalEquipamentos(localEquipamentos);
			// localRepository.save(local);

			// List<LocalEquipamento> localEquipamentos2 = new ArrayList<>();
			// localEquipamentos.add(localEquipamento2);
			// local2.setLocalEquipamentos(localEquipamentos2);
			// localRepository.save(local2);

			// Alocacao alocacao = new Alocacao();
			// alocacao.setDataAula(LocalDate.parse("2021-01-01"));
			// alocacao.setDiaSemana(LocalDate.parse("2021-01-01").getDayOfWeek().getValue());
			// alocacao.setPeriodoDisciplina(periodoDisciplina);
			// alocacao.setProfessor(professor);
			// alocacao.setLocal(local);
			// alocacao.setHorario(horario);
			// alocacao.setTurma("V01");
			// alocacaoRepository.save(alocacao);

			// Alocacao alocacao2 = new Alocacao();
			// alocacao2.setDataAula(LocalDate.parse("2021-01-02"));
			// alocacao2.setDiaSemana(LocalDate.parse("2021-01-02").getDayOfWeek().getValue());
			// alocacao2.setPeriodoDisciplina(periodoDisciplina2);
			// alocacao2.setProfessor(professor2);
			// alocacao2.setLocal(local2);
			// alocacao2.setHorario(horario);
			// alocacao2.setTurma("V01");
			// alocacaoRepository.save(alocacao2);

			// Alocacao alocacao3 = new Alocacao();
			// alocacao3.setDataAula(LocalDate.parse("2021-07-01"));
			// alocacao3.setDiaSemana(LocalDate.parse("2021-07-01").getDayOfWeek().getValue());
			// alocacao3.setPeriodoDisciplina(periodoDisciplina4);
			// alocacao3.setProfessor(professor2);
			// alocacao3.setLocal(local2);
			// alocacao3.setHorario(horario);
			// alocacao3.setTurma("V01");
			// alocacaoRepository.save(alocacao3);

		};
	}
}

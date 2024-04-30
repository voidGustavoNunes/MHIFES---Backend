package com.rfid.mhifes;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rfid.mhifes.controller.LocalController;
import com.rfid.mhifes.model.Aluno;
import com.rfid.mhifes.model.Coordenadoria;
import com.rfid.mhifes.model.Disciplina;
import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.model.Horario;
import com.rfid.mhifes.model.Local;
import com.rfid.mhifes.model.Periodo;
import com.rfid.mhifes.model.Professor;
import com.rfid.mhifes.repository.AlunoRepository;
import com.rfid.mhifes.repository.CoordenadoriaRepository;
import com.rfid.mhifes.repository.DisciplinaRepository;
import com.rfid.mhifes.repository.EquipamentoRepository;
import com.rfid.mhifes.repository.HorarioRepository;
import com.rfid.mhifes.repository.LocalEquipamentoRepository;
import com.rfid.mhifes.repository.LocalRepository;
import com.rfid.mhifes.repository.PeriodoRepository;
import com.rfid.mhifes.repository.ProfessorRepository;

@SpringBootApplication
public class MhifesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MhifesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(EquipamentoRepository equipamentoRepository,
			AlunoRepository alunoRepository, CoordenadoriaRepository coordenadoriaRepository,
			ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository,
			PeriodoRepository periodoRepository, HorarioRepository horarioRepository) {
		return args -> {

			equipamentoRepository.deleteAll();
			Equipamento equipamento = new Equipamento();
			equipamento.setNome("Projetor");
			equipamentoRepository.save(equipamento);

			Equipamento equipamento2 = new Equipamento();
			equipamento2.setNome("Notebook");
			equipamentoRepository.save(equipamento2);
			
			/* */
			alunoRepository.deleteAll();
			Aluno aluno = new Aluno();
			aluno.setNome("João");
			aluno.setMatricula("123456");
			aluno.setCurso("Engenharia de Software");
			alunoRepository.save(aluno);

			Aluno aluno2 = new Aluno();
			aluno2.setNome("Maria");
			aluno2.setMatricula("654321");
			aluno2.setCurso("Engenharia de Software");
			alunoRepository.save(aluno2);

			/* */
			coordenadoriaRepository.deleteAll();
			Coordenadoria coordenadoria = new Coordenadoria();
			coordenadoria.setNome("Coordenadoria de Engenharia de Software");
			coordenadoriaRepository.save(coordenadoria);

			Coordenadoria coordenadoria2 = new Coordenadoria();
			coordenadoria2.setNome("Coordenadoria de Engenharia de Computação");
			coordenadoriaRepository.save(coordenadoria2);
			/* */
			professorRepository.deleteAll();
			Professor professor = new Professor();
			professor.setNome("José");
			professor.setMatricula("1234567");
			// professor.setCurso("Engenharia de Software");
			professor.setEhCoordenador(false);
			professor.setCoordenadoria(coordenadoria);
			professorRepository.save(professor);

			Professor professor2 = new Professor();
			professor2.setNome("Maria");
			professor2.setMatricula("7654321");
			// professor2.setCurso("Engenharia de Computação");
			professor2.setEhCoordenador(false);
			professor2.setCoordenadoria(coordenadoria2);
			professorRepository.save(professor2);

			/* */
			periodoRepository.deleteAll();
			Periodo periodo = new Periodo();
			periodo.setNome("2021.1");
			periodo.setDataInicio(LocalDate.parse("2021-01-01"));
			periodo.setDataFim(LocalDate.parse("2021-06-30"));
			periodoRepository.save(periodo);

			Periodo periodo2 = new Periodo();
			periodo2.setNome("2021.2");
			periodo2.setDataInicio(LocalDate.parse("2021-07-01"));
			periodo2.setDataFim(LocalDate.parse("2021-12-31"));
			periodoRepository.save(periodo2);

			/* */
			disciplinaRepository.deleteAll();
			Disciplina disciplina = new Disciplina();
			disciplina.setNome("Banco de Dados");
			disciplinaRepository.save(disciplina);

			Disciplina disciplina2 = new Disciplina();
			disciplina2.setNome("Engenharia de Software");
			disciplinaRepository.save(disciplina2);

			Horario horario = new Horario();
			horario.setHoraInicio(LocalTime.parse("08:00"));
			horario.setHoraFim(LocalTime.parse("10:00"));
			horarioRepository.save(horario);

		};
	}
}

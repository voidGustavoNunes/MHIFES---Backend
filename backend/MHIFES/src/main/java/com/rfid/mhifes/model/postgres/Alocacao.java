package com.rfid.mhifes.model.postgres;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;

import com.rfid.mhifes.enums.Status;
import com.rfid.mhifes.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "alocacao")
@SQLDelete(sql = "UPDATE alocacao SET status = 'Inativo' WHERE id = ?")
// Essa anotação faz com que as consultas sejam apenas das alocações com o
// status igual a ativo
// @Where(clause = "status = 'Ativo'")
public class Alocacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Horário é obrigatório")
	@ManyToOne
	@JoinColumn(name = "horario")
	private Horario horario;

	@NotBlank(message = "Turma é obrigatória")
	@Column(length = 30, nullable = false)
	private String turma;

	@Column(nullable = true, name = "data_aula")
	private LocalDate dataAula;

	@Column(nullable = false, name = "dia_semana")
	private Integer diaSemana;

	@ManyToOne
	@JoinColumn(name = "local")
	private Local local;

	@NotNull(message = "Período disciplina é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "periodo_disciplina")
	private PeriodoDisciplina periodoDisciplina;

	@NotNull(message = "Professor é obrigatório")
	@ManyToOne
	@JoinColumn(name = "professor")
	private Professor professor;

	@NotNull
	@Column(name = "status", length = 10, nullable = false)
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ATIVO;

	@Override
	public String toString() {

		return "{" + "\"id\": " + id + ", \"horario\": " + horario
				.toString() + ", \"turma\": \"" + turma + "\"" + ", \"dataAula\": \"" + dataAula + "\"" + ", \"local\": " + (local != null
						? local.toString()
						: null) + ", \"periodoDisciplina\": " + (periodoDisciplina != null
								? periodoDisciplina.toString()
								: null) + ", \"professor\": " + (professor != null ? professor.toString()
										: professor) + "}";
	}
}

package com.rfid.mhifes.model.postgres;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "periodo_disciplina")
public class PeriodoDisciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "periodo_id")
	@JsonIgnoreProperties("periodoDisciplinas")
	private Periodo periodo;

	@NotNull(message = "Disciplina é obrigatória")
	@ManyToOne
	@JoinColumn(name = "disciplina_id", nullable = false)
	private Disciplina disciplina;

	@ManyToMany
	@JoinTable(name = "aluno_periodo_disciplina", joinColumns = @JoinColumn(name = "periodo_disciplina_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private List<Aluno> alunos = new ArrayList<>();

	@Override
	public String toString() {

		StringBuilder alunosString = new StringBuilder("[");
		for (Aluno aluno : alunos) {
			alunosString.append(aluno.toString()).append(", ");
		}
		if (!alunos.isEmpty()) {
			alunosString.setLength(alunosString.length() - 2); // Remove a última vírgula e espaço
		}
		alunosString.append("]");

		return "{" + "\"id\": " + id + ", \"periodo\": " + periodo.toString() + ", \"disciplina\": " + disciplina
				.toString() + ", \"alunos\": " + alunosString + "}";
	}

}

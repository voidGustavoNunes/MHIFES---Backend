package com.rfid.mhifes.model.mysql;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "alocacao")
public class AlocacaoMySQL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Year ano;

	@Column(nullable = false)
	private Integer semestre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "disciplina_id")
	private DisciplinaMySQL disciplinaMySQL;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "professor1_id")
	private ProfessorMySQL professor1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "professor2_id")
	private ProfessorMySQL professor2;

	@OneToMany(mappedBy = "alocacao", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("alocacao")
	private List<AulaMySQL> aulas = new ArrayList<>();

}

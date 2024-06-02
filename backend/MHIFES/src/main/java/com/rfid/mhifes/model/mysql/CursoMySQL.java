package com.rfid.mhifes.model.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "curso")
public class CursoMySQL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@Column(nullable = false, name = "qt_periodos")
	private Integer qtPeriodos;

	@Column(nullable = false)
	private String nivel;

	@Column(nullable = false)
	private Boolean semestral;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coordenadoria_id")
	private CoordenadoriaMySQL coordenadoria;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "professor_id")
	private ProfessorMySQL professor;

}

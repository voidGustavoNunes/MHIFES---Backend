package com.rfid.mhifes.model.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "disciplina")
public class DisciplinaMySQL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sigla;

	@Column(nullable = false)
	private String tipo;

	@Column(nullable = false, name = "carga_horaria")
	private Integer cargaHoraria;

	@Column(nullable = false, name = "qt_aulas")
	private Integer qtAulas;

	@Column(nullable = false)
	private Integer periodo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "matriz_id", nullable = false)
	private MatrizCurricularMySQL matriz;

}

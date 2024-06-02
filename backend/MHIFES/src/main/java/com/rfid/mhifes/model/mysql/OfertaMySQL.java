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
@Table(name = "oferta")
public class OfertaMySQL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer ano;

	@Column(nullable = false)
	private Integer semestre;

	@Column(nullable = false, name = "tempo_maximo_trabalho")
	private Integer tempoMaximoTrabalho;

	@Column(nullable = false, name = "intervalo_minimo")
	private Integer intervaloMinimo;

	@Column(nullable = false)
	private Boolean publica;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "turma_id", nullable = false)
	private TurmaMySQL turma;

}

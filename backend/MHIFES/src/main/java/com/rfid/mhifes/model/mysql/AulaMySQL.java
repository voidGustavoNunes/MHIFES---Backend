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
@Table(name = "aula")
public class AulaMySQL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer numero;

	@Column(nullable = false)
	private Integer dia;

	@Column(nullable = false)
	private Integer turno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "alocacao_id")
	private AlocacaoMySQL alocacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "oferta_id", nullable = false)
	private OfertaMySQL oferta;

}

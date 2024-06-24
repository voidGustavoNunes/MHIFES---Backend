package com.rfid.mhifes.model.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "coordenadoria")
@NoArgsConstructor
public class Coordenadoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	@Column(length = 150, nullable = false, unique = true)
	private String nome;

	@ManyToOne
	@JoinColumn(nullable = false, unique = true)
	private Professor coordenador;

	@Override
	public String toString() {
		return "{" + "\"id\": " + id + ", \"nome\": \"" + nome + "\"" + ", \"coordenador\": " + (coordenador != null
				? coordenador.toString()
				: null) + "}";
	}

}

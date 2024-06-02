package com.rfid.mhifes.model.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "disciplina")
@NoArgsConstructor
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	@Column(length = 255, nullable = false)
	private String nome;

	@NotBlank(message = "Sigla é obrigatória")
	@Column(length = 50, nullable = false)
	private String sigla;

	public Disciplina(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return "{" + "\"id\": " + id + ", \"nome\": \"" + nome + "\"" + ", \"sigla\": \"" + sigla + "\"" + "}";
	}

}

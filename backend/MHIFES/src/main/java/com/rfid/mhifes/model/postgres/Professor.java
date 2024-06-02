package com.rfid.mhifes.model.postgres;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "curso", column = @Column(nullable = true))
public class Professor extends Pessoa {

	@NotBlank(message = "Sigla é obrigatória")
	@Column(length = 50, nullable = false)
	private String sigla;

	@NotNull(message = "Coordenador é obrigatório")
	@Column(nullable = false)
	private boolean ehCoordenador;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Coordenadoria coordenadoria;

	@Override
	public String toString() {
		return "{" + "\"id\": " + getId() + ", \"nome\": \"" + getNome() + "\"" + ", \"sigla\": \"" + sigla + "\"" + ", \"matricula\": \"" + getMatricula() + "\"" + ", \"ehCoordenador\": \"" + ehCoordenador + "\"" + ", \"coordenadoria\": " + (coordenadoria != null
				? coordenadoria.toString()
				: null) + "}";
	}

}

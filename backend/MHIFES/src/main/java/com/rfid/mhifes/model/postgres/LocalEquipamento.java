package com.rfid.mhifes.model.postgres;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "local_equipamento")
public class LocalEquipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "local_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Local local;

	@NotNull(message = "Equipamento é obrigatório")
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "equipamento_id", nullable = false)
	private Equipamento equipamento;

	@NotNull(message = "Quantidade é obrigatória")
	@Positive(message = "Quantidade deve ser maior que zero")
	@Column(nullable = false)
	private Integer quantidade;

	@Override
	public String toString() {
		return "{" + "\"id\": " + id + ", \"equipamento\": " + (equipamento != null ? equipamento.toString()
				: null) + ", \"quantidade\": \"" + quantidade + "\"" + "}";
	}

}

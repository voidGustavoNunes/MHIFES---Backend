package com.rfid.mhifes.model;

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

@Data
@Entity
@Table(name = "coordenadoria")
public class Coordenadoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(length = 150, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(nullable = false, unique = true)
    private Professor coordenador;

    @Override
    public String toString() {
        return "{"
                + "\"id\": " + id
                + ", \"nome\": \"" + nome + "\""
                + ", \"coordenador\": " + coordenador.toString()
                + "}";
    }

}

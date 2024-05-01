package com.rfid.mhifes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 10, nullable = false)
    private String sigla;

    @Override
    public String toString() {
        return "{"
                + "\"id\": " + id
                + ", \"nome\": \"" + nome + "\""
                + ", \"sigla\": \"" + sigla + "\""
                + "}";
    }

}

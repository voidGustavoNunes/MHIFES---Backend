package com.rfid.mhifes.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "curso", column = @Column(nullable = true))
public class Professor extends Pessoa {

    @Column(length = 50, nullable = false)
    private String sigla;

    @Column(nullable = false)
    private boolean ehCoordenador;

    @Override
    public String toString() {
        return "{"
                + "\"id\": " + getId()
                + ", \"nome\": \"" + getNome() + "\""
                + ", \"sigla\": \"" + sigla + "\""
                + ", \"matricula\": \"" + getMatricula() + "\""
                + ", \"ehCoordenador\": \"" + ehCoordenador + "\""
                + "}";
    }

}

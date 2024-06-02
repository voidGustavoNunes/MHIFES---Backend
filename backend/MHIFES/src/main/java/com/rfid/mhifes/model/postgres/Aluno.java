package com.rfid.mhifes.model.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "aluno")
@EqualsAndHashCode(callSuper = false)
public class Aluno extends Pessoa {

    @NotBlank(message = "Curso é obrigatório")
    @Column(length = 150, nullable = false)
    private String curso;

    @Override
    public String toString() {
        return "{"
                + "\"id\": " + getId()
                + ", \"nome\": \"" + getNome() + "\""
                + ", \"matricula\": \"" + getMatricula() + "\""
                + ", \"curso\": \"" + getCurso() + "\""
                + "}";
    }
}
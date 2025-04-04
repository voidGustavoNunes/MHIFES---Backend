package com.rfid.mhifes.model.postgres;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Data
@Entity
@Table(name = "horario")
@EqualsAndHashCode(callSuper = false)
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Dia da semana é obrigatório")
    @Column(nullable = false, name ="hora_inicio")
    private LocalTime horaInicio;

    @NotNull(message = "Dia da semana é obrigatório")
    @Column(nullable = false, name ="hora_fim")
    private LocalTime horaFim;

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id
                + ", \"horaInicio\": \"" + horaInicio + "\""
                + ", \"horaFim\": \"" + horaFim + "\""
                + "}";
    }

}
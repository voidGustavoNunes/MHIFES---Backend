package com.rfid.mhifes.model;

import java.time.LocalDate;
import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "periodo")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Year ano;

    @Min(1)
    @Column(nullable = false)
    private Long semestre;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Override
    public String toString() {
        return "{"
                + "\"id\": " + id
                + ", \"ano\": \"" + ano + "\""
                + ", \"semestre\": \"" + semestre + "\""
                + ", \"dataInicio\": \"" + dataInicio + "\""
                + ", \"dataFim\": \"" + dataFim + "\""
                + "}";
    }

}

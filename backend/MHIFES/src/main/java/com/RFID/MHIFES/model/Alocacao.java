package com.RFID.MHIFES.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "alocacao")
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer numAulas;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFinal;

    @Column(length = 10, nullable = false)
    private String turma;

    @Column(length = 10, nullable = false)
    private String diaSemana;
    
    @Column(nullable = false)
    private LocalDate dataAula;
}

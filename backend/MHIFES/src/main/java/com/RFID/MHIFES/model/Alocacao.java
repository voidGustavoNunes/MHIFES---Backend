package com.RFID.MHIFES.model;

import java.sql.Time;

import jakarta.persistence.Table;

@Table(name = "alocacao")
public class Alocacao {
    
    private Long id;

    private Time horaInicio;

    private Time horaFinal;

    private String turma;

    

}

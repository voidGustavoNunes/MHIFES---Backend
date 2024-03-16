package com.rfid.mhifes.model;

import java.time.LocalDate;

import org.hibernate.mapping.List;

public class Evento {
    
    private Long id;

    private LocalDate intervaloDia;

    private String nome;

    private Local local;
}

package com.RFID.MHIFES.model;

import jakarta.persistence.Id;

public class Pessoa {

    @Id
    private Long id;

    private String nome;

    private String matricula;
    
}

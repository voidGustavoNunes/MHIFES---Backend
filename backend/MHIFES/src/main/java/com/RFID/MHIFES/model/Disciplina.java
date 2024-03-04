package com.RFID.MHIFES.model;

import jakarta.persistence.Id;

public class Disciplina {

    @Id
    private Long id;

    private String nome;
}
package com.RFID.MHIFES.model;

import jakarta.persistence.Id;

public class Equipamento {
    
    @Id
    private Long id;

    private String nome;
}

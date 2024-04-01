package com.rfid.mhifes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "coordenadoria")
public class Coordenadoria {
    
    @Id
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;
}

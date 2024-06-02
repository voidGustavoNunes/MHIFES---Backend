package com.rfid.mhifes.model.mysql;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "label")
public class LabelMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "turno", nullable = false)
    private Integer turno;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "inicio", nullable = false)
    private LocalTime inicio;

    @Column(name = "fim", nullable = false)
    private LocalTime fim;

}

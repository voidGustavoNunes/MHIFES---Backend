package com.rfid.mhifes.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "evento")
@EqualsAndHashCode(callSuper = false)
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private List<LocalDate> intervaloData;

    @Column(length = 150, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "local")
    private Local local;
}

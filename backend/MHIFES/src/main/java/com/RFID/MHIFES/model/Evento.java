package com.rfid.mhifes.model;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(nullable = false)
    private LocalDate dataEvento;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 5000, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "horario")
    private Horario horario;

    // @Column(nullable = false)
    // private LocalTime horarioInicio;

    // @Column(nullable = false)
    // private LocalTime horarioFim;

    @ManyToOne
    @JoinColumn(name = "local")
    private Local local;

}

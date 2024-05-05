package com.rfid.mhifes.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Data do evento é obrigatória")
    @Column(nullable = false)
    private LocalDate dataEvento;

    @NotBlank(message = "Nome é obrigatório")
    @Column(length = 50, nullable = false)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(length = 5000, nullable = false)
    private String descricao;

    @NotBlank(message = "Horário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "horario")
    private Horario horario;

    @NotBlank(message = "Local é obrigatório")
    @ManyToOne
    @JoinColumn(name = "local")
    private Local local;

}

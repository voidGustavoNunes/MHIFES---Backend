package com.rfid.mhifes.model.postgres;

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
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "evento")
@EqualsAndHashCode(callSuper = false)
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data do evento é obrigatória")
    @Column(nullable = false)
    private LocalDate dataEvento;

    @NotBlank(message = "Nome é obrigatório")
    @Column(length = 50, nullable = false)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(length = 5000, nullable = false)
    private String descricao;

    @NotNull(message = "Horário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "horario")
    private Horario horario;

    @NotNull(message = "Local é obrigatório")
    @ManyToOne
    @JoinColumn(name = "local")
    private Local local;

}

package com.rfid.mhifes.model;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Time;
import java.time.LocalTime;

import com.rfid.mhifes.enums.DiaSemana;
import com.rfid.mhifes.enums.converters.DiaSemanaConverters;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Data
@Entity
@Table(name = "horario")
@EqualsAndHashCode(callSuper = false)
public class Horario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     @NotNull
     @Column(name = "dia_Semana", nullable = false)
     @Convert(converter = DiaSemanaConverters.class) 
     private DiaSemana diaSemana;

    @Column(nullable = false)
    private Time horaInicio;

    @Column(nullable = false)
    private Time horaFim;
}
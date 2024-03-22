package com.RFID.MHIFES.model;

import java.sql.Time;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "log")
public class Log {

    private Long id;

    private LocalDate data;

    private Time hora;

    private String descricao;
    
}

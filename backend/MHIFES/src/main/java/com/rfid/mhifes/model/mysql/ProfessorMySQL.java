package com.rfid.mhifes.model.mysql;

import com.rfid.mhifes.model.mysql.CoordenadoriaMySQL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "professor_id")
public class ProfessorMySQL extends ServidorMySQL {

    @Column(nullable = false, name = "carga_horaria")
    private Double cargaHoraria;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coordenadoria_id", nullable = false)
    private CoordenadoriaMySQL coordenadoria;

}

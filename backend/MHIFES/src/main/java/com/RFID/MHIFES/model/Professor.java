package com.RFID.MHIFES.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = false)
public class Professor extends Pessoa {

    @Column(nullable = false)
    private boolean ehCoordenador;
}

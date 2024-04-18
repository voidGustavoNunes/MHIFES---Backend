package com.rfid.mhifes.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = false)

@AttributeOverrides({
    @AttributeOverride(name = "curso", column = @Column(nullable = true))
})
public class Professor extends Pessoa {

    @Column(nullable = false)
    private boolean ehCoordenador;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Coordenadoria coordenadoria;
}

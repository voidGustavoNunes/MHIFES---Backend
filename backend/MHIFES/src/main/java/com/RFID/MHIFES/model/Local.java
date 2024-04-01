package com.rfid.mhifes.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;
    
    private int capacidade;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "localEquip", joinColumns = @JoinColumn(name = "local"), inverseJoinColumns = @JoinColumn(name = "equipamentos"))
    private List<Equipamento> equipamentos;

}

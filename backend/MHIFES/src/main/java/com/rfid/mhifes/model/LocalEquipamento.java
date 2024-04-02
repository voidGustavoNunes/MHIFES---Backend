package com.rfid.mhifes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "local_equipamento")
public class LocalEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Local local;

    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Equipamento equipamento;

    @Column(nullable = false)
    private Integer quantidade;

}

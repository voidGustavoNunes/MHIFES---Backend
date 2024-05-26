package com.rfid.mhifes.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(length = 150, nullable = false)
    private String nome;

    @NotNull(message = "Capacidade é obrigatória")
    @Column(nullable = false)
    private Integer capacidade;

    @NotNull(message = "Equipamentos são obrigatórios")
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocalEquipamento> localEquipamentos = new ArrayList<>();

    @Override
    public String toString() {

        StringBuilder localEquipamentosString = new StringBuilder("[");
        for (LocalEquipamento localEquipamento : localEquipamentos) {
            localEquipamentosString.append(localEquipamento.toString()).append(", ");
        }
        if (!localEquipamentos.isEmpty()) {
            localEquipamentosString.setLength(localEquipamentosString.length() - 2); // Remove a última vírgula e espaço
        }
        localEquipamentosString.append("]");

        return "{"
                + "\"id\": " + id
                + ", \"nome\": \"" + nome + "\""
                + ", \"capacidade\": " + capacidade
                + ", \"localEquipamentos\": " + localEquipamentosString
                + "}";
    }

}

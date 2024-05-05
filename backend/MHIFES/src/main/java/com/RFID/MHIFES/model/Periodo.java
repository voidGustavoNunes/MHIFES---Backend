package com.rfid.mhifes.model;

import java.time.LocalDate;
import java.time.Year;
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "periodo")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Ano é obrigatório")
    @Column(nullable = false)
    private Year ano;

    @NotBlank(message = "Semestre é obrigatório")
    @Min(value = 1, message = "O semestre deve ser 1 ou 2")
    @Max(value = 2, message = "O semestre deve ser 1 ou 2")
    @Column(nullable = false)
    private Long semestre;

    @NotBlank(message = "Data de início é obrigatória")
    @Column(nullable = false)
    private LocalDate dataInicio;

    @NotBlank(message = "Data de fim é obrigatória")
    @Column(nullable = false)
    private LocalDate dataFim;

    @NotBlank(message = "Disciplinas são obrigatórias")
    @Column(nullable = false)
    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PeriodoDisciplina> periodoDisciplinas = new ArrayList<>();

    @Override
    public String toString() {

        StringBuilder periodoDisciplinasString = new StringBuilder("[");
        for (PeriodoDisciplina periodoDisciplina : periodoDisciplinas) {
            periodoDisciplinasString.append(periodoDisciplina.toString()).append(", ");
        }
        if (!periodoDisciplinas.isEmpty()) {
            periodoDisciplinasString.setLength(periodoDisciplinasString.length() - 2); // Remove a última vírgula e
                                                                                       // espaço
        }
        periodoDisciplinasString.append("]");

        return "{"
                + "\"id\": " + id
                + ", \"ano\": \"" + ano + "\""
                + ", \"semestre\": \"" + semestre + "\""
                + ", \"dataInicio\": \"" + dataInicio + "\""
                + ", \"dataFim\": \"" + dataFim + "\""
                + ", \"periodoDisciplinas\": " + periodoDisciplinasString
                + "}";
    }

}

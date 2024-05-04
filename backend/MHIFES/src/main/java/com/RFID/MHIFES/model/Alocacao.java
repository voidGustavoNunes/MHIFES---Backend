package com.rfid.mhifes.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLDelete;

import com.rfid.mhifes.enums.Status;
import com.rfid.mhifes.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "alocacao")
@SQLDelete(sql = "UPDATE alocacao SET status = 'Inativo' WHERE id = ?")
// Essa anotação faz com que as consultas sejam apenas das alocações com o
// status igual a ativo
// @Where(clause = "status = 'Ativo'")
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "horario")
    private Horario horario;

    @Column(length = 30, nullable = false)
    private String turma;

    @Column(nullable = false)
    private LocalDate dataAula;
    // private List<LocalDate> dataAulas;

    @ManyToOne
    @JoinColumn(name = "local")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "periodo_disciplina")
    private PeriodoDisciplina periodoDisciplina;

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;

    @NotNull
    @Column(name = "status", length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    @Override
    public String toString() {

        // StringBuilder dataAulaString = new StringBuilder("[");
        // for (LocalDate dataAula : dataAulas) {
        //     dataAulaString.append(dataAula.toString()).append(", ");
        // }
        // if (!dataAulas.isEmpty()) {
        //     dataAulaString.setLength(dataAulaString.length() - 2); // Remove a última vírgula e espaço
        // }
        // dataAulaString.append("]");

        return "{"
                + "\"id\": " + id
                + ", \"horarioInicio\": \"" + horario.getHoraInicio() + "\""
                + ", \"horarioFim\": \"" + horario.getHoraFim() + "\""
                + ", \"turma\": \"" + turma + "\""
                + ", \"dataAula\":" + dataAula
                + ", \"local\": " + local.toString()
                + ", \"periodoDisciplina\": " + periodoDisciplina.toString()
                + ", \"professor\": " + professor.toString()
                + "}";
    }

}

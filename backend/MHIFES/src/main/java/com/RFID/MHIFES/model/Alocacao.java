package com.rfid.mhifes.model;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "local")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "disciplina")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "periodo")
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;

    @NotNull
    @Column(name = "status", length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    @Override
    public String toString() {
        return "{"
                + "\"id\": " + id
                + ", \"horarioInicio\": \"" + horario.getHoraInicio() + "\""
                + ", \"horarioFim\": \"" + horario.getHoraFim() + "\""
                + ", \"turma\": \"" + turma + "\""
                + ", \"dataAula\": \"" + dataAula + "\""
                + ", \"local\": " + local.toString()
                + ", \"disciplina\": " + disciplina.toString()
                + ", \"periodo\": " + periodo.toString()
                + ", \"professor\": " + professor.toString()
                + "}";
    }

}

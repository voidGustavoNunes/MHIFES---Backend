package com.rfid.mhifes.model;

import java.sql.Clob;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;

import com.rfid.mhifes.enums.Status;
import com.rfid.mhifes.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

    @NotNull(message = "Horário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "horario")
    private Horario horario;

    @NotBlank(message = "Turma é obrigatória")
    @Column(length = 30, nullable = false)
    private String turma;

    @NotNull(message = "Data da aula é obrigatória")
    @Column(nullable = false)
    private LocalDate dataAula;

    @Column(nullable = false)
    private Integer diaSemana;

    @NotNull(message = "Local é obrigatório")
    @ManyToOne
    @JoinColumn(name = "local")
    private Local local;

    @NotNull(message = "Período disciplina é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "periodo_disciplina")
    private PeriodoDisciplina periodoDisciplina;

    @NotNull(message = "Professor é obrigatório")
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
                + ", \"periodoDisciplina\": " + periodoDisciplina.toString()
                + ", \"professor\": " + professor.toString()
                + "}";
    }
}

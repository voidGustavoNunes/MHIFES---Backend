package com.rfid.mhifes.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "alocacao")
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

    @ManyToMany
    @JoinTable(name = "alocacao_aluno", joinColumns = @JoinColumn(name = "alocacao_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos = new ArrayList<>();

    @Override
    public String toString() {

        StringBuilder alunosString = new StringBuilder("[");
        for (Aluno aluno : alunos) {
            alunosString.append(aluno.toString()).append(", ");
        }
        if (!alunos.isEmpty()) {
            alunosString.setLength(alunosString.length() - 2); // Remove a última vírgula e espaço
        }
        alunosString.append("]");

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
                + ", \"alunos\": " + alunosString.toString()
                + "}";
    }

}

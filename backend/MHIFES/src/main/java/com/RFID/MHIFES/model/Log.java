package com.rfid.mhifes.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.rfid.mhifes.enums.Operacao;
import com.rfid.mhifes.enums.converters.OperacaoConverters;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;

    private LocalTime hora;

    private String descricao;

    @NotNull
    @Column(name = "operacao", nullable = false)
    @Convert(converter = OperacaoConverters.class)
    private Operacao operacao;

    private Long idRegistro;

    @OneToOne
    private Usuario usuario;

    public Log(LocalDate data, LocalTime hora, String descricao, @NotNull Operacao operacao, Long idRegistro,
            Usuario usuario) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.operacao = operacao;
        this.idRegistro = idRegistro;
        this.usuario = usuario;
    }

}

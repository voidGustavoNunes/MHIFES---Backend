package com.rfid.mhifes.model;

import java.time.LocalDateTime;

import com.rfid.mhifes.enums.Operacao;
import com.rfid.mhifes.enums.converters.OperacaoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "log")
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime data;

    @Column(length = 5000)
    private String descricao;

    @NotNull
    @Column(name = "operacao", nullable = false)
    @Convert(converter = OperacaoConverter.class)
    private Operacao operacao = Operacao.INCLUSAO;

    private Long idRegistro;

    @ManyToOne
    private Usuario usuario;

    public Log(LocalDateTime data, String descricao, @NotNull Operacao operacao, Long idRegistro,
            Usuario usuario) {
        this.data = data;
        this.descricao = descricao;
        this.operacao = operacao;
        this.idRegistro = idRegistro;
        this.usuario = usuario;
    }

}

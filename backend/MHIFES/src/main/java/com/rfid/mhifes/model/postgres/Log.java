package com.rfid.mhifes.model.postgres;

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
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "log")
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data é obrigatória")
    private LocalDateTime data;

    @Column(length = 999999)
    private String descricaoNova;

    @Column(length = 999999)
    private String descricaoAntiga;

    @NotNull
    @Column(name = "operacao", nullable = false)
    @Convert(converter = OperacaoConverter.class)
    private Operacao operacao = Operacao.INCLUSAO;

    @NotNull(message = "ID do registro é obrigatório")
    @Positive(message = "ID do registro deve ser positivo")
    @Column(name = "id_registro")
    private Long idRegistro;

    @NotNull(message = "Usuário é obrigatório")
    @ManyToOne
    private Usuario usuario;

    public Log(LocalDateTime data, String descricaoNova, String descricaoAntiga, @NotNull Operacao operacao, Long idRegistro,
               Usuario usuario) {
        this.data = data;
        this.descricaoNova = descricaoNova;
        this.descricaoAntiga = descricaoAntiga;
        this.operacao = operacao;
        this.idRegistro = idRegistro;
        this.usuario = usuario;
    }

}

package com.rfid.mhifes.enums.converters;

import java.util.stream.Stream;

import com.rfid.mhifes.enums.Operacao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OperacaoConverter implements AttributeConverter<Operacao, String> {

    @Override
    public String convertToDatabaseColumn(Operacao operacao) {
        if (operacao == null) {
            return null;
        }
        return operacao.getValor();
    }

    @Override
    public Operacao convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }

        return Stream.of(Operacao.values())
                .filter(c -> c.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}

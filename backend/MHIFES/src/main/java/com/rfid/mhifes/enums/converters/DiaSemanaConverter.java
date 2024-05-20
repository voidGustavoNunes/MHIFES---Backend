package com.RFID.MHIFES.enums.converters;

import java.util.stream.Stream;

import com.RFID.MHIFES.enums.DiaSemana;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DiaSemanaConverter implements AttributeConverter<DiaSemana, String> {

    @Override
    public String convertToDatabaseColumn(DiaSemana diaSemana) {
        if (diaSemana == null) {
            return null;
        }
        return diaSemana.getValor();
    }

    @Override
    public DiaSemana convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return Stream.of(DiaSemana.values())
                .filter(c -> c.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
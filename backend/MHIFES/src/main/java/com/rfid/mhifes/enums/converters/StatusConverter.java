package com.rfid.mhifes.enums.converters;

import java.util.stream.Stream;

import com.rfid.mhifes.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getValor();
    }

    @Override
    public Status convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return Stream.of(Status.values())
                .filter(c -> c.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
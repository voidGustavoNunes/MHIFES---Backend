package com.rfid.mhifes.enums;

public enum Status {
    ATIVO("Ativo"), INATIVO("Inativo");

    private String valor;

    private Status(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
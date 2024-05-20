package com.RFID.MHIFES.enums;

public enum Operacao {

    INCLUSAO("Inclusão"),
    ALTERACAO("Alteração"),
    EXCLUSAO("Exclusão");

    private String valor;

    private Operacao(String valor) {
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

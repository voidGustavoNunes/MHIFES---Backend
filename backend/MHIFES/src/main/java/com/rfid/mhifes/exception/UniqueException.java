package com.rfid.mhifes.exception;

public class UniqueException extends RuntimeException {

    /**
     * Campo/Field em que houve a violação
     */
    private String campo;

    public UniqueException(String string) {
        super(string);
    }

    public UniqueException(String mensagem, String campo) {
        super(mensagem);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

}

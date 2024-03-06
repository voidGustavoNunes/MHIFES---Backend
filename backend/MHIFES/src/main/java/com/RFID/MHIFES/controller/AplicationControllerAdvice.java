package com.RFID.MHIFES.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.RFID.MHIFES.exception.RegistroNotFoundException;

@RestControllerAdvice
public class AplicationControllerAdvice {

    @ExceptionHandler(RegistroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RegistroNotFoundException ex) {
        return ex.getMessage();
    }
}

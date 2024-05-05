package com.rfid.mhifes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rfid.mhifes.exception.DataIntegrityViolationException;
import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.UniqueException;

@RestControllerAdvice
public class AplicationControllerAdvice {

    @ExceptionHandler(RegistroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RegistroNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UniqueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUniqueException(UniqueException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ex.getMessage();
    }
}

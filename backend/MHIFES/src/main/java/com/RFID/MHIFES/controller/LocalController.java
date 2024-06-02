package com.rfid.mhifes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.postgres.Local;
import com.rfid.mhifes.service.LocalService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping("/api/locais")
public class LocalController extends GenericController<Local> {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        super(localService);
        this.localService = localService;
    }

    @Override
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Local criar(@RequestBody @Valid @NotNull Local local) {
        return localService.criar(local);
    }
}

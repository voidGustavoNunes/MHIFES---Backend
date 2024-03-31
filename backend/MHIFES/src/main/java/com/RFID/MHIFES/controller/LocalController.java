package com.RFID.MHIFES.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.model.Local;
import com.RFID.MHIFES.service.LocalService;


@Validated
@RestController
@RequestMapping("/api/locais")
public class LocalController extends GenericController<Local>{
    

    public LocalController(LocalService localService) {
        super(localService);
    }

}

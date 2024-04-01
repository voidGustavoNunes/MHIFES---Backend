package com.rfid.mhifes.controller;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.model.Local;
import com.rfid.mhifes.service.LocalService;

@Validated
@RestController
@RequestMapping("/api/locais")
public class LocalController extends GenericController<Local>{
    
    public LocalController(LocalService localService) {
        super(localService);
    }
}

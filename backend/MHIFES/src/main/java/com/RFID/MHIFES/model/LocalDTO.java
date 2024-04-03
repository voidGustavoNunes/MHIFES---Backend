package com.RFID.MHIFES.model;

import java.util.List;

import lombok.Data;

@Data
public class LocalDTO {
    private Local local;
    
    private List<LocalEquipamento> localEquipamentos;
}

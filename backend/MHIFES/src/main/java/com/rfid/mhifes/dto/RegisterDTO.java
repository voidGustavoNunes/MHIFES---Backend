package com.rfid.mhifes.dto;

import com.rfid.mhifes.enums.UserRole;

public record RegisterDTO(String login, String nome, String password, UserRole role){
    
}

package com.rfid.mhifes.model;

import com.rfid.mhifes.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role){
    
}

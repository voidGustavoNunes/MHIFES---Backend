package com.rfid.mhifes.dto;

import com.rfid.mhifes.enums.UserRole;

public record LoginResponseDTO(String token, String login, String nome, UserRole role) {
    
}

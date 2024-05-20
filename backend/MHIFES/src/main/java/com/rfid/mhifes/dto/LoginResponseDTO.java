package com.RFID.MHIFES.dto;

import com.RFID.MHIFES.enums.UserRole;

public record LoginResponseDTO(String token, String login, String nome, UserRole role) {
    
}

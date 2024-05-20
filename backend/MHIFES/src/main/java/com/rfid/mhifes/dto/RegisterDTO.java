package com.RFID.MHIFES.dto;

import com.RFID.MHIFES.enums.UserRole;

public record RegisterDTO(String login, String nome, String password, UserRole role) {

}

package com.RFID.MHIFES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "professor")
public class Professor extends Pessoa {
    
}

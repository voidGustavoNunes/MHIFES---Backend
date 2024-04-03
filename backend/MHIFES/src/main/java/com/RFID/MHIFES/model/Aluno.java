package com.RFID.MHIFES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "aluno")
@EqualsAndHashCode(callSuper = false)
public class Aluno extends Pessoa {

}
package com.rfid.mhifes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "coordenador")
@EqualsAndHashCode(callSuper = false)
public class Coordenador extends Pessoa {

    @Override
    public String toString() {
        return "id=" + getId()
                + "\ngetNome()=" + getNome()
                + "\ngetMatricula()=" + getMatricula()
                + "\ngetCurso()=" + getCurso();
    }
}

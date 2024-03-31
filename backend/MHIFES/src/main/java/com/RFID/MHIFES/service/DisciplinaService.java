package com.RFID.MHIFES.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Disciplina;
import com.RFID.MHIFES.repository.DisciplinaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class DisciplinaService  extends GenericServiceImpl<Disciplina, DisciplinaRepository> {


    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        super(disciplinaRepository);

    }

    @Override
    public Disciplina atualizar(@NotNull @Positive Long id, @Valid @NotNull Disciplina disciplina) {
        return repository.findById(id)
                .map(disciplinaEditado -> {
                    disciplinaEditado.setNome(disciplina.getNome());
                    return repository.save(disciplinaEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

}

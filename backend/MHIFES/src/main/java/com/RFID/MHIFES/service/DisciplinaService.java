package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Disciplina;
import com.rfid.mhifes.repository.postgres.DisciplinaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class DisciplinaService extends GenericServiceImpl<Disciplina, DisciplinaRepository> {

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        super(disciplinaRepository);

    }

    @Override
    public Disciplina atualizar(@NotNull @Positive Long id, @Valid @NotNull Disciplina disciplina) {
        return repository.findById(id)
                .map(disciplinaEditado -> {
                    disciplinaEditado.setNome(disciplina.getNome());
                    disciplinaEditado.setSigla(disciplina.getSigla());
                    return repository.save(disciplinaEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

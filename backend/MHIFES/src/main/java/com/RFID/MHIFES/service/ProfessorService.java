package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Professor;
import com.rfid.mhifes.repository.ProfessorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ProfessorService extends GenericServiceImpl<Professor, ProfessorRepository> {

    public ProfessorService(ProfessorRepository professorRepository) {
        super(professorRepository);
    }

    @Override
    public Professor atualizar(@NotNull @Positive Long id, @Valid @NotNull Professor professor) {
        return repository.findById(id)
                .map(professorEditado -> {
                    professorEditado.setNome(professor.getNome());
                    professorEditado.setMatricula(professor.getMatricula());
                    professorEditado.setCurso(professor.getCurso());
                    professorEditado.setEhCoordenador(professor.isEhCoordenador());
                    professorEditado.setCoordenadoria(professor.getCoordenadoria());
                    return repository.save(professorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

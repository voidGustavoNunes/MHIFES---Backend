package com.RFID.MHIFES.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Professor;
import com.RFID.MHIFES.repository.ProfessorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ProfessorService  extends GenericServiceImpl<Professor, ProfessorRepository> {


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
                    return repository.save(professorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

}

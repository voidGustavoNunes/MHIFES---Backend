package com.RFID.MHIFES.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.exception.UniqueException;
import com.RFID.MHIFES.model.Professor;
import com.RFID.MHIFES.repository.ProfessorRepository;

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
    public Professor criar(@Valid @NotNull Professor professor) {

        if (repository.existsByMatricula(professor.getMatricula())) {
            throw new UniqueException("Matrícula já cadastrada", "matricula");
        }

        return repository.save(professor);
    }

    @Override
    public Professor atualizar(@NotNull @Positive Long id, @Valid @NotNull Professor professor) {
        return repository.findById(id)
                .map(professorEditado -> {
                    // Valida se a matrícula já está cadastrada para outro professor ou se a
                    // matrícula é a mesma
                    if (!professorEditado.getMatricula().equals(professor.getMatricula())
                            && repository.existsByMatricula(professor.getMatricula())) {
                        throw new UniqueException("Matrícula já cadastrada", "matricula");
                    }
                    professorEditado.setNome(professor.getNome());
                    professorEditado.setSigla(professor.getSigla());
                    professorEditado.setMatricula(professor.getMatricula());
                    professorEditado.setEhCoordenador(professor.isEhCoordenador());
                    professorEditado.setCoordenadoria(professor.getCoordenadoria());
                    return repository.save(professorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

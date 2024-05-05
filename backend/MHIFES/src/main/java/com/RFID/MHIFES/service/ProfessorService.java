package com.rfid.mhifes.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
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
                    validar(professor);
                    professorEditado.setNome(professor.getNome());
                    professorEditado.setSigla(professor.getSigla());
                    professorEditado.setMatricula(professor.getMatricula());
                    professorEditado.setEhCoordenador(professor.isEhCoordenador());
                    professorEditado.setCoordenadoria(professor.getCoordenadoria());
                    return repository.save(professorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    public void validar(@Valid @NotNull Professor professor) {
        if (professor.getMatricula() == null || professor.getMatricula().isEmpty()) {
            throw new DataIntegrityViolationException("Matrícula não pode ser nula");
        }
        if (professor.getNome() == null || professor.getNome().isEmpty()) {
            throw new DataIntegrityViolationException("Nome não pode ser nulo");
        }
        if (professor.getSigla() == null || professor.getSigla().isEmpty()) {
            throw new DataIntegrityViolationException("Sigla não pode ser nula");
        }
        if (professor.isEhCoordenador()) {
            throw new DataIntegrityViolationException("Professor não pode ser coordenador");
        }

        if (professor.getId() != null) {
            Optional<Professor> optionalProfessor = repository.findById(professor.getId());
            
            if (optionalProfessor.isPresent() && !optionalProfessor.get().getMatricula().equals(professor.getMatricula()) && repository.existsByMatricula(professor.getMatricula())) {
                throw new DataIntegrityViolationException("Matrícula já cadastrada");
            }
        } else {
            if (repository.existsByMatricula(professor.getMatricula())) {
                throw new DataIntegrityViolationException("Matrícula já cadastrada");
            }
        }
        
    }
}

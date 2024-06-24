package com.rfid.mhifes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.UniqueException;
import com.rfid.mhifes.model.postgres.Professor;
import com.rfid.mhifes.repository.postgres.ProfessorRepository;

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
    
	public Page<Professor> acharNome(String substring, Pageable pageable) {
		return repository.findByNomeContaining(substring, pageable);
	}
	public Page<Professor> acharSigla(String substring, Pageable pageable) {
		return repository.findBySiglaContaining(substring, pageable);
	}
	public Page<Professor> acharMatricula(String substring, Pageable pageable) {
		return repository.findByMatriculaContaining(substring, pageable);
	}
}

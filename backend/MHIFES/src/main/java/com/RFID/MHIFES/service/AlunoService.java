package com.rfid.mhifes.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Aluno;
import com.rfid.mhifes.repository.AlunoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class AlunoService extends GenericServiceImpl<Aluno, AlunoRepository> {

    public AlunoService(AlunoRepository alunoRepository) {
        super(alunoRepository);
    }

    @Override
    public Aluno atualizar(@NotNull @Positive Long id, @Valid @NotNull Aluno aluno) {
        return repository.findById(id)
                .map(alunoEditado -> {
                    validar(aluno);
                    alunoEditado.setNome(aluno.getNome());
                    alunoEditado.setMatricula(aluno.getMatricula());
                    alunoEditado.setCurso(aluno.getCurso());
                    return repository.save(alunoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    public void validar(@Valid @NotNull Aluno aluno) {
        if (aluno.getMatricula() == null || aluno.getMatricula().isEmpty()) {
            throw new DataIntegrityViolationException("Matrícula não pode ser nula");
        }
        if (aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new DataIntegrityViolationException("Nome não pode ser nulo");
        }
        if (aluno.getCurso() == null || aluno.getCurso().isEmpty()) {
            throw new DataIntegrityViolationException("Curso não pode ser nulo");
        }

        if (aluno.getId() != null) {
            Optional<Aluno> optionalAluno = repository.findById(aluno.getId());
            
            if (optionalAluno.isPresent() && !optionalAluno.get().getMatricula().equals(aluno.getMatricula()) && repository.existsByMatricula(aluno.getMatricula())) {
                throw new DataIntegrityViolationException("Matrícula já cadastrada");
            }
        } else {
            if (repository.existsByMatricula(aluno.getMatricula())) {
                throw new DataIntegrityViolationException("Matrícula já cadastrada");
            }
        }
        
    }
}

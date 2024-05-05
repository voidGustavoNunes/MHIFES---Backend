package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.UniqueException;
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
    public Aluno criar(@Valid @NotNull Aluno aluno) {
        
        if (repository.existsByMatricula(aluno.getMatricula())) {
            throw new UniqueException("Matrícula já cadastrada", "matricula");
        }

        return repository.save(aluno);
    }



    @Override
    public Aluno atualizar(@NotNull @Positive Long id, @Valid @NotNull Aluno aluno) {
        return repository.findById(id)
                .map(alunoEditado -> {
                    // Valida se a matrícula já está cadastrada para outro aluno ou se a matrícula é a mesma
                    if(!alunoEditado.getMatricula().equals(aluno.getMatricula()) && repository.existsByMatricula(aluno.getMatricula())) {
                        throw new UniqueException("Matrícula já cadastrada", "matricula");
                    }
                    alunoEditado.setNome(aluno.getNome());
                    alunoEditado.setMatricula(aluno.getMatricula());
                    alunoEditado.setCurso(aluno.getCurso());
                    return repository.save(alunoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

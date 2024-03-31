package com.RFID.MHIFES.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Aluno;
import com.RFID.MHIFES.repository.AlunoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class AlunoService  extends GenericServiceImpl<Aluno, AlunoRepository> {

    public AlunoService(AlunoRepository alunoRepository) {
        super(alunoRepository);
    }

    @Override
    public Aluno atualizar(@NotNull @Positive Long id, @Valid @NotNull Aluno aluno) {
        return repository.findById(id)
                .map(alunoEditado -> {
                    alunoEditado.setNome(aluno.getNome());
                    alunoEditado.setMatricula(aluno.getMatricula());
                    alunoEditado.setCurso(aluno.getCurso());
                    return repository.save(alunoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }



}

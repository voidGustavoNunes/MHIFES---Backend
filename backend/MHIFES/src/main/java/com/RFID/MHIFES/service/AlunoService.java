package com.RFID.MHIFES.service;

import java.util.List;

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
public class AlunoService {

    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(@NotNull @Positive Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Aluno criar(@Valid @NotNull Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(@NotNull @Positive Long id, @Valid @NotNull Aluno aluno) {
        return alunoRepository.findById(id)
                .map(alunoEditado -> {
                    alunoEditado.setNome(aluno.getNome());
                    alunoEditado.setMatricula(aluno.getMatricula());
                    return alunoRepository.save(alunoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        alunoRepository.delete(alunoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}

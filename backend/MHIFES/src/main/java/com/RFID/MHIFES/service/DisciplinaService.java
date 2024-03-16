package com.rfid.mhifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Disciplina;
import com.rfid.mhifes.repository.DisciplinaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> listar() {
        return disciplinaRepository.findAll();
    }

    public Disciplina buscarPorId(@NotNull @Positive Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Disciplina criar(@Valid @NotNull Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina atualizar(@NotNull @Positive Long id, @Valid @NotNull Disciplina disciplina) {
        return disciplinaRepository.findById(id)
                .map(disciplinaEditado -> {
                    disciplinaEditado.setNome(disciplina.getNome());
                    return disciplinaRepository.save(disciplinaEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        disciplinaRepository.delete(disciplinaRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}

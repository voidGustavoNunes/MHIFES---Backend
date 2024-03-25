package com.RFID.MHIFES.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Aluno;
import com.RFID.MHIFES.model.Coordenadoria;
import com.RFID.MHIFES.repository.AlunoRepository;
import com.RFID.MHIFES.repository.CoordenadoriaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CoordenadoriaService {
    
    private CoordenadoriaRepository coordenadoriaRepository;

    public CoordenadoriaService(CoordenadoriaRepository coordenadoriaRepository) {
        this.coordenadoriaRepository = coordenadoriaRepository;
    }

    public List<Coordenadoria> listar() {
        return coordenadoriaRepository.findAll();
    }

    public Coordenadoria buscarPorId(@NotNull @Positive Long id) {
        return coordenadoriaRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Coordenadoria criar(@Valid @NotNull Coordenadoria coordenadoria) {
        return coordenadoriaRepository.save(coordenadoria);
    }

    public Coordenadoria atualizar(@NotNull @Positive Long id, @Valid @NotNull Coordenadoria coordenadoria) {
        return coordenadoriaRepository.findById(id)
                .map(coordenadoriaEditada -> {
                    coordenadoriaEditada.setNome(coordenadoria.getNome());
                    return coordenadoriaRepository.save(coordenadoriaEditada);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        coordenadoriaRepository.delete(coordenadoriaRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}

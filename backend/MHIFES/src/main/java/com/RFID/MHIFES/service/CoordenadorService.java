package com.RFID.MHIFES.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Coordenador;
import com.RFID.MHIFES.repository.CoordenadorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CoordenadorService {

    private CoordenadorRepository coordenadorRepository;

    public CoordenadorService(CoordenadorRepository coordenadorRepository) {
        this.coordenadorRepository = coordenadorRepository;
    }

    public List<Coordenador> listar() {
        return coordenadorRepository.findAll();
    }

    public Coordenador buscarPorId(@NotNull @Positive Long id) {
        return coordenadorRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Coordenador criar(@Valid @NotNull Coordenador coordenador) {
        return coordenadorRepository.save(coordenador);
    }

    public Coordenador atualizar(@NotNull @Positive Long id, @Valid @NotNull Coordenador coordenador) {
        return coordenadorRepository.findById(id)
                .map(coordenadorEditado -> {
                    coordenadorEditado.setNome(coordenador.getNome());
                    coordenadorEditado.setMatricula(coordenador.getMatricula());
                    return coordenadorRepository.save(coordenadorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        coordenadorRepository.delete(coordenadorRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}

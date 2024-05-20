package com.RFID.MHIFES.service;

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
public class CoordenadorService extends GenericServiceImpl<Coordenador, CoordenadorRepository> {

    public CoordenadorService(CoordenadorRepository coordenadorRepository) {
        super(coordenadorRepository);
    }

    @Override
    public Coordenador atualizar(@NotNull @Positive Long id, @Valid @NotNull Coordenador coordenador) {
        return repository.findById(id)
                .map(coordenadorEditado -> {
                    coordenadorEditado.setNome(coordenador.getNome());
                    coordenadorEditado.setMatricula(coordenador.getMatricula());
                    // coordenadorEditado.setCurso(coordenador.getCurso());
                    return repository.save(coordenadorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

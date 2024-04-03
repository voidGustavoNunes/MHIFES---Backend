package com.RFID.MHIFES.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Coordenadoria;
import com.RFID.MHIFES.repository.CoordenadoriaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CoordenadoriaService extends GenericServiceImpl<Coordenadoria, CoordenadoriaRepository> {

    public CoordenadoriaService(CoordenadoriaRepository coordenadoriaRepository) {
        super(coordenadoriaRepository);
    }

    @Override
    public Coordenadoria atualizar(@NotNull @Positive Long id, @Valid @NotNull Coordenadoria coordenadoria) {
        return repository.findById(id)
                .map(coordenadoriaEditada -> {
                    coordenadoriaEditada.setNome(coordenadoria.getNome());
                    return repository.save(coordenadoriaEditada);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

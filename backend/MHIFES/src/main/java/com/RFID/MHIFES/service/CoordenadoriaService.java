package com.rfid.mhifes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Coordenadoria;
import com.rfid.mhifes.repository.CoordenadoriaRepository;

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
                    validar(coordenadoria);
                    coordenadoriaEditada.setNome(coordenadoria.getNome());
                    coordenadoriaEditada.setCoordenador(coordenadoria.getCoordenador());
                    return repository.save(coordenadoriaEditada);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    public void validar(@Valid @NotNull Coordenadoria coordenadoria) {

        if (coordenadoria.getNome() == null || coordenadoria.getNome().isEmpty()) {
            throw new DataIntegrityViolationException("Nome não pode ser nulo");
        }
        
    }
}

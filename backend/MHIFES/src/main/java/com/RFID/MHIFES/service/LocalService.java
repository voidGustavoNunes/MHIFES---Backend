package com.RFID.MHIFES.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Local;
import com.RFID.MHIFES.repository.LocalRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Service
@Validated
public class LocalService {
    
    private LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    public List<Local> listar() {
        return localRepository.findAll();
    }

    public Local buscarPorId(@NotNull @Positive Long id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Local criar(@Valid @NotNull Local local) {
        return localRepository.save(local);
    }

    public Local atualizar(@NotNull @Positive Long id, @Valid @NotNull Local local) {
        return localRepository.findById(id)
                .map(localEditado -> {
                    localEditado.setNome(local.getNome());
                    localEditado.setCapacidade(local.getCapacidade());
                    return localRepository.save(localEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        localRepository.delete(localRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}

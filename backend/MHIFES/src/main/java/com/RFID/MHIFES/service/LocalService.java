package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Local;
import com.rfid.mhifes.repository.LocalRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class LocalService  extends GenericServiceImpl<Local, LocalRepository> {
    
    public LocalService(LocalRepository localRepository) {
        super(localRepository);

    }

    @Override
    public Local atualizar(@NotNull @Positive Long id, @Valid @NotNull Local local) {
        return repository.findById(id)
                .map(localEditado -> {
                    localEditado.setNome(local.getNome());
                    localEditado.setCapacidade(local.getCapacidade());
                    localEditado.setEquipamentos(local.getEquipamentos());
                    return repository.save(localEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

package com.RFID.MHIFES.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.LocalEquipamento;
import com.RFID.MHIFES.repository.LocalEquipamentoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class LocalEquipamentoService extends GenericServiceImpl<LocalEquipamento, LocalEquipamentoRepository> {

    public LocalEquipamentoService(LocalEquipamentoRepository localEquipamentoRepository) {
        super(localEquipamentoRepository);
    }

    @Override
    public LocalEquipamento atualizar(@NotNull @Positive Long id, @Valid @NotNull LocalEquipamento entity) {
        return repository.findById(id)
                .map(localEquipamento -> {
                    localEquipamento.setQuantidade(entity.getQuantidade());
                    return repository.save(localEquipamento);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

}

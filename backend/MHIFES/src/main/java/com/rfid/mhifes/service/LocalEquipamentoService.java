package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.LocalEquipamento;
import com.rfid.mhifes.repository.postgres.LocalEquipamentoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

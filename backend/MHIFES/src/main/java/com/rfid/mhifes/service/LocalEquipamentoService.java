package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.LocalEquipamento;
import com.rfid.mhifes.repository.LocalEquipamentoRepository;
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

    @Override
    public void excluir(@NotNull @Positive Long id) {
        LocalEquipamento localEquipamentoExistente = repository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));

        localEquipamentoExistente.getEquipamento().getLocalEquipamentos().remove(localEquipamentoExistente);
        localEquipamentoExistente.setEquipamento(null);

        repository.delete(localEquipamentoExistente);
    }

}

package com.rfid.mhifes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.model.Local;
import com.rfid.mhifes.model.LocalEquipamento;
import com.rfid.mhifes.repository.EquipamentoRepository;
import com.rfid.mhifes.repository.LocalEquipamentoRepository;
import com.rfid.mhifes.repository.LocalRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class LocalService extends GenericServiceImpl<Local, LocalRepository> {

    private final LocalEquipamentoRepository localEquipamentoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public LocalService(LocalRepository localRepository, LocalEquipamentoRepository localEquipamentoRepository,
            EquipamentoRepository equipamentoRepository) {
        super(localRepository);
        this.localEquipamentoRepository = localEquipamentoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Local criar(@Valid @NotNull Local local, @Valid List<LocalEquipamento> localEquipamentos) {
        
        repository.save(local);

        for(LocalEquipamento localEquipamento : localEquipamentos) {
            localEquipamentoRepository.save(localEquipamento);
        }

        local.setLocalEquipamentos(localEquipamentos);
        repository.save(local);

        return local;
    }

    @Override
    public Local atualizar(@NotNull @Positive Long id, @Valid @NotNull Local local) {
        return repository.findById(id)
                .map(localEditado -> {
                    localEditado.setNome(local.getNome());
                    localEditado.setCapacidade(local.getCapacidade());
                    localEditado.setLocalEquipamentos(local.getLocalEquipamentos());
                    return repository.save(localEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

}

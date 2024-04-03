package com.RFID.MHIFES.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Equipamento;
import com.RFID.MHIFES.model.Local;
import com.RFID.MHIFES.model.LocalDTO;
import com.RFID.MHIFES.model.LocalEquipamento;
import com.RFID.MHIFES.repository.EquipamentoRepository;
import com.RFID.MHIFES.repository.LocalEquipamentoRepository;
import com.RFID.MHIFES.repository.LocalRepository;

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

    public Local criar(@Valid @NotNull LocalDTO localDTO
    // , @Valid List<LocalEquipamento> localEquipamentos
    ) {
        Local local = localDTO.getLocal();
        List<LocalEquipamento> localEquipamentos = localDTO.getLocalEquipamentos();


        Local localSalvo = repository.save(local);

        for(LocalEquipamento localEquipamento : localEquipamentos) {
            localEquipamento.setLocal(localSalvo);
            localEquipamentoRepository.save(localEquipamento);
        }

        localSalvo.setLocalEquipamentos(localEquipamentos);
        repository.save(localSalvo);

        return localSalvo;
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

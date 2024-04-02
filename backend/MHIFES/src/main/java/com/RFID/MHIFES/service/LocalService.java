package com.rfid.mhifes.service;

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

    public Local criar(Local local, List<Equipamento> equipamentos, List<LocalEquipamento> localEquipamentos) {
        Local localSalvo = repository.save(local);

        for (Equipamento equipamento : equipamentos) {
            LocalEquipamento localEquipamento = new LocalEquipamento();
            localEquipamento.setEquipamento(equipamento);
            localEquipamento.setLocal(localSalvo);

            for (LocalEquipamento localEquipamento2 : localEquipamentos) {
                if (localEquipamento2.getEquipamento().getId().equals(equipamento.getId())) {
                    localEquipamento.setQuantidade(localEquipamento2.getQuantidade());
                    break;
                }
            }

            localEquipamentoRepository.save(localEquipamento);

            equipamento.setLocalEquipamentos(localEquipamentos);
            equipamentoRepository.save(equipamento);
        }

        localSalvo.setLocalEquipamentos(localEquipamentos);
        repository.save(localSalvo);

        return localSalvo;
    }

    @Override
    public Local atualizar(Long id, Local entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    // @Override
    // public Local atualizar(@NotNull @Positive Long id, @Valid @NotNull Local
    // local) {
    // return repository.findById(id)
    // .map(localEditado -> {
    // localEditado.setNome(local.getNome());
    // localEditado.setCapacidade(local.getCapacidade());
    // localEditado.setLocalEquipamentos(local.getLocalEquipamentos());
    // return repository.save(localEditado);
    // }).orElseThrow(() -> new RegistroNotFoundException(id));
    // }
}
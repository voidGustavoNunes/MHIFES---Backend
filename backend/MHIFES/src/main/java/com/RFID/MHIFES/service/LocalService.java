package com.rfid.mhifes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Local;
import com.rfid.mhifes.model.postgres.LocalEquipamento;
import com.rfid.mhifes.repository.postgres.LocalRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class LocalService extends GenericServiceImpl<Local, LocalRepository> {

    private final LocalEquipamentoService localEquipamentoService;

    public LocalService(LocalRepository localRepository, LocalEquipamentoService localEquipamentoService) {
        super(localRepository);
        this.localEquipamentoService = localEquipamentoService;
    }

    @Override
    public Local criar(@Valid @NotNull Local local) {
        Local localNovo = repository.save(local);

        for (LocalEquipamento localEquipamento : local.getLocalEquipamentos()) {
            localEquipamento.setLocal(local);
            localEquipamentoService.criar(localEquipamento);
        }

        return localNovo;
    }

    @Transactional
    @Override
    public Local atualizar(@NotNull @Positive Long id, @Valid @NotNull Local local) {
        Local localExistente = repository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));

        localExistente.setNome(local.getNome());
        localExistente.setCapacidade(local.getCapacidade());

        List<LocalEquipamento> equipamentosParaExcluir = new ArrayList<>(localExistente.getLocalEquipamentos());

        for (LocalEquipamento localEquipamentoNovo : local.getLocalEquipamentos()) {
            LocalEquipamento existente = localExistente.getLocalEquipamentos().stream()
                    .filter(e -> e.getId().equals(localEquipamentoNovo.getId()))
                    .findFirst()
                    .orElse(null);
            if (existente != null) {
                existente.setQuantidade(localEquipamentoNovo.getQuantidade());
                equipamentosParaExcluir.remove(existente);
            } else {
                localEquipamentoNovo.setLocal(localExistente);
                localExistente.getLocalEquipamentos().add(localEquipamentoNovo);
            }
        }

        for (LocalEquipamento equipamentoParaExcluir : equipamentosParaExcluir) {
            localExistente.getLocalEquipamentos().remove(equipamentoParaExcluir);
            localEquipamentoService.excluir(equipamentoParaExcluir.getId());
        }

        return repository.save(localExistente);
    }

}

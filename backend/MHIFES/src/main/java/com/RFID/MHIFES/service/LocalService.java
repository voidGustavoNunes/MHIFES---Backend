package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Local;
import com.rfid.mhifes.model.LocalEquipamento;
import com.rfid.mhifes.repository.LocalRepository;

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

    @Override
    public Local atualizar(@NotNull @Positive Long id, @Valid @NotNull Local local) {
        return repository.findById(id)
                .map(localEditado -> {
                    localEditado.setNome(local.getNome());
                    localEditado.setCapacidade(local.getCapacidade());

                    for (LocalEquipamento localEquipamento : localEditado.getLocalEquipamentos()) {
                        if(!local.getLocalEquipamentos().contains(localEquipamento)){
                            localEquipamentoService.excluir(localEquipamento.getId());
                        }
                    }

                    for(LocalEquipamento localEquipamento : local.getLocalEquipamentos()){
                        if(localEditado.getLocalEquipamentos().contains(localEquipamento)){
                            localEquipamentoService.atualizar(localEquipamento.getId(), localEquipamento);
                        } else {
                            localEquipamento.setLocal(localEditado);
                            localEquipamentoService.criar(localEquipamento);
                        }
                    }

                    return repository.save(localEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

}

package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Equipamento;
import com.rfid.mhifes.model.postgres.LocalEquipamento;
import com.rfid.mhifes.repository.postgres.EquipamentoRepository;
import com.rfid.mhifes.repository.postgres.LocalEquipamentoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class EquipamentoService extends GenericServiceImpl<Equipamento, EquipamentoRepository> {

    @Autowired
    private LocalEquipamentoRepository localEquipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        super(equipamentoRepository);
    }

    @Override
    public Equipamento atualizar(@NotNull @Positive Long id, @Valid @NotNull Equipamento equipamento) {
        return repository.findById(id)
                .map(equipamentoEditado -> {
                    equipamentoEditado.setNome(equipamento.getNome());
                    return repository.save(equipamentoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Equipamento equipamento = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        List<LocalEquipamento> locaisEquipamento = localEquipamentoRepository.findByEquipamento(equipamento);

        if (!locaisEquipamento.isEmpty()) {
            throw new ValidationException("Equipamento não pode ser removido pois possui locais vinculados.");
        }

        repository.delete(equipamento);
    }
}

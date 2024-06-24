package com.rfid.mhifes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Equipamento;
import com.rfid.mhifes.repository.postgres.EquipamentoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class EquipamentoService extends GenericServiceImpl<Equipamento, EquipamentoRepository> {

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
	
	public Page<Equipamento> acharNome(String substring, Pageable pageable) {
		return repository.findByNomeContaining(substring, pageable);
	}
}

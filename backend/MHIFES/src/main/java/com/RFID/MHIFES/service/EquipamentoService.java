package com.rfid.mhifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.repository.EquipamentoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class EquipamentoService {

    private EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Equipamento buscarPorId(@NotNull @Positive Long id) {
        return equipamentoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Equipamento criar(@Valid @NotNull Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento atualizar(@NotNull @Positive Long id, @Valid @NotNull Equipamento equipamento) {
        return equipamentoRepository.findById(id)
                .map(equipamentoEditado -> {
                    equipamentoEditado.setNome(equipamento.getNome());
                    return equipamentoRepository.save(equipamentoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        equipamentoRepository.delete(equipamentoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}

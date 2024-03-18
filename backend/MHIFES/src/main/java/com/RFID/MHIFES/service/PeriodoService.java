package com.RFID.MHIFES.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Periodo;
import com.RFID.MHIFES.repository.PeriodoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class PeriodoService {

    private PeriodoRepository periodoRepository;

    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    public List<Periodo> listar() {
        return periodoRepository.findAll();
    }

    public Periodo buscarPorId(@NotNull @Positive Long id) {
        return periodoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Periodo criar(@Valid @NotNull Periodo periodo) {
        return periodoRepository.save(periodo);
    }

    public Periodo atualizar(@NotNull @Positive Long id, @Valid @NotNull Periodo periodo) {
        return periodoRepository.findById(id)
                .map(periodoEditado -> {
                    periodoEditado.setDataInicio(periodo.getDataInicio());
                    periodoEditado.setDataFim(periodo.getDataFim());
                    periodoEditado.setDescricao(periodo.getDescricao());
                    return periodoRepository.save(periodoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        periodoRepository.delete(periodoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}

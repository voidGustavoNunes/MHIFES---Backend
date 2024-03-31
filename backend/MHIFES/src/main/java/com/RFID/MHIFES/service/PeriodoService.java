package com.RFID.MHIFES.service;


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
public class PeriodoService  extends GenericServiceImpl<Periodo, PeriodoRepository> {


    public PeriodoService(PeriodoRepository periodoRepository) {
        super(periodoRepository);

    }

    @Override
    public Periodo atualizar(@NotNull @Positive Long id, @Valid @NotNull Periodo periodo) {
        return repository.findById(id)
                .map(periodoEditado -> {
                    periodoEditado.setDataInicio(periodo.getDataInicio());
                    periodoEditado.setDataFim(periodo.getDataFim());
                    periodoEditado.setDescricao(periodo.getDescricao());
                    return repository.save(periodoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }


}

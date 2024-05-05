package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.PeriodoDisciplina;
import com.rfid.mhifes.repository.PeriodoDisciplinaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class PeriodoDisciplinaService extends GenericServiceImpl<PeriodoDisciplina, PeriodoDisciplinaRepository> {

    public PeriodoDisciplinaService(PeriodoDisciplinaRepository periodoDisciplinaRepository) {
        super(periodoDisciplinaRepository);
    }

    @Override
    public PeriodoDisciplina atualizar(@NotNull @Positive Long id, @Valid @NotNull PeriodoDisciplina entity) {
        return repository.findById(id)
                .map(periodoDisciplina -> {
                    periodoDisciplina.setDisciplina(entity.getDisciplina());
                    periodoDisciplina.setPeriodo(entity.getPeriodo());
                    periodoDisciplina.setAlunos(entity.getAlunos());
                    return repository.save(periodoDisciplina);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

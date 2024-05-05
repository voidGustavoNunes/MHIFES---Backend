package com.rfid.mhifes.service;

import java.time.LocalDate;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Periodo;
import com.rfid.mhifes.model.PeriodoDisciplina;
import com.rfid.mhifes.repository.LocalEquipamentoRepository;
import com.rfid.mhifes.repository.PeriodoDisciplinaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class PeriodoDisciplinaService extends GenericServiceImpl<PeriodoDisciplina, PeriodoDisciplinaRepository> {

    DisciplinaService disciplinaService;

    AlunoService alunoService;

    public PeriodoDisciplinaService(PeriodoDisciplinaRepository periodoDisciplinaRepository,
            DisciplinaService disciplinaService, AlunoService alunoService) {
        super(periodoDisciplinaRepository);
        this.disciplinaService = disciplinaService;
        this.alunoService = alunoService;
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

    @Override
    public void validar(@Valid @NotNull PeriodoDisciplina periodoDisciplina) {

        disciplinaService.buscarPorId(periodoDisciplina.getDisciplina().getId());

        disciplinaService.validar(periodoDisciplina.getDisciplina());

        periodoDisciplina.getAlunos().forEach(aluno -> {
            alunoService.buscarPorId(aluno.getId());
            alunoService.validar(aluno);
        });

    }

}

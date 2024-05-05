package com.rfid.mhifes.service;

import java.time.LocalDate;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Local;
import com.rfid.mhifes.model.LocalEquipamento;
import com.rfid.mhifes.model.Periodo;
import com.rfid.mhifes.model.PeriodoDisciplina;
import com.rfid.mhifes.repository.PeriodoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class PeriodoService extends GenericServiceImpl<Periodo, PeriodoRepository> {

    PeriodoDisciplinaService periodoDisciplinaService;

    public PeriodoService(PeriodoRepository periodoRepository,
            PeriodoDisciplinaService periodoDisciplinaService) {
        super(periodoRepository);
        this.periodoDisciplinaService = periodoDisciplinaService;
    }

    @Override
    public Periodo criar(@Valid @NotNull Periodo periodo) {
        Periodo periodoNovo = repository.save(periodo);

        for (PeriodoDisciplina periodoDisciplina : periodo.getPeriodoDisciplinas()) {
            periodoDisciplina.setPeriodo(periodoNovo);
            periodoDisciplinaService.criar(periodoDisciplina);
        }

        return periodoNovo;
    }

    @Override
    public Periodo atualizar(@NotNull @Positive Long id, @Valid @NotNull Periodo periodo) {
        return repository.findById(id)
                .map(periodoEditado -> {
                    validar(periodo);
                    periodoEditado.setAno(periodo.getAno());
                    periodoEditado.setSemestre(periodo.getSemestre());
                    periodoEditado.setDataInicio(periodo.getDataInicio());
                    periodoEditado.setDataFim(periodo.getDataFim());
                    periodoEditado.setPeriodoDisciplinas(periodo.getPeriodoDisciplinas());
                    return repository.save(periodoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    public void validar(@Valid @NotNull Periodo periodo) {

        if (periodo.getAno() == null) {
            throw new DataIntegrityViolationException("Ano não pode ser nulo");
        }
        if (periodo.getAno().getValue() < 1900) {
            throw new DataIntegrityViolationException("Ano deve ser maior que 1900");
        }
        if (periodo.getSemestre() == null) {
            throw new DataIntegrityViolationException("Semestre não pode ser nulo");
        }
        if (periodo.getSemestre() < 1 || periodo.getSemestre() > 2) {
            throw new DataIntegrityViolationException("Semestre deve ser 1 ou 2");
        }
        if (periodo.getDataInicio() == null) {
            throw new DataIntegrityViolationException("Data de início não pode ser nula");
        }
        if (periodo.getDataFim() == null) {
            throw new DataIntegrityViolationException("Data de fim não pode ser nula");
        }
        if (periodo.getDataFim().isBefore(periodo.getDataInicio())) {
            throw new DataIntegrityViolationException("Data de fim deve ser maior que a data de início");
        }
        if (periodo.getDataFim().isEqual(periodo.getDataInicio())) {
            throw new DataIntegrityViolationException("Data de fim não pode ser igual a data de início");
        }
        if (periodo.getDataInicio().isBefore(LocalDate.parse("1900-01-01"))) {
            throw new DataIntegrityViolationException("Data de início deve ser maior que 01/01/1900");
        }
        if (periodo.getDataFim().isBefore(LocalDate.parse("1900-01-01"))) {
            throw new DataIntegrityViolationException("Data de fim deve ser maior que 01/01/1900");
        }
        if (periodo.getPeriodoDisciplinas() == null || periodo.getPeriodoDisciplinas().isEmpty()) {
            throw new DataIntegrityViolationException("Deve haver pelo menos uma disciplina no período");
        }
        periodo.getPeriodoDisciplinas().forEach(periodoDisciplina -> {
            periodoDisciplinaService.validar(periodoDisciplina);
        });
    }

}

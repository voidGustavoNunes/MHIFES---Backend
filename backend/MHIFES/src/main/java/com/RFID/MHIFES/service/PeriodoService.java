package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Periodo;
import com.rfid.mhifes.model.postgres.PeriodoDisciplina;
import com.rfid.mhifes.repository.postgres.PeriodoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    @Override
    public Periodo atualizar(@NotNull @Positive Long id, @Valid @NotNull Periodo periodo) {
        Periodo periodoEditado = repository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));

        periodoEditado.setAno(periodo.getAno());
        periodoEditado.setSemestre(periodo.getSemestre());
        periodoEditado.setDataInicio(periodo.getDataInicio());
        periodoEditado.setDataFim(periodo.getDataFim());

        List<PeriodoDisciplina> periodoDisciplinasParaExcluir = new ArrayList<>(periodoEditado.getPeriodoDisciplinas());

        for (PeriodoDisciplina periodoDisciplinaNovo : periodo.getPeriodoDisciplinas()) {
            PeriodoDisciplina existente = periodoEditado.getPeriodoDisciplinas().stream()
                    .filter(e -> e.getId().equals(periodoDisciplinaNovo.getId()))
                    .findFirst()
                    .orElse(null);

            if (existente != null) {
                existente.setDisciplina(periodoDisciplinaNovo.getDisciplina());
                existente.setAlunos(periodoDisciplinaNovo.getAlunos());
                periodoDisciplinasParaExcluir.remove(existente);
            } else {
                periodoDisciplinaNovo.setPeriodo(periodoEditado);
                periodoEditado.getPeriodoDisciplinas().add(periodoDisciplinaNovo);
            }
        }

        for (PeriodoDisciplina periodoDisciplinaParaExcluir : periodoDisciplinasParaExcluir) {
            periodoEditado.getPeriodoDisciplinas().remove(periodoDisciplinaParaExcluir);
            periodoDisciplinaService.excluir(periodoDisciplinaParaExcluir.getId());
        }

        return repository.save(periodoEditado);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Periodo periodo = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        List<PeriodoDisciplina> periodosDisciplina = periodo.getPeriodoDisciplinas();

        if (!periodosDisciplina.isEmpty()) {
            for (PeriodoDisciplina periodoDisciplina : periodosDisciplina) {
                periodoDisciplinaService.excluir(periodoDisciplina.getId());
            }
        }

        repository.delete(periodo);
    }

}

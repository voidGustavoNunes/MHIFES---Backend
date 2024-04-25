package com.rfid.mhifes.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.enums.Operacao;
import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Alocacao;
import com.rfid.mhifes.model.Log;
import com.rfid.mhifes.repository.AlocacaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class AlocacaoService extends GenericServiceImpl<Alocacao, AlocacaoRepository> {

    private final LogService logService;

    public AlocacaoService(AlocacaoRepository alocacaoRepository, LogService logService) {
        super(alocacaoRepository);
        this.logService = logService;
    }

    @Override
    public Alocacao criar(@Valid @NotNull Alocacao alocacao) {
        Alocacao alocacaoCriada = repository.save(alocacao);
        Log log = new Log(LocalDate.now(), LocalTime.now(), alocacaoCriada.toString(), Operacao.INCLUSAO,
                alocacaoCriada.getId());
        logService.criar(log);
        return alocacaoCriada;
    }

    @Override
    public Alocacao atualizar(@NotNull @Positive Long id, @Valid @NotNull Alocacao alocacao) {
        Alocacao alocacaoAlterada = repository.findById(id)
                .map(alocacaoEditada -> {
                    // alocacaoEditada.setNumAulas(alocacao.getNumAulas());
                    // alocacaoEditada.setHorario(alocacao.getHorario());
                    alocacaoEditada.setHorarioInicio(alocacao.getHorarioInicio());
                    alocacaoEditada.setHorarioFim(alocacao.getHorarioFim());
                    alocacaoEditada.setTurma(alocacao.getTurma());
                    // alocacaoEditada.setDiaSemana(alocacao.getDiaSemana());
                    // alocacaoEditada.setDataAula(alocacao.getDataAula());
                    alocacaoEditada.setLocal(alocacao.getLocal());
                    alocacaoEditada.setDisciplina(alocacao.getDisciplina());
                    alocacaoEditada.setPeriodo(alocacao.getPeriodo());
                    alocacaoEditada.setProfessor(alocacao.getProfessor());
                    alocacaoEditada.setAlunos(alocacao.getAlunos());
                    return repository.save(alocacaoEditada);
                }).orElseThrow(() -> new RegistroNotFoundException(id));

        Log log = new Log(LocalDate.now(), LocalTime.now(), alocacaoAlterada.toString(), Operacao.ALTERACAO,
                alocacaoAlterada.getId());
        logService.criar(log);
        System.out.println(log);

        return alocacaoAlterada;
    }
}

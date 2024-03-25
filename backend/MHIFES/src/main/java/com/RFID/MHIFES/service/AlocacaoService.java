package com.RFID.MHIFES.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.RFID.MHIFES.exception.RegistroNotFoundException;
import com.RFID.MHIFES.model.Alocacao;
import com.RFID.MHIFES.repository.AlocacaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
@Service
public class AlocacaoService {
    
    private AlocacaoRepository alocacaoRepository;

    public AlocacaoService(AlocacaoRepository alocacaoRepository) {
        this.alocacaoRepository = alocacaoRepository;
    }

    public List<Alocacao> listar() {
        return alocacaoRepository.findAll();
    }

    public Alocacao buscarPorId(@NotNull @Positive Long id) {
        return alocacaoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Alocacao criar(@Valid @NotNull Alocacao alocacao) {
        return alocacaoRepository.save(alocacao);
    }

    public Alocacao atualizar(@NotNull @Positive Long id, @Valid @NotNull Alocacao alocacao) {
        return alocacaoRepository.findById(id)
                .map(alocacaoEditada -> {
                    alocacaoEditada.setHoraFinal(alocacao.getHoraFinal());
                    alocacaoEditada.setHoraInicio(alocacao.getHoraInicio());
                    alocacaoEditada.setTurma(alocacao.getTurma());
                    return alocacaoRepository.save(alocacaoEditada);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        alocacaoRepository.delete(alocacaoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}

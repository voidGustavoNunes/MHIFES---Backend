package com.RFID.MHIFES.service;


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
public class AlocacaoService extends GenericServiceImpl<Alocacao, AlocacaoRepository>  {
    
    public AlocacaoService(AlocacaoRepository alocacaoRepository) {
        super(alocacaoRepository);
    }

    @Override
    public Alocacao atualizar(@NotNull @Positive Long id, @Valid @NotNull Alocacao alocacao) {
        return repository.findById(id)
                .map(alocacaoEditada -> {
                    alocacaoEditada.setHoraFinal(alocacao.getHoraFinal());
                    alocacaoEditada.setHoraInicio(alocacao.getHoraInicio());
                    alocacaoEditada.setTurma(alocacao.getTurma());
                    return repository.save(alocacaoEditada);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }


}


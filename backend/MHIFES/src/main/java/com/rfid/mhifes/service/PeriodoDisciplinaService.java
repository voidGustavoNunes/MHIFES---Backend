package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.DataIntegrityViolationException;
import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.PeriodoDisciplina;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;
import com.rfid.mhifes.repository.postgres.PeriodoDisciplinaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PeriodoDisciplinaService extends GenericServiceImpl<PeriodoDisciplina, PeriodoDisciplinaRepository> {

    @Autowired
    private AlocacaoRepository alocacaoRepository;

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

    @Override
    @Transactional
    public void excluir(Long id) {
        PeriodoDisciplina periodoDisciplina = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        List<Alocacao> alocacoes = alocacaoRepository.findByPeriodoDisciplina(periodoDisciplina);

        if (!alocacoes.isEmpty()) {
            throw new DataIntegrityViolationException("Período não pode ser removido pois possui alocações vinculadas.");
        }

        repository.delete(periodoDisciplina);
    }
}

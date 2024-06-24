package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Disciplina;
import com.rfid.mhifes.model.postgres.PeriodoDisciplina;
import com.rfid.mhifes.repository.postgres.DisciplinaRepository;
import com.rfid.mhifes.repository.postgres.PeriodoDisciplinaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class DisciplinaService extends GenericServiceImpl<Disciplina, DisciplinaRepository> {

    @Autowired
    private PeriodoDisciplinaRepository periodoDisciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        super(disciplinaRepository);

    }

    @Override
    public Disciplina atualizar(@NotNull @Positive Long id, @Valid @NotNull Disciplina disciplina) {
        return repository.findById(id)
                .map(disciplinaEditado -> {
                    disciplinaEditado.setNome(disciplina.getNome());
                    disciplinaEditado.setSigla(disciplina.getSigla());
                    return repository.save(disciplinaEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Disciplina disciplina = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        List<PeriodoDisciplina> periodosDisciplina = periodoDisciplinaRepository.findByDisciplina(disciplina);

        if (!periodosDisciplina.isEmpty()) {
            throw new ValidationException("Disciplina não pode ser removida pois possui períodos vinculados.");
        }

        repository.delete(disciplina);
    }
}

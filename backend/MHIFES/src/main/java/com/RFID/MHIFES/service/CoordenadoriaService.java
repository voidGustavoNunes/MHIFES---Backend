package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Coordenadoria;
import com.rfid.mhifes.model.postgres.Professor;
import com.rfid.mhifes.repository.postgres.CoordenadoriaRepository;
import com.rfid.mhifes.repository.postgres.ProfessorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class CoordenadoriaService extends GenericServiceImpl<Coordenadoria, CoordenadoriaRepository> {

    @Autowired
    private ProfessorRepository professorRepository;

    public CoordenadoriaService(CoordenadoriaRepository coordenadoriaRepository) {
        super(coordenadoriaRepository);
    }

    @Override
    public Coordenadoria criar(@Valid @NotNull Coordenadoria coordenadoria) {
        if (!coordenadoria.getCoordenador().isEhCoordenador()) {
            throw new DataIntegrityViolationException("Coordenador não é coordenador");
        }

        return repository.save(coordenadoria);
    }

    @Override
    public Coordenadoria atualizar(@NotNull @Positive Long id, @Valid @NotNull Coordenadoria coordenadoria) {
        return repository.findById(id)
                .map(coordenadoriaEditada -> {
                    // Verifica se o coordenador é coordenador
                    if (!coordenadoria.getCoordenador().isEhCoordenador()) {
                        throw new DataIntegrityViolationException("Coordenador não é coordenador");
                    }
                    coordenadoriaEditada.setNome(coordenadoria.getNome());
                    coordenadoriaEditada.setCoordenador(coordenadoria.getCoordenador());
                    return repository.save(coordenadoriaEditada);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Coordenadoria coordenadoria = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        if (coordenadoria.getCoordenador() != null) {
            throw new DataIntegrityViolationException("Coordenadoria não pode ser removida pois possui um coordenador vinculado.");
        }

        List<Professor> professores = professorRepository.findByCoordenadoria(coordenadoria);

        if (!professores.isEmpty()) {
            throw new DataIntegrityViolationException("Coordenadoria não pode ser removida pois possui professores vinculados.");
        }

        repository.delete(coordenadoria);
    }

}

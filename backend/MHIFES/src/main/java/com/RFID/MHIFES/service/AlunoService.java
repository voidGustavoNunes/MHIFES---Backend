package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.DataIntegrityViolationException;
import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Aluno;
import com.rfid.mhifes.model.postgres.PeriodoDisciplina;
import com.rfid.mhifes.repository.postgres.AlunoRepository;
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
public class AlunoService extends GenericServiceImpl<Aluno, AlunoRepository> {

    @Autowired
    private PeriodoDisciplinaRepository periodoDisciplinaRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        super(alunoRepository);
    }

    @Override
    public Aluno criar(@Valid @NotNull Aluno aluno) {

        if (repository.existsByMatricula(aluno.getMatricula())) {
            throw new ValidationException("Matrícula já cadastrada");
        }

        return repository.save(aluno);
    }

    @Override
    public Aluno atualizar(@NotNull @Positive Long id, @Valid @NotNull Aluno aluno) {
        return repository.findById(id).map(alunoEditado -> {
            // Valida se a matrícula já está cadastrada para outro aluno ou se a matrícula é a mesma
            if (!alunoEditado.getMatricula().equals(aluno.getMatricula()) && repository
                    .existsByMatricula(aluno.getMatricula())) {
                throw new ValidationException("Matrícula já cadastrada");
            }
            alunoEditado.setNome(aluno.getNome());
            alunoEditado.setMatricula(aluno.getMatricula());
            alunoEditado.setCurso(aluno.getCurso());
            return repository.save(alunoEditado);
        }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Aluno aluno = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        List<PeriodoDisciplina> periodosDisciplina = periodoDisciplinaRepository.findByAlunosContains(aluno);

        if (!periodosDisciplina.isEmpty()) {
            throw new DataIntegrityViolationException("O aluno não pode ser removido pois está vinculado a um ou mais períodos.");
        }

        repository.delete(aluno);
    }
}

package com.rfid.mhifes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.DataIntegrityViolationException;
import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Coordenadoria;
import com.rfid.mhifes.model.postgres.Professor;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;
import com.rfid.mhifes.repository.postgres.CoordenadoriaRepository;
import com.rfid.mhifes.repository.postgres.ProfessorRepository;
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
public class ProfessorService extends GenericServiceImpl<Professor, ProfessorRepository> {

    @Autowired
    private AlocacaoRepository alocacaoRepository;

    @Autowired
    private CoordenadoriaRepository coordenadoriaRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        super(professorRepository);
    }

    @Override
    public Professor criar(@Valid @NotNull Professor professor) {

        if (professor.getCoordenadoria() == null && !professor.isEhCoordenador()) {
            throw new ValidationException("Professor deve estar vinculado a uma coordenadoria");
        }

        if (repository.existsByMatricula(professor.getMatricula())) {
            throw new ValidationException("Matrícula já cadastrada");
        }

        return repository.save(professor);
    }

    @Override
    public Professor atualizar(@NotNull @Positive Long id, @Valid @NotNull Professor professor) {
        if (professor.getCoordenadoria() == null && !professor.isEhCoordenador()) {
            throw new ValidationException("Professor deve estar vinculado a uma coordenadoria");
        }

        return repository.findById(id)
                .map(professorEditado -> {
                    // Valida se a matrícula já está cadastrada para outro professor ou se a
                    // matrícula é a mesma
                    if (!professorEditado.getMatricula().equals(professor.getMatricula())
                            && repository.existsByMatricula(professor.getMatricula())) {
                        throw new ValidationException("Matrícula já cadastrada");
                    }
                    professorEditado.setNome(professor.getNome());
                    professorEditado.setSigla(professor.getSigla());
                    professorEditado.setMatricula(professor.getMatricula());
                    professorEditado.setEhCoordenador(professor.isEhCoordenador());
                    professorEditado.setCoordenadoria(professor.getCoordenadoria());
                    return repository.save(professorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
    
	public Page<Professor> acharNome(String substring, Pageable pageable) {
		return repository.findByNomeContaining(substring, pageable);
	}
	public Page<Professor> acharSigla(String substring, Pageable pageable) {
		return repository.findBySiglaContaining(substring, pageable);
	}
	public Page<Professor> acharMatricula(String substring, Pageable pageable) {
		return repository.findByMatriculaContaining(substring, pageable);
	}

    @Override
    @Transactional
    public void excluir(Long id) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));

        List<Alocacao> alocacoes = alocacaoRepository.findByProfessor(professor);
        if (!alocacoes.isEmpty()) {
            throw new DataIntegrityViolationException("O professor não pode ser removido pois está associado a uma ou várias alocações.");
        }

        Coordenadoria coordenadoria = coordenadoriaRepository.findByCoordenador(professor);

        if (professor.isEhCoordenador() && coordenadoria != null) {
            throw new DataIntegrityViolationException("O professor não pode ser removido pois é coordenador de uma coordenadoria.");
        }

        if (!professor.isEhCoordenador() && professor.getCoordenadoria() != null) {
            throw new DataIntegrityViolationException("O professor não pode ser removido pois está vinculado a uma coordenadoria.");
        }

        repository.delete(professor);
    }
}

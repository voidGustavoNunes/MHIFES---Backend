package com.rfid.mhifes.repository.postgres;

import com.rfid.mhifes.model.postgres.Aluno;
import com.rfid.mhifes.model.postgres.Disciplina;
import com.rfid.mhifes.model.postgres.Periodo;
import com.rfid.mhifes.model.postgres.PeriodoDisciplina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeriodoDisciplinaRepository extends JpaRepository<PeriodoDisciplina, Long> {

    Page<PeriodoDisciplina> findAll(Pageable pageable);

    Optional<PeriodoDisciplina> findByPeriodoAndDisciplina(Periodo periodo, Disciplina disciplina);

    List<PeriodoDisciplina> findByAlunosContains(Aluno aluno);

    List<PeriodoDisciplina> findByDisciplina(Disciplina disciplina);
}

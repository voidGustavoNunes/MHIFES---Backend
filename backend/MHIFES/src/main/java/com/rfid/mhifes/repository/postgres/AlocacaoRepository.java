package com.rfid.mhifes.repository.postgres;

import com.rfid.mhifes.enums.Status;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Horario;
import com.rfid.mhifes.model.postgres.Local;
import com.rfid.mhifes.model.postgres.PeriodoDisciplina;
import com.rfid.mhifes.model.postgres.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

    @Query("SELECT a FROM Alocacao a")
    List<Alocacao> findAll();

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Ativo'")
    List<Alocacao> findAllStatusAtivo();

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Inativo'")
    List<Alocacao> findAllStatusInativo();

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.ano = ?1 AND a.status = 'Ativo'")
    List<Alocacao> findByAno(Year ano);

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.semestre = ?1 AND a.status = 'Ativo'")
    List<Alocacao> findBySemestre(Long semestre);

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.ano = ?1 and p.semestre = ?2 AND a.status = 'Ativo'")
    List<Alocacao> findByAnoAndSemestre(@Param("ano") Year ano, @Param("semestre") Long semestre);

    Page<Alocacao> findAll(Pageable pageable);

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Ativo'")
    Page<Alocacao> findAllStatusAtivo(Pageable pageable);

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Inativo'")
    Page<Alocacao> findAllStatusInativo(Pageable pageable);

    boolean existsByDataAulaAndHorarioAndPeriodoDisciplinaAndStatus(LocalDate dataAtual, Horario horario,
                                                                    PeriodoDisciplina periodoDisciplina, Status status);

    Optional<Alocacao> findByHorarioAndDataAulaAndLocalAndStatus(Horario horario, LocalDate dataAula, Local local, Status status);

    Optional<Alocacao> findByHorarioAndDataAulaAndTurmaAndLocalAndStatus(Horario horario, LocalDate dataAula, String turma, Local local, Status status);

    Optional<Alocacao> findByHorarioAndDataAulaAndProfessorAndLocalAndStatus(Horario horario, LocalDate dataAula, Professor professor, Local local, Status status);

    Optional<Alocacao> findByHorarioAndDataAulaAndPeriodoDisciplinaAndLocalAndStatus(Horario horario, LocalDate dataAula, PeriodoDisciplina periodoDisciplina, Local local, Status status);

    List<Alocacao> findByProfessor(Professor professor);

    List<Alocacao> findByLocal(Local local);

    List<Alocacao> findByHorario(Horario horario);

    List<Alocacao> findByPeriodoDisciplina(PeriodoDisciplina periodoDisciplina);
}

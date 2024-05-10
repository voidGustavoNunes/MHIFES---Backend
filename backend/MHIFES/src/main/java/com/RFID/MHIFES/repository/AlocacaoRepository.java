package com.rfid.mhifes.repository;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rfid.mhifes.model.Alocacao;

public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

    @Query("SELECT a FROM Alocacao a")
    List<Alocacao> findAll();

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Ativo'")
    List<Alocacao> findAllStatusAtivo();

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Inativo'")
    List<Alocacao> findAllStatusInativo();

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.ano = ?1")
    List<Alocacao> findByAno(Year ano);

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.semestre = ?1")
    List<Alocacao> findBySemestre(Long semestre);

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.ano = ?1 and p.semestre = ?2")
    List<Alocacao> findByAnoAndSemestre(Year ano, Long semestre);

}

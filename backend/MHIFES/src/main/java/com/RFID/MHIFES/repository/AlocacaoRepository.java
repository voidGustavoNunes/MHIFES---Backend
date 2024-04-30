package com.rfid.mhifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rfid.mhifes.model.Alocacao;

public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Ativo'")
    List<Alocacao> findAll();

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Inativo'")
    List<Alocacao> findAllStatusInativo();

}

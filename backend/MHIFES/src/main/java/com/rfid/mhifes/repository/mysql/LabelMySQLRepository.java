package com.rfid.mhifes.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.mysql.LabelMySQL;

public interface LabelMySQLRepository extends JpaRepository<LabelMySQL, Long> {

    @Query("SELECT l FROM LabelMySQL l WHERE l.numero = :numero AND l.turno = :turno")
    Optional<LabelMySQL> findFirstByNumeroAndTurno(@Param("numero") Integer numero, @Param("turno") Integer turno);
}

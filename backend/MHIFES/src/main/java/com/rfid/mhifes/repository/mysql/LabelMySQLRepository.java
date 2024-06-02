package com.rfid.mhifes.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.mysql.LabelMySQL;

public interface LabelMySQLRepository extends JpaRepository<LabelMySQL, Long> {

	LabelMySQL findByNumeroAndTurno(Integer numero, Integer turno);
}

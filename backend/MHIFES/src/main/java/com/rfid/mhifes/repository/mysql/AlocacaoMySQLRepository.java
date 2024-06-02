package com.rfid.mhifes.repository.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.mysql.AlocacaoMySQL;

public interface AlocacaoMySQLRepository extends JpaRepository<AlocacaoMySQL, Long> {

	Page<AlocacaoMySQL> findAll(Pageable pageable);
}

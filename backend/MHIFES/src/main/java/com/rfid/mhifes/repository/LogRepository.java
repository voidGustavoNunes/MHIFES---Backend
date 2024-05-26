package com.rfid.mhifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.Log;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByIdRegistro(Long idRegistro);

}

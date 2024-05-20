package com.RFID.MHIFES.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RFID.MHIFES.model.Log;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByIdRegistro(Long idRegistro);

}

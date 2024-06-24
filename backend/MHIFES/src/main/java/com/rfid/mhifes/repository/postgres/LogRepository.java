package com.rfid.mhifes.repository.postgres;

import com.rfid.mhifes.model.postgres.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByIdRegistro(Long idRegistro);

    @Query(value = "SELECT * FROM log WHERE id_registro = ?1 ORDER BY data DESC LIMIT 1", nativeQuery = true)
    Log findLastByIdRegistro(Long id);
}

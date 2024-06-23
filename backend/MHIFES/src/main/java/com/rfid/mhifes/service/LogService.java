package com.rfid.mhifes.service;

import com.rfid.mhifes.model.postgres.Log;
import com.rfid.mhifes.repository.postgres.LogRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class LogService extends GenericServiceImpl<Log, LogRepository> {

    protected LogService(LogRepository logRepository) {
        super(logRepository);
    }

    @Override
    public Log atualizar(@NotNull @Positive Long id, @Valid @NotNull Log entity) {
        // Esse método não irá fazer nada porque não existe atualização de log
        return null;
    }

    public List<Log> buscarLogPorIdRegistro(@NotNull @Positive Long id) {
        return repository.findByIdRegistro(id);
    }

    public Log buscarUltimoLogPorIdRegistro(@NotNull @Positive Long id) {
        return repository.findLastByIdRegistro(id);
    }
}

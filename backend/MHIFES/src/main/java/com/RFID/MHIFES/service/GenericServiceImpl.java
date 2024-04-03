package com.RFID.MHIFES.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RFID.MHIFES.exception.RegistroNotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public abstract class GenericServiceImpl<T, R extends JpaRepository<T, Long>> implements GenericService<T> {

    protected R repository;

    protected GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> listar() {
        return repository.findAll();
    }

    @Override
    public T buscarPorId(@NotNull @Positive Long id) {
        return repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    public T criar(@Valid @NotNull T entity) {
        return repository.save(entity);
    }

    @Override
    public void excluir(@NotNull @Positive Long id) {
        repository.delete(this.repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}

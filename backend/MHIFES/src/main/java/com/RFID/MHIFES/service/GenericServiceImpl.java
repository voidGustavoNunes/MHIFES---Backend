package com.rfid.mhifes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.exception.RegistroNotFoundException;

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
    public Page<T> listar(Pageable pageable) {
        return repository.findAll(pageable);
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

package com.RFID.MHIFES.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RFID.MHIFES.exception.RegistroNotFoundException;

import java.util.List;

public abstract class GenericServiceImpl<T, R extends JpaRepository<T, Long>> implements GenericService<T> {

    protected R repository;

    public GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> listar() {
        return repository.findAll();
    }

    @Override
    public T buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    public T criar(T entity) {
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        repository.delete(this.repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}

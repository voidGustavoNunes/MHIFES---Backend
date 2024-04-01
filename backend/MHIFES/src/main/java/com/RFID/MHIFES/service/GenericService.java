package com.rfid.mhifes.service;

import java.util.List;

public interface GenericService<T> {
    List<T> listar();
    T buscarPorId(Long id);
    T criar(T entity);
    T atualizar(Long id, T entity);
    void excluir(Long id);
}

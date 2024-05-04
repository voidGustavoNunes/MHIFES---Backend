package com.rfid.mhifes.service;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface GenericService<T> {

    List<T> listar();

    T buscarPorId(@NotNull @Positive Long id);

    T criar(@Valid @NotNull T entity);

    T atualizar(@NotNull @Positive Long id, @Valid @NotNull T entity);

    void excluir(@NotNull @Positive Long id);

    void validar(@Valid @NotNull T entity);
}

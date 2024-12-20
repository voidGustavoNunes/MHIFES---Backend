package com.rfid.mhifes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface GenericService<T> {

	// List<T> listar();

	T buscarPorId(@NotNull @Positive Long id);

	T criar(@Valid @NotNull T entity);

	T atualizar(@NotNull @Positive Long id, @Valid @NotNull T entity);

	void excluir(@NotNull @Positive Long id);

	Page<T> listar(Pageable pageable);
}

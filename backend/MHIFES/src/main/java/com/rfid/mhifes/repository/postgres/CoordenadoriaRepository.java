package com.rfid.mhifes.repository.postgres;

import com.rfid.mhifes.model.postgres.Coordenadoria;
import com.rfid.mhifes.model.postgres.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadoriaRepository extends JpaRepository<Coordenadoria, Long> {

    Page<Coordenadoria> findAll(Pageable pageable);

    Coordenadoria findByNome(String nome);

    Coordenadoria findByCoordenador(Professor professor);
}

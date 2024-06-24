package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Coordenador;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;
import com.rfid.mhifes.repository.postgres.CoordenadorRepository;
import com.rfid.mhifes.repository.postgres.CoordenadoriaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class CoordenadorService extends GenericServiceImpl<Coordenador, CoordenadorRepository> {

    @Autowired
    private AlocacaoRepository alocacaoRepository;

    @Autowired
    private CoordenadoriaRepository coordenadoriaRepository;

    public CoordenadorService(CoordenadorRepository coordenadorRepository) {
        super(coordenadorRepository);
    }

    @Override
    public Coordenador atualizar(@NotNull @Positive Long id, @Valid @NotNull Coordenador coordenador) {
        return repository.findById(id)
                .map(coordenadorEditado -> {
                    coordenadorEditado.setNome(coordenador.getNome());
                    coordenadorEditado.setMatricula(coordenador.getMatricula());
                    // coordenadorEditado.setCurso(coordenador.getCurso());
                    return repository.save(coordenadorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

}

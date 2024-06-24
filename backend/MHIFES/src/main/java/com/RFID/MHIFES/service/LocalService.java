package com.rfid.mhifes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Evento;
import com.rfid.mhifes.model.postgres.Local;
import com.rfid.mhifes.model.postgres.LocalEquipamento;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;
import com.rfid.mhifes.repository.postgres.EventoRepository;
import com.rfid.mhifes.repository.postgres.LocalRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class LocalService extends GenericServiceImpl<Local, LocalRepository> {

    @Autowired
    private AlocacaoRepository alocacaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    private final LocalEquipamentoService localEquipamentoService;

    public LocalService(LocalRepository localRepository, LocalEquipamentoService localEquipamentoService) {
        super(localRepository);
        this.localEquipamentoService = localEquipamentoService;
    }

    @Override
    public Local criar(@Valid @NotNull Local local) {
        Local localNovo = repository.save(local);

        for (LocalEquipamento localEquipamento : local.getLocalEquipamentos()) {
            localEquipamento.setLocal(local);
            localEquipamentoService.criar(localEquipamento);
        }

        return localNovo;
    }

    @Transactional
    @Override
    public Local atualizar(@NotNull @Positive Long id, @Valid @NotNull Local local) {
        Local localExistente = repository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));

        localExistente.setNome(local.getNome());
        localExistente.setCapacidade(local.getCapacidade());

        List<LocalEquipamento> equipamentosParaExcluir = new ArrayList<>(localExistente.getLocalEquipamentos());

        for (LocalEquipamento localEquipamentoNovo : local.getLocalEquipamentos()) {
            LocalEquipamento existente = localExistente.getLocalEquipamentos().stream()
                    .filter(e -> e.getId().equals(localEquipamentoNovo.getId()))
                    .findFirst()
                    .orElse(null);
            if (existente != null) {
                existente.setQuantidade(localEquipamentoNovo.getQuantidade());
                equipamentosParaExcluir.remove(existente);
            } else {
                localEquipamentoNovo.setLocal(localExistente);
                localExistente.getLocalEquipamentos().add(localEquipamentoNovo);
            }
        }

        for (LocalEquipamento equipamentoParaExcluir : equipamentosParaExcluir) {
            localExistente.getLocalEquipamentos().remove(equipamentoParaExcluir);
            localEquipamentoService.excluir(equipamentoParaExcluir.getId());
        }

        return repository.save(localExistente);
    }

	public Page<Local> acharNome(String substring, Pageable pageable) {
		return repository.findByNomeContaining(substring, pageable);
	}
	public Page<Local> acharCapacidade(Integer capa, Pageable pageable) {
		return repository.findByCapacidadeContaining(capa, pageable);
	}
    @Override
    @Transactional
    public void excluir(Long id) {
        Local local = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        List<Alocacao> alocacoes = alocacaoRepository.findByLocal(local);

        if (!alocacoes.isEmpty()) {
            throw new ValidationException("Local não pode ser removido pois possui alocações vinculadas.");
        }

        List<Evento> eventos = eventoRepository.findByLocal(local);

        if (!eventos.isEmpty()) {
            throw new ValidationException("Local não pode ser removido pois possui eventos vinculados.");
        }


        repository.delete(local);
    }

}

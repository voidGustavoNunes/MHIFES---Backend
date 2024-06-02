package com.rfid.mhifes.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.enums.Operacao;
import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Log;
import com.rfid.mhifes.model.postgres.Usuario;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class AlocacaoService extends GenericServiceImpl<Alocacao, AlocacaoRepository> {

	private final LogService logService;

	public AlocacaoService(AlocacaoRepository alocacaoRepository, LogService logService) {
		super(alocacaoRepository);
		this.logService = logService;
	}

	@Override
	public Alocacao criar(@Valid @NotNull Alocacao alocacao) {
		alocacao.setDiaSemana(alocacao.getDataAula().getDayOfWeek().getValue());
		Alocacao alocacaoCriada = repository.save(alocacao);

		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Log log = new Log(LocalDateTime.now(), alocacaoCriada.toString(), Operacao.INCLUSAO, alocacaoCriada.getId(),
				usuario);
		logService.criar(log);

		return alocacaoCriada;
	}

	@Override
	public Alocacao atualizar(@NotNull @Positive Long id, @Valid @NotNull Alocacao alocacao) {
		Alocacao alocacaoAlterada = repository.findById(id).map(alocacaoEditada -> {
			alocacaoEditada.setHorario(alocacao.getHorario());
			alocacaoEditada.setTurma(alocacao.getTurma());
			alocacaoEditada.setDataAula(alocacao.getDataAula());
			alocacaoEditada.setDiaSemana(alocacao.getDataAula().getDayOfWeek().getValue());
			alocacaoEditada.setLocal(alocacao.getLocal());
			alocacaoEditada.setPeriodoDisciplina(alocacao.getPeriodoDisciplina());
			alocacaoEditada.setProfessor(alocacao.getProfessor());
			return repository.save(alocacaoEditada);
		}).orElseThrow(() -> new RegistroNotFoundException(id));

		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Log log = new Log(LocalDateTime.now(), alocacaoAlterada.toString(), Operacao.ALTERACAO,
				alocacaoAlterada.getId(), usuario);
		logService.criar(log);

		return alocacaoAlterada;
	}

	@Override
	public void excluir(@NotNull @Positive Long id) {
		Alocacao alocacao = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

		repository.delete(alocacao);

		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Log log = new Log(LocalDateTime.now(), alocacao.toString(), Operacao.EXCLUSAO, alocacao.getId(), usuario);

		logService.criar(log);
	}

	public Page<Alocacao> listarAtivos(Pageable pageable) {
		return repository.findAllStatusAtivo(pageable);
	}

	public Page<Alocacao> listarInativos(Pageable pageable) {
		return repository.findAllStatusInativo(pageable);
	}
}

package com.rfid.mhifes.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.rfid.mhifes.enums.Operacao;
import com.rfid.mhifes.enums.Status;
import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Evento;
import com.rfid.mhifes.model.postgres.Log;
import com.rfid.mhifes.model.postgres.Usuario;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;
import com.rfid.mhifes.repository.postgres.EventoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Validated
@Service
public class AlocacaoService extends GenericServiceImpl<Alocacao, AlocacaoRepository> {

    private final LogService logService;
    private final EventoRepository eventoRepository;

    public AlocacaoService(AlocacaoRepository alocacaoRepository, LogService logService, EventoRepository eventoRepository) {
        super(alocacaoRepository);
        this.logService = logService;
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Alocacao criar(@Valid @NotNull Alocacao alocacao) {
        validarAlocacao(alocacao);

        alocacao.setDiaSemana(alocacao.getDataAula().getDayOfWeek().getValue());
        Alocacao alocacaoCriada = repository.save(alocacao);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logService.criar(new Log(LocalDateTime.now(), alocacaoCriada.toString(), null, Operacao.INCLUSAO, alocacaoCriada.getId(),
                usuario));

        return alocacaoCriada;
    }

    @Override
    public Alocacao atualizar(@NotNull @Positive Long id, @Valid @NotNull Alocacao alocacao) {
        validarAlocacao(alocacao);

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

        Log logAntigo = logService.buscarUltimoLogPorIdRegistro(alocacao.getId());

        Log log = new Log(LocalDateTime.now(), alocacaoAlterada.toString(), logAntigo.getDescricaoNova(), Operacao.ALTERACAO,
                alocacaoAlterada.getId(), usuario);
        logService.criar(log);

        return alocacaoAlterada;
    }

    @Override
    public void excluir(@NotNull @Positive Long id) {
        Alocacao alocacao = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        repository.delete(alocacao);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Log log = new Log(LocalDateTime.now(), null, alocacao.toString(), Operacao.EXCLUSAO, alocacao.getId(), usuario);

        logService.criar(log);
    }

    public Page<Alocacao> listarAtivos(Pageable pageable) {
        return repository.findAllStatusAtivo(pageable);
    }

    public Page<Alocacao> listarInativos(Pageable pageable) {
        return repository.findAllStatusInativo(pageable);
    }

    private void validarAlocacao(Alocacao alocacao) {
        // Verificar se existe alguma alocação ativa com o mesmo horário,
        // na mesma data aula, no mesmo local
        Optional<Alocacao> alocacaoExistente = repository.findByHorarioAndDataAulaAndLocalAndStatus(
                alocacao.getHorario(), alocacao.getDataAula(), alocacao.getLocal(), Status.ATIVO);

        if (alocacaoExistente.isPresent()) {
            throw new ValidationException("Já existe uma alocação ativa cadastrada para o horário (" +
                    alocacaoExistente.get().getHorario().getHoraInicio().toString() + " - " +
                    alocacaoExistente.get().getHorario().getHoraFim().toString() + "), " +
                    "no local " + alocacaoExistente.get().getLocal().getNome() + " e na data " +
                    alocacaoExistente.get().getDataAula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " selecionada.");
        }

        // Verificar se existe alguma alocação ativa com o mesmo horário,
        // na mesma data aula, na mesma turma e no mesmo local
        alocacaoExistente = repository.findByHorarioAndDataAulaAndTurmaAndLocalAndStatus(
                alocacao.getHorario(), alocacao.getDataAula(), alocacao.getTurma(), alocacao.getLocal(), Status.ATIVO);

        if (alocacaoExistente.isPresent()) {
            throw new ValidationException("Já existe uma alocação ativa cadastrada para o horário (" +
                    alocacaoExistente.get().getHorario().getHoraInicio().toString() + " - " +
                    alocacaoExistente.get().getHorario().getHoraFim().toString() + "), " +
                    "na turma " + alocacaoExistente.get().getTurma() + ", no local " +
                    alocacaoExistente.get().getLocal().getNome() + " e na data " +
                    alocacaoExistente.get().getDataAula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " selecionada.");
        }

        // Verificar se existe alguma alocação ativa com o mesmo horário,
        // na mesma data aula, para o mesmo professor e no mesmo local
        alocacaoExistente = repository.findByHorarioAndDataAulaAndProfessorAndLocalAndStatus(
                alocacao.getHorario(), alocacao.getDataAula(), alocacao.getProfessor(), alocacao.getLocal(), Status.ATIVO);

        if (alocacaoExistente.isPresent()) {
            throw new ValidationException("Já existe uma alocação ativa cadastrada para o horário (" +
                    alocacaoExistente.get().getHorario().getHoraInicio().toString() + " - " +
                    alocacaoExistente.get().getHorario().getHoraFim().toString() + "), " +
                    "para o professor " + alocacaoExistente.get().getProfessor().getNome() + ", no local " +
                    alocacaoExistente.get().getLocal().getNome() + " e na data " +
                    alocacaoExistente.get().getDataAula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " selecionada.");
        }

        // Verificar se existe alguma alocação ativa com o mesmo horário,
        // na mesma data aula, para o mesmo período e disciplina e no mesmo local
        alocacaoExistente = repository.findByHorarioAndDataAulaAndPeriodoDisciplinaAndLocalAndStatus(
                alocacao.getHorario(), alocacao.getDataAula(), alocacao.getPeriodoDisciplina(), alocacao.getLocal(), Status.ATIVO);

        if (alocacaoExistente.isPresent()) {
            throw new ValidationException("Já existe uma alocação ativa cadastrada para o horário (" +
                    alocacaoExistente.get().getHorario().getHoraInicio().toString() + " - " +
                    alocacaoExistente.get().getHorario().getHoraFim().toString() + "), " +
                    "para a disciplina " + alocacaoExistente.get().getPeriodoDisciplina().getDisciplina().getNome() + ", no local " +
                    alocacaoExistente.get().getLocal().getNome() + " e na data " +
                    alocacaoExistente.get().getDataAula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " selecionada.");
        }

        // Verificar se existe algum evento com o mesmo horário, na mesma data e no mesmo local
        Optional<Evento> eventoExistente = eventoRepository.findByHorarioAndDataEventoAndLocal(
                alocacao.getHorario(), alocacao.getDataAula(), alocacao.getLocal());

        if (eventoExistente.isPresent()) {
            throw new ValidationException("Já existe um evento cadastrado para o horário (" +
                    eventoExistente.get().getHorario().getHoraInicio().toString() + " - " +
                    eventoExistente.get().getHorario().getHoraFim().toString() + "), " +
                    "no local " + eventoExistente.get().getLocal().getNome() + " e na data " +
                    eventoExistente.get().getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " selecionada.");
        }
    }

	// FILTER ALOCAÇÃO ATIVO
	public Page<Alocacao> acharProfessorAtivo(String substring, Pageable pageable) {
		return repository.findByProfessorContainingAt(substring, pageable);
	}
	public Page<Alocacao> acharLocalAtivo(String substring, Pageable pageable) {
		return repository.findByLocalContainingAt(substring, pageable);
	}
	public Page<Alocacao> acharDisciplinaAtivo(String substring, Pageable pageable) {
		return repository.findByDisciplinaContainingAt(substring, pageable);
	}
	public Page<Alocacao> acharHorarioAtivo(LocalTime time, Pageable pageable) {
		return repository.findByTimeAt(time, pageable);
	}

	// FILTER ALOCAÇÃO INATIVO
	public Page<Alocacao> acharProfessorInativo(String substring, Pageable pageable) {
		return repository.findByProfessorContainingInat(substring, pageable);
	}
	public Page<Alocacao> acharLocalInativo(String substring, Pageable pageable) {
		return repository.findByLocalContainingInat(substring, pageable);
	}
	public Page<Alocacao> acharDisciplinaInativo(String substring, Pageable pageable) {
		return repository.findByDisciplinaContainingInat(substring, pageable);
	}
	public Page<Alocacao> acharHorarioInativo(LocalTime time, Pageable pageable) {
		return repository.findByTimeInat(time, pageable);
	}
}

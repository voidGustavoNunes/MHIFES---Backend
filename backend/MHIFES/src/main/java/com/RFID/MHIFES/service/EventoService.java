package com.rfid.mhifes.service;

import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rfid.mhifes.enums.Status;
import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Evento;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;
import com.rfid.mhifes.repository.postgres.EventoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class EventoService extends GenericServiceImpl<Evento, EventoRepository> {

    private final AlocacaoRepository alocacaoRepository;

    public EventoService(EventoRepository eventoRepository, AlocacaoRepository alocacaoRepository) {
        super(eventoRepository);
        this.alocacaoRepository = alocacaoRepository;
    }

    @Override
    public Evento criar(@Valid @NotNull Evento evento) {
        validarEvento(evento);
        return repository.save(evento);
    }

    @Override
    public Evento atualizar(@NotNull @Positive Long id, @Valid @NotNull Evento evento) {
        validarEvento(evento);
        return repository.findById(id)
                .map(eventoEditado -> {
                    eventoEditado.setNome(evento.getNome());
                    eventoEditado.setDataEvento(evento.getDataEvento());
                    eventoEditado.setDescricao(evento.getDescricao());
                    eventoEditado.setHorario(evento.getHorario());
                    eventoEditado.setLocal(evento.getLocal());
                    return repository.save(eventoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
    
	public Page<Evento> acharNome(String substring, Pageable pageable) {
		return repository.findByNomeContaining(substring, pageable);
	}
	public Page<Evento> acharDia(String substring, Pageable pageable) {
		return repository.findByDiaContaining(substring, pageable);
	}
	public Page<Evento> acharTimeInicio(LocalTime time, Pageable pageable) {
		return repository.findByTimeInicio(time, pageable);
	}

    private void validarEvento(Evento evento) {
        // Verificar se existe alguma alocação ativa com o mesmo horário, na mesma data e no mesmo local
        Optional<Alocacao> alocacaoExistente = alocacaoRepository.findByHorarioAndDataAulaAndLocalAndStatus(
                evento.getHorario(), evento.getDataEvento(), evento.getLocal(), Status.ATIVO);

        if (alocacaoExistente.isPresent()) {
            throw new ValidationException("Já existe uma alocação ativa cadastrada para o horário (" +
                    alocacaoExistente.get().getHorario().getHoraInicio().toString() + " - " +
                    alocacaoExistente.get().getHorario().getHoraFim().toString() + "), " +
                    "no local " + alocacaoExistente.get().getLocal().getNome() + " e na data " +
                    alocacaoExistente.get().getDataAula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " selecionada.");
        }

        // Verificar se existe algum outro evento com o mesmo horário, na mesma data e no mesmo local
        Optional<Evento> eventoExistente = repository.findByHorarioAndDataEventoAndLocal(
                evento.getHorario(), evento.getDataEvento(), evento.getLocal());

        if (eventoExistente.isPresent() && !eventoExistente.get().getId().equals(evento.getId())) {
            throw new ValidationException("Já existe um evento cadastrado para o horário (" +
                    eventoExistente.get().getHorario().getHoraInicio().toString() + " - " +
                    eventoExistente.get().getHorario().getHoraFim().toString() + "), " +
                    "no local " + eventoExistente.get().getLocal().getNome() + " e na data " +
                    eventoExistente.get().getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " selecionada.");
        }
    }
}

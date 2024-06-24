package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Alocacao;
import com.rfid.mhifes.model.postgres.Evento;
import com.rfid.mhifes.model.postgres.Horario;
import com.rfid.mhifes.repository.postgres.AlocacaoRepository;
import com.rfid.mhifes.repository.postgres.EventoRepository;
import com.rfid.mhifes.repository.postgres.HorarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService extends GenericServiceImpl<Horario, HorarioRepository> {

    @Autowired
    private AlocacaoRepository alocacaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        super(horarioRepository);
    }

    @Override
    public Horario atualizar(@NotNull @Positive Long id, @Valid @NotNull Horario horario) {
        return repository.findById(id)
                .map(horarioEditado -> {
                    horarioEditado.setHoraFim(horario.getHoraFim());
                    horarioEditado.setHoraInicio(horario.getHoraInicio());
                    return repository.save(horarioEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void validar(@Valid @NotNull Horario horario) {

        if (horario.getHoraInicio() == null) {
            throw new DataIntegrityViolationException("Hora de início não pode ser nula");
        }

        if (horario.getHoraFim() == null) {
            throw new DataIntegrityViolationException("Hora de fim não pode ser nula");
        }

        if (horario.getHoraInicio().isAfter(horario.getHoraFim())) {
            throw new DataIntegrityViolationException("Hora de início não pode ser maior que a hora de fim");
        }

        if (repository.existsByHoraInicioAndHoraFim(horario.getHoraInicio(), horario.getHoraFim())) {
            throw new DataIntegrityViolationException("Horário já cadastrado");
        }

    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Horario horario = repository.findById(id).orElseThrow(() -> new RegistroNotFoundException(id));

        List<Alocacao> alocacoes = alocacaoRepository.findByHorario(horario);

        if (!alocacoes.isEmpty()) {
            throw new ValidationException("Horário não pode ser removido pois possui alocações vinculadas.");
        }

        List<Evento> eventos = eventoRepository.findByHorario(horario);

        if (!eventos.isEmpty()) {
            throw new ValidationException("Horário não pode ser removido pois possui eventos vinculados.");
        }

        repository.delete(horario);
    }
}

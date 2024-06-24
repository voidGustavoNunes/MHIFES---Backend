package com.rfid.mhifes.service;

import java.time.LocalTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.postgres.Horario;
import com.rfid.mhifes.repository.postgres.HorarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class HorarioService extends GenericServiceImpl<Horario, HorarioRepository> {
    
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

        if(repository.existsByHoraInicioAndHoraFim(horario.getHoraInicio(), horario.getHoraFim())) {
            throw new DataIntegrityViolationException("Horário já cadastrado");
        }

    }
    
	public Page<Horario> acharTimeInicio(LocalTime time, Pageable pageable) {
		return repository.findByTimeInicio(time, pageable);
	}
	public Page<Horario> acharTimeFim(LocalTime time, Pageable pageable) {
		return repository.findByTimeFim(time, pageable);
	}
}

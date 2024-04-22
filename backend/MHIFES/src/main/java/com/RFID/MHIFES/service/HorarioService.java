package com.rfid.mhifes.service;

import org.springframework.stereotype.Service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Horario;
import com.rfid.mhifes.repository.HorarioRepository;

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
                    horarioEditado.setDiaSemana(horario.getDiaSemana());
                    horarioEditado.setHoraFim(horario.getHoraFim());
                    horarioEditado.setHoraInicio(horario.getHoraInicio());
                    return repository.save(horarioEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}

package com.rfid.mhifes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rfid.mhifes.controller.LocalController;
import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.repository.EquipamentoRepository;
import com.rfid.mhifes.repository.LocalEquipamentoRepository;
import com.rfid.mhifes.repository.LocalRepository;

@SpringBootApplication
public class MhifesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MhifesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(EquipamentoRepository equipamentoRepository,
			LocalRepository localRepository, LocalEquipamentoRepository localEquipamentoRepository,
			LocalController localController) {
		return args -> {

			equipamentoRepository.deleteAll();
			Equipamento equipamento = new Equipamento();
			equipamento.setNome("Projetor");
			equipamentoRepository.save(equipamento);

			Equipamento equipamento2 = new Equipamento();
			equipamento2.setNome("Notebook");
			equipamentoRepository.save(equipamento2);
		};
	}
}

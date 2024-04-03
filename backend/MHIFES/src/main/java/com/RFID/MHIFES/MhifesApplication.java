package com.RFID.MHIFES;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.RFID.MHIFES.controller.LocalController;
import com.RFID.MHIFES.model.Equipamento;
import com.RFID.MHIFES.model.Local;
import com.RFID.MHIFES.model.LocalEquipamento;
import com.RFID.MHIFES.repository.EquipamentoRepository;
import com.RFID.MHIFES.repository.LocalEquipamentoRepository;
import com.RFID.MHIFES.repository.LocalRepository;

@SpringBootApplication
public class MhifesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MhifesApplication.class, args);
	}

	// @Bean
	// CommandLineRunner initDatabase(EquipamentoRepository equipamentoRepository,
	// LocalRepository localRepository, LocalEquipamentoRepository localEquipamentoRepository,
	// LocalController localController) {
		// return args -> {
			
		// 	equipamentoRepository.deleteAll();
		// 	Equipamento equipamento = new Equipamento();
		// 	equipamento.setNome("Projetor");
		// 	equipamentoRepository.save(equipamento);

		// 	Equipamento equipamento2 = new Equipamento();
		// 	equipamento2.setNome("Notebook");
		// 	equipamentoRepository.save(equipamento2);

		// 	localRepository.deleteAll();
		// 	Local local = new Local();
		// 	local.setNome("Sala 1");
		// 	local.setCapacidade(50);
		// 	localRepository.save(local);
			
		// 	Local local2 = new Local();
		// 	local2.setNome("Sala 2");
		// 	local2.setCapacidade(30);
		// 	localRepository.save(local2);


		// 	localEquipamentoRepository.deleteAll();
		// 	LocalEquipamento localEquipamento = new LocalEquipamento();
		// 	localEquipamento.setLocal(local);
		// 	localEquipamento.setEquipamento(equipamento);
		// 	localEquipamento.setQuantidade(2);

		// 	LocalEquipamento localEquipamento2 = new LocalEquipamento();
		// 	localEquipamento2.setLocal(local);
		// 	localEquipamento2.setEquipamento(equipamento2);
		// 	localEquipamento2.setQuantidade(1);

		// 	List<LocalEquipamento> localEquipamentos = new ArrayList<>();
		// 	localEquipamentos.add(localEquipamento);
		// 	localEquipamentos.add(localEquipamento2);

		// 	List<Equipamento> equipamentos = new ArrayList<>();
		// 	equipamentos.add(equipamento);
		// 	equipamentos.add(equipamento2);
			
		// 	localController.criar(local, equipamentos, localEquipamentos);
		// 	};
		// }
}

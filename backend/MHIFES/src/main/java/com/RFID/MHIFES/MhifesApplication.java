package com.rfid.mhifes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rfid.mhifes.controller.LocalController;
import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.model.Local;
import com.rfid.mhifes.model.LocalEquipamento;
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

			// localRepository.deleteAll();
			// Local local = new Local();
			// local.setNome("Sala 1");
			// local.setCapacidade(50);
			
			// Local local2 = new Local();
			// local2.setNome("Sala 2");
			// local2.setCapacidade(30);


			// localEquipamentoRepository.deleteAll();
			// LocalEquipamento localEquipamento = new LocalEquipamento();
			// localEquipamento.setLocal(local);
			// localEquipamento.setEquipamento(equipamento);
			// localEquipamento.setQuantidade(2);

			// LocalEquipamento localEquipamento2 = new LocalEquipamento();
			// localEquipamento2.setLocal(local);
			// localEquipamento2.setEquipamento(equipamento2);
			// localEquipamento2.setQuantidade(1);

			// List<LocalEquipamento> localEquipamentos = new ArrayList<>();
			// localEquipamentos.add(localEquipamento);
			// localEquipamentos.add(localEquipamento2);

			// List<Equipamento> equipamentos = new ArrayList<>();
			// equipamentos.add(equipamento);
			// equipamentos.add(equipamento2);

			// local.setLocalEquipamentos(localEquipamentos);
			
			// localController.criar(local);
			// localController.criar(local2);
			};
		}
}

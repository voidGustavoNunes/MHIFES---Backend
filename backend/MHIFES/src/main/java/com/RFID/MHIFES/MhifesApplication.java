package com.RFID.MHIFES;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.RFID.MHIFES.repository.EquipamentoRepository;

@SpringBootApplication
public class MhifesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MhifesApplication.class, args);
	}

}

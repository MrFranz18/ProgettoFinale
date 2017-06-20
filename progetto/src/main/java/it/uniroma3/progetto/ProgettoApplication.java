package it.uniroma3.progetto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import it.uniroma3.progetto.storage.*;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ProgettoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoApplication.class, args);
	}
	

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
            storageService.deleteAll();
            storageService.init();
		};
	}

}

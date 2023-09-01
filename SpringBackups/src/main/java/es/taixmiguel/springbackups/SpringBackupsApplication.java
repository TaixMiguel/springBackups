package es.taixmiguel.springbackups;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.taixmiguel.springbackups.models.StorageService;
import es.taixmiguel.springbackups.repositories.StorageServiceRepository;

@SpringBootApplication
public class SpringBackupsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackupsApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(StorageServiceRepository repository) {
		return (args) -> {
			repository.saveAll(Arrays.asList(new StorageService("LOCAL", "Servidor host")));
		};
	}
}

package es.taixmiguel.springbackups;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBackupsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackupsApplication.class, args);
	}
}

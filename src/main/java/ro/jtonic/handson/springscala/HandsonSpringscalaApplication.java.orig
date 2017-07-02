package ro.jtonic.handson.springscala;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HandsonSpringscalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandsonSpringscalaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return (String[] args) -> System.out.println("Is running");
	}
}

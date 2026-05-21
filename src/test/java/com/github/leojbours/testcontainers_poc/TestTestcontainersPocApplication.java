package com.github.leojbours.testcontainers_poc;

import com.github.leojbours.testcontainers_poc.config.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestTestcontainersPocApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestcontainersPocApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

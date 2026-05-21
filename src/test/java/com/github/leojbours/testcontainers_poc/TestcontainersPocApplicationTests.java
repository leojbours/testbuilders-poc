package com.github.leojbours.testcontainers_poc;

import com.github.leojbours.testcontainers_poc.config.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TestcontainersPocApplicationTests {

	@Test
	void contextLoads() {
	}

}

package com.github.leojbours.testcontainers_poc.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.mysql.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

	@Bean
	// Annotation que fala ao Spring que esse container é um serviço ao qual ele pode se conectar.
	// Por baixo dos panos o Spring vai fazer a configuração da conexão com o banco de dados
	@ServiceConnection
	MySQLContainer mysqlContainer() {
		return new MySQLContainer(DockerImageName.parse("mysql:latest"));
	}

}

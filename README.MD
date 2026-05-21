# Testcontainers POC

POC para demonstrar o uso de **Testcontainers** em testes de repositório com Spring Boot e MySQL.

## O que é Testcontainers?

Testcontainers é uma biblioteca que permite subir docker containers durante a execução de testes. Em vez de usar um banco em memória como H2, os testes rodam em um ambiente simular ao de produção utilizando um banco de dados como o MySQL. Isso garante que o que funciona no teste vai funcionar em produção.

## Tecnologias

- Java 25
- Spring Boot 4
- Spring Data JPA
- MySQL
- Testcontainers
- JUnit 5

## Estrutura do projeto

```
src
├── main
│   └── java
│       └── ...
│           ├── controller        # OrderController, ProductController
│           ├── model             # Order, Product
│           ├── repository        # OrderRepository, ProductRepository
│           └── service           # OrderService, ProductService
└── test
    └── java
        └── ...
            ├── config            # TestcontainersConfiguration
            ├── repository        # OrderRepositoryTest
            └── util              # OrderTestBuilder, ProductTestBuilder
```

## Como os testes funcionam

### `@DataJpaTest`

Sobe um contexto reduzido com somente o necessário para testes de repositório: repositories, EntityManager e datasource. Por ser um contexto fatiado, não faz component scan completo, o que torna os testes mais rápidos. Cada teste roda em uma transação que sofre rollback ao final, mantendo o banco limpo entre os testes.

### `@Import(TestcontainersConfiguration.class)`

Como o `@DataJpaTest` não faz component scan completo, ele não encontra a `TestcontainersConfiguration` automaticamente. O `@Import` força esse bean a entrar no contexto fatiado.

### `TestcontainersConfiguration`

```java
@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    MySQLContainer mysqlContainer() {
        return new MySQLContainer(DockerImageName.parse("mysql:latest"));
    }
}
```

- `@TestConfiguration` — garante que essa configuração só existe no contexto de testes
- `MySQLContainer` — sobe um container Docker com MySQL real em uma porta aleatória
- `@ServiceConnection` — detecta o tipo `MySQLContainer` e autoconfigura o datasource apontando para o container, sem precisar definir nada no `application.properties`

### Ciclo de vida de um teste

```
JUnit inicia o teste
  → Spring sobe o contexto @DataJpaTest
  → @Import força a TestcontainersConfiguration
  → MySQLContainer sobe o container Docker
  → @ServiceConnection configura o DataSource automaticamente
  → Hibernate cria o schema pelas entidades (ddl-auto: create-drop)
  → Teste roda utilizando banco de dados similar ao de produção (MySQL no meu caso)
  → Rollback da transação
  → Spring encerra o contexto
  → Testcontainers para e remove o container
```

### Test Data Builder

Padrão utilizado para facilitar a criação de cenários de teste sem repetir código de setup:

```java
Order order = OrderTestBuilder.builder()
    .withId(null)
    .withTotalPrice(new BigDecimal("100.0"))
    .withCreatedAt(Instant.parse("2026-01-10T10:00:00Z"))
    .build();
```

## Executando os testes

> Requisito: Docker rodando localmente.

```bash
./gradlew test
```

## Executando a aplicação

```bash
./gradlew bootRun
```
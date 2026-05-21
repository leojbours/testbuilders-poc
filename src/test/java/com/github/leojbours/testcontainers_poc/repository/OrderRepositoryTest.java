package com.github.leojbours.testcontainers_poc.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.leojbours.testcontainers_poc.config.TestcontainersConfiguration;
import com.github.leojbours.testcontainers_poc.model.Order;
import com.github.leojbours.testcontainers_poc.util.OrderTestBuilder;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

// @DataJpaTest é utilizado para testes de repositório. Ele irá criar transações com o banco
// de dados para cada teste, e após finalizar o teste, realizará o rollback da transação.
// Além disso, sobe um contexto reduzido com somente o que é necessário para os testes de repositório
@DataJpaTest
// Precisamos colocar o import, pois o JpaTest não vai subir o contexto inteiro da aplicação, ou seja,
// essa classe não vai conhecer a classe de configuração. Com essa annotation forçamos a criação
// do bean de mysql e a autoconfiguração da conexão.
@Import(TestcontainersConfiguration.class)
class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  @Test
  void shouldCountOrdersBetweenDates() {
    Instant startDate = Instant.parse("2026-01-01T00:00:00Z");
    Instant endDate = Instant.parse("2026-01-31T23:59:59Z");

    Order firstOrderInsideRange = OrderTestBuilder.builder()
        .withTotalPrice(new BigDecimal("100.0"))
        .withCreatedAt(Instant.parse("2026-01-10T10:00:00Z"))
        .build();

    Order secondOrderInsideRange = OrderTestBuilder.builder()
        .withTotalPrice(new BigDecimal("200.0"))
        .withCreatedAt(Instant.parse("2026-01-20T10:00:00Z"))
        .build();

    Order orderOutsideRange = OrderTestBuilder.builder()
        .withTotalPrice(new BigDecimal("300.0"))
        .withCreatedAt(Instant.parse("2026-02-10T10:00:00Z"))
        .build();

    orderRepository.save(firstOrderInsideRange);
    orderRepository.save(secondOrderInsideRange);
    orderRepository.save(orderOutsideRange);

    Integer totalOrders = orderRepository.countOrdersBetweenDates(startDate, endDate);

    assertEquals(2, totalOrders);
  }

  @Test
  void shouldSumRevenueBetweenDates() {
    Instant startDate = Instant.parse("2026-01-01T00:00:00Z");
    Instant endDate = Instant.parse("2026-01-31T23:59:59Z");

    Order firstOrderInsideRange = OrderTestBuilder.builder()
        .withTotalPrice(new BigDecimal("100.0"))
        .withCreatedAt(Instant.parse("2026-01-10T10:00:00Z"))
        .build();

    Order secondOrderInsideRange = OrderTestBuilder.builder()
        .withTotalPrice(new BigDecimal("200.0"))
        .withCreatedAt(Instant.parse("2026-01-20T10:00:00Z"))
        .build();

    Order orderOutsideRange = OrderTestBuilder.builder()
        .withTotalPrice(new BigDecimal("300.0"))
        .withCreatedAt(Instant.parse("2026-02-10T10:00:00Z"))
        .build();

    orderRepository.save(firstOrderInsideRange);
    orderRepository.save(secondOrderInsideRange);
    orderRepository.save(orderOutsideRange);

    BigDecimal totalRevenue = orderRepository.sumRevenueBetweenDates(startDate, endDate).orElse(BigDecimal.ZERO);

    assertEquals(0, new BigDecimal("300.0").compareTo(totalRevenue));
  }
}
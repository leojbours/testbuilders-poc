package com.github.leojbours.testcontainers_poc.util;

import com.github.leojbours.testcontainers_poc.model.Order;
import java.math.BigDecimal;
import java.time.Instant;

// Classe para facilitar criação de cenários para testes
public class OrderTestBuilder {

  public static final BigDecimal DEFAULT_TOTAL_PRICE = new BigDecimal("100.00");
  public static final Instant DEFAULT_CREATED_AT = Instant.parse("2026-01-01T00:00:00Z");

  private Long id = null;
  private BigDecimal totalPrice = DEFAULT_TOTAL_PRICE;
  private Instant createdAt = DEFAULT_CREATED_AT;

  private OrderTestBuilder() {
  }

  public static OrderTestBuilder builder() {
    return new OrderTestBuilder();
  }

  public OrderTestBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public OrderTestBuilder withTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public OrderTestBuilder withCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public Order build() {
    Order order = new Order();
    order.setId(id);
    order.setTotalPrice(totalPrice);
    order.setCreatedAt(createdAt);

    return order;
  }
}

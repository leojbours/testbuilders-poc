package com.github.leojbours.testcontainers_poc.service;

import com.github.leojbours.testcontainers_poc.model.Order;
import com.github.leojbours.testcontainers_poc.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order create(Order order) {
    if (order.getCreatedAt() == null) {
      order.setCreatedAt(Instant.now());
    }

    return orderRepository.save(order);
  }

  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  public Integer countOrdersBetweenDates(Instant startDate, Instant endDate) {
    return orderRepository.countOrdersBetweenDates(startDate, endDate);
  }

  public BigDecimal sumRevenueBetweenDates(Instant startDate, Instant endDate) {
    return orderRepository.sumRevenueBetweenDates(startDate, endDate).orElse(BigDecimal.ZERO);
  }
}

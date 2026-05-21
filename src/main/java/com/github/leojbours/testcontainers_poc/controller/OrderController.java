package com.github.leojbours.testcontainers_poc.controller;

import com.github.leojbours.testcontainers_poc.model.Order;
import com.github.leojbours.testcontainers_poc.service.OrderService;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Order> create(@RequestBody Order order) {
    return ResponseEntity.ok(orderService.create(order));
  }

  @GetMapping
  public ResponseEntity<List<Order>> findAll() {
    return ResponseEntity.ok(orderService.findAll());
  }

  @GetMapping("/order-count")
  public ResponseEntity<Integer> countOrdersBetweenDates(
      @RequestParam Instant startDate,
      @RequestParam Instant endDate
  ) {
    return ResponseEntity.ok(orderService.countOrdersBetweenDates(startDate, endDate));
  }

  @GetMapping("/sum-revenue")
  public ResponseEntity<BigDecimal> sumRevenueBetweenDates(
      @RequestParam Instant startDate,
      @RequestParam Instant endDate
  ) {
    return ResponseEntity.ok(orderService.sumRevenueBetweenDates(startDate, endDate));
  }
}

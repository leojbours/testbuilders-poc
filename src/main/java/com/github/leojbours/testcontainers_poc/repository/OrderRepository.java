package com.github.leojbours.testcontainers_poc.repository;

import com.github.leojbours.testcontainers_poc.model.Order;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("SELECT COUNT(o) FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate")
  Integer countOrdersBetweenDates(Instant startDate, Instant endDate);

  @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate")
  Optional<BigDecimal> sumRevenueBetweenDates(Instant startDate, Instant endDate);
}

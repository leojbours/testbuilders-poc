package com.github.leojbours.testcontainers_poc.repository;

import com.github.leojbours.testcontainers_poc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

package com.github.leojbours.testcontainers_poc.service;

import com.github.leojbours.testcontainers_poc.model.Product;
import com.github.leojbours.testcontainers_poc.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product create(Product product) {
    return productRepository.save(product);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }
}

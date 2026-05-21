package com.github.leojbours.testcontainers_poc.controller;

import com.github.leojbours.testcontainers_poc.model.Product;
import com.github.leojbours.testcontainers_poc.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product create(@RequestBody Product product) {
    return productService.create(product);
  }

  @GetMapping
  public List<Product> findAll() {
    return productService.findAll();
  }
}

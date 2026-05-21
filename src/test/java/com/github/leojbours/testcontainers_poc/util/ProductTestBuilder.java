package com.github.leojbours.testcontainers_poc.util;

import com.github.leojbours.testcontainers_poc.model.Product;

// Classe para facilitar criação de cenários para testes
public class ProductTestBuilder {

  public static final String DEFAULT_NAME = "Produto Teste";
  public static final Double DEFAULT_PRICE = 100.0;

  private Long id = null;
  private String name = DEFAULT_NAME;
  private Double price = DEFAULT_PRICE;

  private ProductTestBuilder() {
  }

  public static ProductTestBuilder builder() {
    return new ProductTestBuilder();
  }

  public ProductTestBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public ProductTestBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public ProductTestBuilder withPrice(Double price) {
    this.price = price;
    return this;
  }

  public Product build() {
    Product product = new Product();
    product.setId(id);
    product.setName(name);
    product.setPrice(price);

    return product;
  }
}

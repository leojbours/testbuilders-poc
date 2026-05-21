CREATE TABLE IF NOT EXISTS products(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255),
    price DOUBLE(10,2),
    CONSTRAINT PK_products PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders(
    id BIGINT AUTO_INCREMENT,
    total_price DOUBLE(10,2),
    created_at TIMESTAMP,
    CONSTRAINT PK_orders PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product_order(
    order_id BIGINT,
    product_id BIGINT,
    CONSTRAINT PK_product_order PRIMARY KEY (order_id, product_id),
    CONSTRAINT FK_product_order_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT FK_product_order_product FOREIGN KEY (product_id) REFERENCES products(id)
);
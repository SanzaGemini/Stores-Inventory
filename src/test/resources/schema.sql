-- Creating the products table (assumed for OrderItem's @ManyToOne relationship)
CREATE TABLE product (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Description VARCHAR(255) NOT NULL,
    Price DECIMAL(19, 2) NOT NULL,
    Quantity INT NOT NULL
);

-- Creating the orders table for the Order entity
CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    local_date_time TIMESTAMP,
    total_price DECIMAL(19,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Creating the order_items table for the OrderItem entity
CREATE TABLE order_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL,
    price DECIMAL(19,2),
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

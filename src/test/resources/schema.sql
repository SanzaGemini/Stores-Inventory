CREATE TABLE IF NOT EXISTS Products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description VARCHAR(255) NOT NULL,
    Price DOUBLE NOT NULL,
    Quantity INT NOT NULL
);

INSERT INTO Products (Name, Description, Price, Quantity) VALUES
('Product 1', 'Description of product 1', 19.99, 100),
('Product 2', 'Description of product 2', 29.99, 50);

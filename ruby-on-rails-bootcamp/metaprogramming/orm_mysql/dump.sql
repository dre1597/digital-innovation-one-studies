CREATE DATABASE IF NOT EXISTS orm_mysql;

USE orm_mysql;

CREATE TABLE IF NOT EXISTS customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    phone VARCHAR(15),
    cpf VARCHAR(11) UNIQUE
);

INSERT INTO customers (name, phone, cpf)
VALUES
    ('Customer 1', '123456789', '12345678901'),
    ('Customer 2', '123456789', '12345678902'),
    ('Customer 3', '123456789', '12345678903'),
    ('Customer 4', '123456789', '12345678904'),
    ('Customer 5', '123456789', '12345678905'),
    ('Customer 6', '123456789', '12345678906'),
    ('Customer 7', '123456789', '12345678907'),
    ('Customer 8', '123456789', '12345678908'),
    ('Customer 9', '123456789', '12345678909'),
    ('Customer 10', '123456789', '12345678910');

CREATE TABLE IF NOT EXISTS suppliers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    phone VARCHAR(15),
    cnpj VARCHAR(14) UNIQUE,
    address VARCHAR(255)
);

INSERT INTO suppliers (name, phone, cnpj, address)
VALUES
    ('Supplier 1', '123456789', '12345678901234', 'Address 1'),
    ('Supplier 2', '123456789', '12345678901235', 'Address 2'),
    ('Supplier 3', '123456789', '12345678901236', 'Address 3'),
    ('Supplier 4', '123456789', '12345678901237', 'Address 4'),
    ('Supplier 5', '123456789', '12345678901238', 'Address 5'),
    ('Supplier 6', '123456789', '12345678901239', 'Address 6'),
    ('Supplier 7', '123456789', '12345678901240', 'Address 7'),
    ('Supplier 8', '123456789', '12345678901241', 'Address 8'),
    ('Supplier 9', '123456789', '12345678901242', 'Address 9'),
    ('Supplier 10', '123456789', '12345678901243', 'Address 10');

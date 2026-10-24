
CREATE DATABASE IF NOT EXISTS libros
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE libros;

-- TABLA TIENDA
CREATE TABLE tienda (
    id_tienda INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefono VARCHAR(20),
    ciudad VARCHAR(100) NOT NULL
);

-- TABLA EDITORIAL
CREATE TABLE editorial (
    id_editorial INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    pais VARCHAR(100),
    telefono VARCHAR(20)
);

-- TABLA AUTOR
CREATE TABLE autor (
    id_autor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    nacionalidad VARCHAR(100),
    anio_nacimiento INT
);

-- TABLA LIBRO
CREATE TABLE libro (
    isbn CHAR(13) PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    anio_publicacion INT,
    num_paginas INT,
    precio_catalogo DECIMAL(10,2) NOT NULL,
    editorial_id INT NOT NULL,
    FOREIGN KEY (editorial_id)
        REFERENCES editorial(id_editorial)
);

-- RELACIÓN ESCRIBE: AUTOR Y LIBRO
CREATE TABLE libro_autor (
    isbn CHAR(13) NOT NULL,
    autor_id INT NOT NULL,
    rol VARCHAR(50),
    PRIMARY KEY (isbn, autor_id),
    FOREIGN KEY (isbn) REFERENCES libro(isbn),
    FOREIGN KEY (autor_id) REFERENCES autor(id_autor)
);

-- RELACIÓN INVENTARIO: TIENDA Y LIBRO
CREATE TABLE inventario (
    tienda_id INT NOT NULL,
    isbn CHAR(13) NOT NULL,
    cantidad INT NOT NULL DEFAULT 0,
    fecha_recuento DATE NOT NULL,
    PRIMARY KEY (tienda_id, isbn),
    FOREIGN KEY (tienda_id) REFERENCES tienda(id_tienda),
    FOREIGN KEY (isbn) REFERENCES libro(isbn),
    CHECK (cantidad >= 0)
);

-- TABLA EMPLEADO
CREATE TABLE empleado (
    dni VARCHAR(12) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    tienda_id INT NOT NULL,
    FOREIGN KEY (tienda_id) REFERENCES tienda(id_tienda)
);

-- TABLA CLIENTE
CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    es_socio BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_alta DATE NOT NULL
);

-- TABLA PEDIDO
CREATE TABLE pedido (
    num_pedido INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    forma_pago ENUM('efectivo','tarjeta','bizum') NOT NULL,
    estado ENUM('preparado','entregado','cancelado') NOT NULL,
    total DECIMAL(10,2) NOT NULL DEFAULT 0,
    tienda_id INT NOT NULL,
    empleado_dni VARCHAR(12) NOT NULL,
    cliente_id INT NOT NULL,
    FOREIGN KEY (tienda_id) REFERENCES tienda(id_tienda),
    FOREIGN KEY (empleado_dni) REFERENCES empleado(dni),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id_cliente)
);

-- RELACIÓN CONTIENE: PEDIDO Y LIBRO
CREATE TABLE detalle_pedido (
    pedido_id INT NOT NULL,
    isbn CHAR(13) NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (pedido_id, isbn),
    FOREIGN KEY (pedido_id) REFERENCES pedido(num_pedido),
    FOREIGN KEY (isbn) REFERENCES libro(isbn),
    CHECK (cantidad > 0),
    CHECK (precio_unitario >= 0)
);

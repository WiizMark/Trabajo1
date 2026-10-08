    fecha_contratacion DATE NOT NULL,
    email_trabajo VARCHAR(150) NOT NULL UNIQUE,
    tienda_id INT NOT NULL,
    FOREIGN KEY (tienda_id) REFERENCES tienda(id)
);

CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_completo VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_alta DATE NOT NULL
);

CREATE TABLE pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    metodo_pago ENUM('efectivo','tarjeta','bizum') NOT NULL,
    estado ENUM('preparado','entregado','cancelado') NOT NULL,
    tienda_id INT NOT NULL,
    empleado_dni VARCHAR(12) NOT NULL,
    cliente_id INT NOT NULL,
    FOREIGN KEY (tienda_id) REFERENCES tienda(id),
    FOREIGN KEY (empleado_dni) REFERENCES empleado(dni),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE detalle_pedido (
    pedido_id INT NOT NULL,
    isbn CHAR(13) NOT NULL,
    cantidad INT NOT NULL,
    precio_pagado DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (pedido_id, isbn),
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (isbn) REFERENCES libro(isbn),
    CHECK (cantidad > 0),
    CHECK (precio_pagado >= 0)
);

-- DATOS DE PRUEBA

INSERT INTO tienda (nombre, direccion, telefono, ciudad) VALUES
('Centro', 'Calle Mayor 10', '976 000 111', 'Villa Serena'),
('Ribera', 'Avenida del Río 25', '976 000 222', 'Aldeaverde'),
('Universidad', 'Calle Campus 5', '976 000 333', 'Villa Serena');

INSERT INTO editorial (nombre, pais, telefono_contacto) VALUES
('Alfaguara', 'España', '910 111 111'),
('Alianza', 'España', '910 222 222'),
('Plaza & Janés', 'España', '910 333 333');

INSERT INTO autor (nombre, nacionalidad, anio_nacimiento) VALUES
('Julio Cortázar', 'Argentina', 1914),
('Jorge Luis Borges', 'Argentina', 1899),
('Isabel Allende', 'Chile', 1942),
('Gabriel García Márquez', 'Colombia', 1927);

INSERT INTO libro (isbn, titulo, anio_publicacion, paginas, precio_catalogo, editorial_id) VALUES
('9788420437485', 'Rayuela', 1963, 736, 16.50, 1),
('9788420603310', 'Ficciones', 1944, 176, 12.00, 2),
('9788401351361', 'Cuentos de Eva Luna', 1989, 256, 14.90, 3),
('9788420633133', 'Antología del cuento', 2020, 320, 18.00, 2),
('9788408072534', 'Cien años de soledad', 1967, 496, 19.50, 3);

INSERT INTO libro_autor (isbn, autor_id, tipo_autoria) VALUES
('9788420437485', 1, 'principal'),
('9788420603310', 2, 'principal'),
('9788401351361', 3, 'principal'),
('9788420633133', 1, 'principal'),
('9788420633133', 2, 'colaborador'),
('9788408072534', 4, 'principal');

INSERT INTO inventario (tienda_id, isbn, stock, fecha_ultimo_conteo) VALUES
(1, '9788420437485', 3, '2026-03-02'),
(1, '9788420603310', 0, '2026-03-02'),
(1, '9788401351361', 2, '2026-03-02'),
(1, '9788420633133', 1, '2026-03-02'),
(1, '9788408072534', 0, '2026-03-02'),
(2, '9788420437485', 1, '2026-03-02'),
(2, '9788420603310', 4, '2026-03-02'),
(2, '9788401351361', 0, '2026-03-02'),
(2, '9788420633133', 2, '2026-03-02'),
(2, '9788408072534', 3, '2026-03-02'),
(3, '9788420437485', 4, '2026-03-02'),
(3, '9788420603310', 2, '2026-03-02'),
(3, '9788401351361', 0, '2026-03-02'),
(3, '9788420633133', 6, '2026-02-28'),
(3, '9788408072534', 1, '2026-03-02');

INSERT INTO empleado (dni, nombre, apellidos, cargo, fecha_contratacion, email_trabajo, tienda_id) VALUES
('11111111A', 'Marta', 'López', 'cajero', '2022-05-10', 'marta.lopez@villaserena.es', 1),
('22222222B', 'Carlos', 'Martín', 'librero', '2021-09-01', 'carlos.martin@villaserena.es', 1),
('33333333C', 'Laura', 'Gómez', 'encargado', '2020-02-15', 'laura.gomez@villaserena.es', 2),
('44444444D', 'Diego', 'Sanz', 'cajero', '2023-01-20', 'diego.sanz@villaserena.es', 2),
('55555555E', 'Ana', 'Navarro', 'librero', '2024-06-03', 'ana.navarro@villaserena.es', 3);

INSERT INTO cliente (nombre_completo, email, telefono, fecha_alta) VALUES
('Andrés Pérez', 'andres.p@correo.es', '600 111 111', '2025-10-01'),
('Lucía García', 'lucia.g@correo.es', '600 222 222', '2025-11-12'),
('Pablo Martín', 'pablo.m@correo.es', NULL, '2026-01-10'),
('Sofía Ruiz', 'sofia.r@correo.es', '600 444 444', '2026-02-02');

INSERT INTO pedido (fecha, metodo_pago, estado, tienda_id, empleado_dni, cliente_id) VALUES
('2026-03-12 10:30:00', 'tarjeta', 'entregado', 1, '11111111A', 1),
('2026-03-13 17:15:00', 'efectivo', 'entregado', 1, '22222222B', 2),
('2026-03-15 12:00:00', 'bizum', 'entregado', 2, '33333333C', 1),
('2026-03-18 18:20:00', 'tarjeta', 'entregado', 3, '55555555E', 1),
('2026-03-20 11:10:00', 'tarjeta', 'entregado', 1, '11111111A', 1),
('2026-03-21 16:40:00', 'efectivo', 'preparado', 2, '33333333C', 3);

INSERT INTO detalle_pedido (pedido_id, isbn, cantidad, precio_pagado) VALUES
(1, '9788420437485', 1, 16.50),
(1, '9788420603310', 2, 12.00),
(1, '9788401351361', 1, 10.00),
(2, '9788420603310', 1, 12.00),
(2, '9788420633133', 1, 18.00),
(3, '9788408072534', 2, 19.50),
(3, '9788420437485', 1, 16.50),
(4, '9788420633133', 2, 18.00),
(4, '9788401351361', 1, 14.90),
(5, '9788420603310', 3, 12.00),
(5, '9788420437485', 1, 16.50),
(6, '9788408072534', 1, 19.50),
(6, '9788420603310', 1, 12.00);

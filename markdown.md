# Base de datos

# Páginas de Villa Serena.

## 1. Resumen del caso

Las paginas de Villa Serena. Es una librería que dispone de tres tiendas, la de Centro, Ribera y Universidad. Ahora mismo la información se lleva en hojas de calculo separadas, por lo cual es más dificil conocer con precision el stock disponible en cada tienda y poder consultar las ventas.
La librería también necesita una base de datos central que permita almacenar las tiendas, libros, editoriales, autores, inventario, empleados, clientes y pedidos. También debe conservar el precio real cobrado en cada linea de pedido, ya que el precio de catálogo cambia con el tiempo.
El sistema tiene que permitir consultar el stock por tienda, analizar las ventas, conocer la facturación, localizar clientes con frecuencia, comparar existencias entre tiendas, saber qué empleados atienden más pedidos y detectar los que aparecen publicados por distintas editoriales.

---

## 2. Analisis del caso

### 2.1 Entidades y atributos

**Tienda**
Atributos: Id, nombre, dirección, telefono y ciudad.
Fragmento del caso: Cada una tiene su nombre, su dirección, su teléfono y su ciudad.

**Libro**
Atributos: ISBN, titulo, año de publicacion, paginas, precio de catalogo y editorial.
Fragmento del caso: Cada libro se identifica por su ISBN, titulo, año de publicación, numero de paginas y precio de catalogo.

**Editorial**
Atributos: Id, nombre, país y telefono de contacto.
Fragmento del caso: De cada editorial anotan su nombre, el país y un telefono de contacto.

**Autor**
Atributos: Id, nombre, nacionalidad y año de nacimiento.
Fragmento del caso:  De cada autor guardamos el nombre, la nacionalidad y el año de nacimiento.

**Inventario**
Atributos: tienda, libro, stock y fecha del ultimo conteo.
Fragmento del caso: Para cada libro y cada tienda sepa cuantas copias hay, y cuando se contó por ultima vez.

**Empleado**
Atributos: DNI, nombre, apellidos, cargo, fecha de contratación, correo y tienda.
Fragmento del caso: De cada empleado trabaja en una sola tienda, y se indican sus datos.

**Cliente**
Atributos: id, nombre completo, email, telefono y fecha de alta.
Fragmento del caso: Datos de socios y clientes registrados.

**Pedido**
Atributos: id, fecha, metodo de pago, estado, tienda, empleado y cliente.
Fragmento del caso: Un pedido se hace siempre en una tienda, lo atiende un empleado y lo compra un cliente.

**Detalle de pedido**
Atributos: pedido, libro, cantidad y precio pagado.
Fragmento del caso: Cada pedido puede llevar varios libros distintos, y se necesita guardar lo realmente cobrado.

**Libro Autor**
Atributos: libro, autor y tipo de autoría.
Fragmento del caso: Un libro puede tener varios autores y se distingue entre principal y colaborador.

### 2.2 Datos descartados

**Total del pedido**
Decision: No se almacena.
Motivo: Se puede calcular sumando cantidad * precio_pagado de sus lineas.

**Telefono de la tienda dentro del pedido**
Decision: No se repite.
Motivo: El pedido ya referencia a la tienda y el telefono pertenece a ella.

**Nombre del empleado dentro del pedido**
Decision: No se repite.
Motivo: Se obtiene con la FK empleado_dni.

**Nombre del cliente dentro del pedido**
Decision: No se repite.
Motivo: Se obtiene xcon la FK cliente_id.

**Stock en una columna del libro**
Decision: No se almacena.
Motivo: El stock depende de cada tienda y se guarda en inventario.

**Precio actual como precio historico del pedido**
Decision: No se utiliza.
Motivo: El precio de catalogo puede cambiar, el pedido conserva precio_pagado.

**Historial de cambios de tienda de un empleado**
Decision: No se almacena.
Motivo: El caso indica expresamente que no interesa conservar ese historial.

---

## 3. Reglas de negocio

1. Una tienda tiene un nombre, una dirección, un telefono y la ciudad.
2. Un libro se identifica mediante el ISBN, tiene 13 cifras.
3. Cada libro pertenece a una unica editorial.
4. Una editorial puede publicar muchos libros.
5. Un libro puede tener varios autores y un autor puede participar en muchos libros.
6. Para cada relación libro autor se debe indicar si el autor es principal o colaborador.
7. El inventario se controla por una combinación de tienda y libro.
8. Si un libro no esta presente en una tienda, no es hace falta crear una fila de inventario para esa combinación.
9. El stock nunca estara en negativo.
10. Cada empleado trabajara en una sola tienda.
11. El cargo de un empleado solo puede ser librero, cajero o encargado.
12. El correo electronico de cada cliente es unico.
13. El telefono del cliente es opcional.
14. Cada pedido pertenece a una unica tienda, lo atiende un unico empleado y pertenece a un unico cliente.
15. El metodo de pago solo puede ser efectivo, tarjeta o bizum.
16. El estado de un pedido solo puede ser preparado, entregado o cancelado.
17. Cada pedido debe tener libros distintos en sus lineas.
18. La cantidad de cada linea debe ser mayor que cero.
19. Cada linea de pedido conserva el precio realmente pagado.
20. El precio pagado no puede estar en negativo.

---

## 4. Diagrama entidad-relación

![Diagrama](erdEsquema.png)

### Tabla de relaciones

| Relación | Tipo | Cómo se resuelve |
|---|---|---|
| tienda – empleado | 1:N | empleado.tienda_id |
| tienda – inventario | 1:N | inventario.tienda_id |
| libro – inventario | 1:N | inventario.isbn |
| editorial – libro | 1:N | libro.editorial_id |
| libro – autor | N:M | Tabla libro_autor |
| tienda – pedido | 1:N | pedido.tienda_id |
| empleado – pedido | 1:N | pedido.empleado_dni |
| cliente – pedido | 1:N | pedido.cliente_id |
| pedido – libro | N:M | Tabla detalle_pedido |

---

## 5. Modelo lógico

### `tienda`

| Columna | Clave | Referencia |
|---|---|---|
| id | PK | |
| nombre | | |
| direccion | | |
| telefono | | |
| ciudad | | |

### `editorial`

| Columna | Clave | Referencia |
|---|---|---|
| id | PK | |
| nombre | | |
| pais | | |
| telefono_contacto | | |

### `autor`

| Columna | Clave | Referencia |
|---|---|---|
| id | PK | |
| nombre | | |
| nacionalidad | | |
| anio_nacimiento | | |

### `libro`

| Columna | Clave | Referencia |
|---|---|---|
| isbn | PK | |
| titulo | | |
| anio_publicacion | | |
| paginas | | |
| precio_catalogo | | |
| editorial_id | FK | editorial.id |

### `libro_autor`

| Columna | Clave | Referencia |
|---|---|---|
| isbn | PK, FK | libro.isbn |
| autor_id | PK, FK | autor.id |
| tipo_autoria | | |

### `inventario`

| Columna | Clave | Referencia |
|---|---|---|
| tienda_id | PK, FK | tienda.id |
| isbn | PK, FK | libro.isbn |
| stock | | |
| fecha_ultimo_conteo | | |

### `empleado`

| Columna | Clave | Referencia |
|---|---|---|
| dni | PK | |
| nombre | | |
| apellidos | | |
| cargo | | |
| fecha_contratacion | | |
| email_trabajo | | |
| tienda_id | FK | tienda.id |

### `cliente`

| Columna | Clave | Referencia |
|---|---|---|
| id | PK | |
| nombre_completo | | |
| email | | |
| telefono | | |
| fecha_alta | | |

### `pedido`

| Columna | Clave | Referencia |
|---|---|---|
| id | PK | |
| fecha | | |
| metodo_pago | | |
| estado | | |
| tienda_id | FK | tienda.id |
| empleado_dni | FK | empleado.dni |
| cliente_id | FK | cliente.id |

### `detalle_pedido`

| Columna | Clave | Referencia |
|---|---|---|
| pedido_id | PK, FK | pedido.id |
| isbn | PK, FK | libro.isbn |
| cantidad | | |
| precio_pagado | | |

---
## 6. Script SQL (schema.sql)

El archivo `schema.sql` contiene la creación de la base de datos, las tablas, restricciones y datos de prueba.

La estructura se crea en orden de dependencia: primero las entidades independientes y después las tablas que contienen claves foráneas.

```sql
CREATE DATABASE IF NOT EXISTS libreria;
USE libreria;
```

El script completo se entrega junto a este documento como `schema.sql`.

---
## 7. Diccionario de datos

### tienda

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| id | INT | Sí | Identificador único de la tienda. |
| nombre | VARCHAR(50) | Sí | Nombre de la tienda. No puede repetirse. |
| direccion | VARCHAR(150) | Sí | Dirección física. |
| telefono | VARCHAR(20) | Sí | Teléfono de la tienda. |
| ciudad | VARCHAR(80) | Sí | Ciudad donde se encuentra. |

### editorial

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| id | INT | Sí | Identificador único de la editorial. |
| nombre | VARCHAR(120) | Sí | Nombre de la editorial. No puede repetirse. |
| pais | VARCHAR(80) | Sí | País de la editorial. |
| telefono_contacto | VARCHAR(20) | Sí | Teléfono usado para contactar con la editorial. |

### autor

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| id | INT | Sí | Identificador único del autor. |
| nombre | VARCHAR(120) | Sí | Nombre completo del autor. |
| nacionalidad | VARCHAR(80) | Sí | Nacionalidad del autor. |
| anio_nacimiento | SMALLINT | Sí | Año de nacimiento. |

### libro

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| isbn | CHAR(13) | Sí | ISBN de 13 cifras que identifica el libro. |
| titulo | VARCHAR(200) | Sí | Título del libro. |
| anio_publicacion | SMALLINT | Sí | Año de publicación. |
| paginas | INT | Sí | Número de páginas, mayor que cero. |
| precio_catalogo | DECIMAL(10,2) | Sí | Precio actual de catálogo en euros. |
| editorial_id | INT | Sí | Editorial que publica el libro. FK a editorial. |

### libro_autor

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| isbn | CHAR(13) | Sí | Libro relacionado. Parte de la PK y FK. |
| autor_id | INT | Sí | Autor relacionado. Parte de la PK y FK. |
| tipo_autoria | ENUM | Sí | Indica si el autor es principal o colaborador. |

### inventario

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| tienda_id | INT | Sí | Tienda donde se cuenta el stock. |
| isbn | CHAR(13) | Sí | Libro contado. |
| stock | INT | Sí | Número de copias disponibles. No puede ser negativo. |
| fecha_ultimo_conteo | DATE | Sí | Fecha del último recuento. |

### empleado

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| dni | VARCHAR(12) | Sí | Identificador del empleado. PK. |
| nombre | VARCHAR(80) | Sí | Nombre del empleado. |
| apellidos | VARCHAR(120) | Sí | Apellidos del empleado. |
| cargo | ENUM | Sí | Librero, cajero o encargado. |
| fecha_contratacion | DATE | Sí | Fecha de contratación. |
| email_trabajo | VARCHAR(150) | Sí | Correo laboral único. |
| tienda_id | INT | Sí | Tienda donde trabaja actualmente. |

### cliente

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| id | INT | Sí | Identificador interno del cliente. |
| nombre_completo | VARCHAR(150) | Sí | Nombre completo. |
| email | VARCHAR(150) | Sí | Correo único del cliente. |
| telefono | VARCHAR(20) | No | Teléfono, si el cliente lo proporciona. |
| fecha_alta | DATE | Sí | Fecha en que se registra el cliente. |

### pedido

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| id | INT | Sí | Identificador del pedido. |
| fecha | DATETIME | Sí | Fecha y hora del pedido. |
| metodo_pago | ENUM | Sí | Efectivo, tarjeta o bizum. |
| estado | ENUM | Sí | Preparado, entregado o cancelado. |
| tienda_id | INT | Sí | Tienda donde se realiza el pedido. |
| empleado_dni | VARCHAR(12) | Sí | Empleado que atiende el pedido. |
| cliente_id | INT | Sí | Cliente que realiza el pedido. |

### detalle_pedido

| Columna | Tipo | Obligatorio | Descripción |
|---|---|:---:|---|
| pedido_id | INT | Sí | Pedido al que pertenece la línea. |
| isbn | CHAR(13) | Sí | Libro incluido en el pedido. |
| cantidad | INT | Sí | Número de ejemplares. Debe ser mayor que cero. |
| precio_pagado | DECIMAL(10,2) | Sí | Precio realmente cobrado por ejemplar en ese pedido. No puede ser negativo. |

---

## 8. Decisiones de diseño

### 8.1 Guardar el precio pagado en detalle_pedido

**Que se decidfo=** cada linea de pedido guarda `precio_pagado`.

**Por que=** el precio de catlogo puede cambiar. El caso pone como ejemplo que Ficciones paso de 10 € a 12 €, por lo que un pedido antiguo debe conservar el importe.

**Alternativa descartada=** consultar siempre `libro.precio_catalogo` para calcular el importe del pedido.

### 8.2 Crear libro_autor

**Que se decidio:** la relacion entre libros y autores se representa mediante una tabla intermedia.

**Por que=** un libro puede tener dos o tres autores y un autor puede participar en muchos libros. Ademas, la relacion tiene los datos propio `tipo_autoria`.

**Alternativa descartada:** almacenar varios autores en una sola columna de `libro`, por ejemplo `Cortazar / Borges`, porque dificultaria las busquedas y no permitiria distinguir correctamente la autoria.
### 8.3 Crear inventario por tienda y libro

**Que se decidio=** el stock se almacena en una tabla cuya clave primaria es la combinacion de `tienda_id` y `isbn`.

**Por que=** un mismo libro puede tener cantidades diferentes en cada tienda.

**Alternativa descartada=** guardar un unico campo `stock` en `libro`, porque no permitiria saber en que tienda estan las copias.

### 8.4 No guardar el total del pedido

**Que se decidio=** no crear una columna `total` en `pedido`.

**Por que=** el total se obtiene sumando `cantidad * precio_pagado` de sus lineas. Al ser un dato derivado, se evita duplicar informacion.

**Alternativa descartada=** guardar el total y actualizarlo cada vez que cambia una linea, porque podria provocar problemas.

### 8.5 No guardar el historial de tiendas de los empleados

**Que se decidio=** `empleado` contiene unicamente la tienda en la que trabaja actualmente.

**Por que=** el caso indica que no interesa guardar el historial de cambios de tienda.

**Alternativa descartada=** crear una tabla historica de asignaciones de empleados a tiendas.

### 8.6 Usar DECIMAL(10,2) para los precios

**Que se decidio=** los precios se almacenan con `DECIMAL(10,2)`.

**Por que=** los importes monetarios necesitan conservar exactamente dos decimales.

**Alternativa descartada=** usar tipos de coma flotante como `FLOAT`, que no son apropiados para representar importes monetarios con precision exacta.

---

## 9. Datos de prueba

Los datos de prueba incluyen 3 tiendas, 3 editoriales, 4 autores, 5 libros, relaciones con varios autores, inventario de las tres tiendas, 5 empleados, 4 clientes y 6 pedidos con varias lineas.
Se han utilizado como referencia los datos proporcionados en la hoja de Universidad y el ticket del caso.
El archivo schema.sql contiene todos los Insert en el orden correcto para respetar las claves foraneas.

---

## 10. Consultas de prueba

### 10.1 ¿Qué libros tiene la tienda Centro y cuántas copias quedan?

```sql
SELECT l.titulo, i.stock
FROM inventario i
JOIN libro l ON l.isbn = i.isbn
JOIN tienda t ON t.id = i.tienda_id
WHERE t.nombre = 'Centro'
ORDER BY l.titulo;
```

### 10.2 ¿Cuál es el libro más vendido en cada tienda?

```sql
WITH ventas AS (
    SELECT
        t.nombre AS tienda,
        l.titulo,
        SUM(d.cantidad) AS unidades_vendidas,
        RANK() OVER (
            PARTITION BY t.id
            ORDER BY SUM(d.cantidad) DESC
        ) AS posicion
    FROM pedido p
    JOIN tienda t ON t.id = p.tienda_id
    JOIN detalle_pedido d ON d.pedido_id = p.id
    JOIN libro l ON l.isbn = d.isbn
    WHERE p.estado <> 'cancelado'
    GROUP BY t.id, t.nombre, l.isbn, l.titulo
)
SELECT tienda, titulo, unidades_vendidas
FROM ventas
WHERE posicion = 1
ORDER BY tienda;
```
### 10.3 ¿Cuánto ha facturado cada tienda este año?

```sql
SELECT
    t.nombre AS tienda,
    ROUND(SUM(d.cantidad * d.precio_pagado), 2) AS facturacion
FROM pedido p
JOIN tienda t ON t.id = p.tienda_id
JOIN detalle_pedido d ON d.pedido_id = p.id
WHERE YEAR(p.fecha) = 2026
  AND p.estado = 'entregado'
GROUP BY t.id, t.nombre
ORDER BY t.nombre;
```sql

### 10.4 ¿Qué clientes han hecho más de dos pedidos?

```sql
SELECT
    c.nombre_completo,
    COUNT(p.id) AS numero_pedidos
FROM cliente c
JOIN pedido p ON p.cliente_id = c.id
GROUP BY c.id, c.nombre_completo
HAVING COUNT(p.id) > 2
ORDER BY numero_pedidos DESC;
```




### 10.5 ¿Qué libros están agotados en una tienda pero disponibles en otra?

```sql
SELECT
    l.titulo,
    GROUP_CONCAT(
        CASE WHEN i.stock = 0 THEN t.nombre END
        ORDER BY t.nombre SEPARATOR ', '
    ) AS agotado_en,
    GROUP_CONCAT(
        CASE WHEN i.stock > 0 THEN t.nombre END
        ORDER BY t.nombre SEPARATOR ', '
    ) AS disponible_en
FROM libro l
JOIN inventario i ON i.isbn = l.isbn
JOIN tienda t ON t.id = i.tienda_id
GROUP BY l.isbn, l.titulo
HAVING SUM(i.stock = 0) > 0
   AND SUM(i.stock > 0) > 0
ORDER BY l.titulo;
```



### 10.6 ¿Qué empleado ha atendido más pedidos?

```sql
WITH conteo AS (
    SELECT
        e.dni,
        e.nombre,
        e.apellidos,
        COUNT(p.id) AS pedidos_atendidos
    FROM empleado e
    JOIN pedido p ON p.empleado_dni = e.dni
    GROUP BY e.dni, e.nombre, e.apellidos
)
SELECT nombre, apellidos, pedidos_atendidos
FROM conteo
WHERE pedidos_atendidos = (SELECT MAX(pedidos_atendidos) FROM conteo);
```


### 10.7 ¿Qué autores tienen libros en más de una editorial?

```sql
SELECT
    a.nombre,
    COUNT(DISTINCT l.editorial_id) AS numero_editoriales
FROM autor a
JOIN libro_autor la ON la.autor_id = a.id
JOIN libro l ON l.isbn = la.isbn
GROUP BY a.id, a.nombre
HAVING COUNT(DISTINCT l.editorial_id) > 1
ORDER BY a.nombre;
```


---


## 11. Limitaciones y mejoras futuras

- No se guarda el historial de cambios de la tienda de los empleados, por si en el futuro se necesitara, podria crearse una tabla de asignaciones historicas.
- No se guarda el historial de cambios del precio de catalogo. Podria añadirse una tabla de precios con fecha de inicio y fin.
- No se controla automaticamente que el empleado que atiende un pedido pertenezca a la tienda del pedido. Se podria añadir una validación mediante logica de aplicación o algunos mecanismos especificos de la base de datos.
- No se almacenan datos especificos sobre la condición de socio. Podrian añadirse si la libreria necesitara gestionar promociones.
- No se registran devoluciones de pedidos. En el futuro podria añadirse un sistema de devoluciones.
- No se controla el stock automaticamente al insertar un pedido. Una aplicación podria actualizar el inventario al confirmar una venta.
- El modelo registra clientes mediante nombre y email, pero no distingue si son socios. Si la condición de socio pasa a tener más información propia, seria más conveniente crear una estructura especifica para ella.



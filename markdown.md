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
Fragmento del caso para cada libro y cada tienda sepa cuantas copias hay, y cuando se contó por ultima vez.

**Empleado**
Atributos: DNI, nombre, apellidos, cargo, fecha de contratación, correo y tienda.
Fragmento del caso  cada empleado trabaja en una sola tienda, y se indican sus datos.

**Cliente**
Atributos: id, nombre completo, email, telefono y fecha de alta.
Fragmento del caso datos de socios y clientes registrados.

**Pedido**
Atributos: id, fecha, metodo de pago, estado, tienda, empleado y cliente.
Fragmento del caso Un pedido se hace siempre en una tienda, lo atiende un empleado y lo compra un cliente.

**Detalle de pedido**
Atributos: pedido, libro, cantidad y precio pagado.
Fragmento del caso Cada pedido puede llevar varios libros distintos, y se necesita guardar lo realmente cobrado.

**Libro Autor**
Atributos: libro, autor y tipo de autoría.
Fragmento del caso un libro puede tener varios autores y se distingue entre principal y colaborador.
# Estructura del Proyecto - Microservicios

Este proyecto está basado en una arquitectura de microservicios, con las siguientes características:

- **Frontend**: Implementado con Angular para la aplicación web y Flutter para la aplicación móvil.
- **Gateway**: Utilizamos un API Gateway con Spring Cloud Gateway para gestionar todas las peticiones entrantes.
- **Eureka**: Servicio de descubrimiento de microservicios con Spring Cloud Netflix Eureka.
- **Microservicios**:
  - `usuario-service`: Gestiona la autenticación y manejo de usuarios.
  - `inventario-service`: Controla la gestión del inventario de máquinas y materiales.
  - `pedidos-service`: Gestiona los pedidos y el uso de materiales.
  - `prestamo-service`: Gestiona los préstamos de materiales.
- **Base de Datos**: Usamos MySQL como base de datos principal, y phpMyAdmin para la gestión de la base de datos.

## Gráfico de la arquitectura de microservicios para el proyecto:
[![Proyecto-de-Fab-Lab-Microservicios.png](https://i.postimg.cc/VLnXbTM3/Proyecto-de-Fab-Lab-Microservicios.png)](https://postimg.cc/ppVmwssZ)



# Data sintética para la base de datos para realizar pruebas y testeo

Para agregar datos de ejemplo a la base de datos y realizar pruebas, puedes ejecutar los siguientes queries:

```sql

-- Creación de cargos
INSERT INTO cargo (cargo_id, nombre) VALUES (1, 'ESTUDIANTE');
INSERT INTO cargo (cargo_id, nombre) VALUES (2, 'CLIENTE');

-- Creación de personas
INSERT INTO persona (persona_id, apellido, codigo, email, fecha_nacimiento, nombre, cargo_id) VALUES (1, 'Orihuela', '20254124', 'luis.orihuela@upeu.edu.pe', '2000-08-24','Luis', 1);
INSERT INTO persona (persona_id, apellido, codigo, email, fecha_nacimiento, nombre, cargo_id) VALUES (2, 'Ramos', '20254155', 'arturo.ramos@upeu.edu.pe', '1999-08-24','Arturo', 1);
INSERT INTO persona (persona_id, apellido, codigo, email, fecha_nacimiento, nombre, cargo_id) VALUES (3, 'Ruidiaz Flores', 'juan.ruidiaz@upeu.edu.pe', '20254120','2000-08-24','Juan', 1);
INSERT INTO persona (persona_id, apellido, codigo, email, fecha_nacimiento, nombre, cargo_id) VALUES (4, 'Quispe Mamani', 'esau.quispe@upeu.edu.pe', '20254127','2001-08-24','Esau', 1);

-- Creación de usuarios
INSERT INTO usuario (usuario_id, password, username, persona_id) VALUES (1, '123456', 'luisbeto', 1);
INSERT INTO usuario (usuario_id, password, username, persona_id) VALUES (2, '123456', 'arturo', 2);
INSERT INTO usuario (usuario_id, password, username, persona_id) VALUES (3, '123456', 'juanxd', 3);
INSERT INTO usuario (usuario_id, password, username, persona_id) VALUES (4, '123456', 'esauquispe', 4);

-- Creación de categoria
INSERT INTO categoria (categoria_id, nombre) VALUES (1, 'Ploteo');
INSERT INTO categoria (categoria_id, nombre) VALUES (2, 'Corte Laser');
INSERT INTO categoria (categoria_id, nombre) VALUES (3, 'Impresion');

-- Creación de maquinas
INSERT INTO maquina (maquina_id, nombre, fecha_compra, precio, codigo_upeu, estado, porc_desperdicio, tipo_cotizacion, activo, categoria_id) VALUES (1, 'Maquina1', '2024-08-20', 5500.20, '124512', 'activo', 20.2, '', 1, 1);
INSERT INTO maquina (maquina_id, nombre,  fecha_compra, precio, codigo_upeu, estado, porc_desperdicio, tipo_cotizacion, activo, categoria_id) VALUES (2, 'Maquina2', '2024-08-20', 6500.20, '124513', 'activo', 20.2, '', 1, 2);
INSERT INTO maquina (maquina_id, nombre, fecha_compra, precio, codigo_upeu, estado, porc_desperdicio, tipo_cotizacion, activo, categoria_id) VALUES (3, 'Maquina3', '2024-08-20', 7500.20, '124514', 'activo', 20.2, '', 1, 3);

-- Creación de Marca
INSERT INTO marca (marca_id, activo, nombre) VALUES (1, 1, 'Ranger');
INSERT INTO marca (marca_id, activo, nombre) VALUES (2, 1, 'Celtic');
INSERT INTO marca (marca_id, activo, nombre) VALUES (3, 0, 'Glasgow');

-- Creación de Insumo
INSERT INTO insumo (insumo_id, activo, descripcion, dimension, div_unidad, nombre, observacion, precio_venta, categoria_id, marca_id) VALUES (1, 1,'','','','Papel','',30.5, 1,1);
INSERT INTO insumo (insumo_id, activo, descripcion, dimension, div_unidad, nombre, observacion, precio_venta, categoria_id, marca_id) VALUES (2, 1,'','','','Carton','',40.5, 1,1);
INSERT INTO insumo (insumo_id, activo, descripcion, dimension, div_unidad, nombre, observacion, precio_venta, categoria_id, marca_id) VALUES (3, 1,'','','','Madera','',50.5, 1,1);

-- Creación de Materiales
INSERT INTO material (material_id, activo, cantidad, codigo_upeu, nombre) VALUES (1, 1, 50, 'DFD32', 'Guincha');
INSERT INTO material (material_id, activo, cantidad, codigo_upeu, nombre) VALUES (2, 1, 10, 'SXC12', 'Dron');
INSERT INTO material (material_id, activo, cantidad, codigo_upeu, nombre) VALUES (3, 1, 50, 'LKJ55', 'Tijera');
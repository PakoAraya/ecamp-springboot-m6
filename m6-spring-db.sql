
-- CREACION DE TABLAS MODULO 6 SPRING
--Creacion de la tabla de productos
CREATE TABLE producto (
    id SERIAL PRIMARY KEY,         -- ID autogenerado
    nombre VARCHAR(100) NOT NULL,  -- Nombre del producto
    descripcion TEXT,              -- Descripción del producto
    precio DECIMAL(10, 2) NOT NULL,-- Precio con hasta 2 decimales
    en_stock BOOLEAN NOT NULL      -- Indica si el producto está en stock
);

-- Carga de data de ejemplo
INSERT INTO producto (nombre, descripcion, precio, en_stock)
VALUES
('Laptop', 'Laptop de 15 pulgadas con procesador Intel i7', 1200.00, true),
('Teclado', 'Teclado mecánico con retroiluminación RGB', 80.50, true),
('Mouse', 'Mouse inalámbrico ergonómico', 45.99, false),
('Monitor', 'Monitor de 24 pulgadas Full HD', 200.00, true),
('Cargador', 'Cargador universal para laptops', 25.75, false);

SELECT * FROM producto ;

-- Creacion de tabla de usuarios
CREATE TABLE usuario(
	id 			serial PRIMARY KEY,
	nombre 	varchar(100) NOT NULL,
	email		varchar(100) unique NOT NULL,
	edad 		int NOT null 
);

-- Carga de data para tabla usuarios
INSERT INTO usuario (nombre, email, edad) VALUES 
('Aytor Senna', 'ayrtonsenna@mclaren.com', 34),
('Michael Schumacher', 'mschumacher@ferrari.com', 32),
('Sergio Perez', 'checo@redbull.com', 31),
('Max Versttapen', 'mversttapen@redbull.com', 28),
('Lewis Hamilton', 'lhamilton@ferrari.com', 32);



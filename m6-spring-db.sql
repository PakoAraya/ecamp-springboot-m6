
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
/*
CREATE TABLE usuario(
	id 			serial PRIMARY KEY,
	nombre 	varchar(100) NOT NULL,
	email		varchar(100) unique NOT NULL,
	edad 		int NOT null 
); */

CREATE TABLE usuario(
	id				serial PRIMARY KEY,
	nombre 		varchar(100) NOT NULL,
	email 		varchar(100) NOT NULL,
	edad 			integer NOT NULL,
	activo		boolean NOT NULL
);

-- Carga de data para tabla usuarios
INSERT INTO usuario (nombre, email, edad, activo) VALUES 
('Ayrton Senna', 'ayrtonsenna@mclaren.com', 34, false),
('Michael Schumacher', 'mschumacher@ferrari.com', 32, false),
('Sergio Perez', 'checo@redbull.com', 31, true),
('Max Verstappen', 'mverstappen@redbull.com', 28, true),
('Lewis Hamilton', 'lhamilton@ferrari.com', 32, true),
('Mariah Carey', 'mcarey@bootcamp.cl', 31, true),
('Karol G', 'karolg@bootcamp.cl', 28, true),
('Taylor Swift', 'tswift@bootcamp.cl', 32, false);

SELECT * FROM usuario u ;



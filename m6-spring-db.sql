
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
('Taylor Swift', 'tswift@bootcamp.cl', 32, false),
('Milton Millas', 'mmillas@mclaren.com', 14, false),
('Michael Bootas', 'mbottas@ferrari.com', 12, false),
('Sergio Gonzalez', 'sgonzalez@bootcamp.cl', 10, true);

SELECT * FROM usuario u ;

SELECT * FROM usuario u 
WHERE u.email like 'lhamilton@ferrari.com';

SELECT count(*) FROM usuario u WHERE u.activo = TRUE; 

SELECT * FROM usuario u 
WHERE u.id = 1;

CREATE TABLE categoria(
    id serial PRIMARY KEY,
    nombre varchar(100) UNIQUE NOT NULL
);

ALTER TABLE producto 
    ADD COLUMN categoria_id BIGINT,
    ADD CONSTRAINT fk_productos_categorias
        FOREIGN KEY (categoria_id)
        REFERENCES categoria(id);

INSERT INTO categoria (nombre) VALUES
('Electrodomesticos'),
('Muebles'),
('Computacion');

UPDATE producto
SET categoria_id = 3
WHERE id IN (1,2,3,4,5);

INSERT INTO producto (nombre, descripcion, precio, en_stock, categoria_id)
VALUES
('Silla', 'Silla de comedor', 200.00, TRUE, 2),
('Mesa', 'Mesa de comedor', 80.50, TRUE, 2),
('Comoda', 'Comoda para guardar ropa', 45.99, FALSE, 2),
('Lavadora', 'Lavadora Samsung Epic IA', 450.00, TRUE, 1),
('Refrigerador', 'Refrigerador Midea con IA', 500.75, FALSE, 1);

SELECT * FROM categoria c;
SELECT * FROM producto p ;

-- Creacion de tabla para Spring Security
CREATE TABLE usuario_login(
	id 			serial PRIMARY KEY,
	username	varchar(50) UNIQUE NOT NULL,
	password 	varchar(100) UNIQUE NOT NULL,
	role			varchar(20) NOT null 
);

INSERT INTO usuario_login  (username, PASSWORD, role)
VALUES ('admin', 'admin123', 'ADMIN'),
			 ('user', 'user123', 'USER');

SELECT * FROM usuario_login ;




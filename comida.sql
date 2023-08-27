CREATE DATABASE comida
USE comida

CREATE TABLE pedido(
	Id int AUTO_INCREMENT PRIMARY KEY,
	nombre varchar(20),
	precio int(50),
	plato varchar(50)
);
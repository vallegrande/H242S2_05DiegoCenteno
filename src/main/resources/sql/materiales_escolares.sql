CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

INSERT INTO usuarios (username, password) VALUES ('admin', 'admin');

DROP TABLE IF EXISTS materiales_escolares;
CREATE TABLE IF NOT EXISTS materiales_escolares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    categoria VARCHAR(50) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    cantidad_unidad INT NOT NULL,
    cantidad_docena INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock_minimo INT DEFAULT 1,
    estado VARCHAR(20) DEFAULT 'Activo',
    fecha_registro DATETIME NOT NULL,
    eliminado_logico BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS marca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
); 
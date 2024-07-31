CREATE DATABASE libreria;
USE libreria;

CREATE TABLE Genero(
		codigoGenero INT not null auto_increment,
    PRIMARY KEY(codigoGenero),
    descripcion VARCHAR(20)
);

CREATE TABLE Libro(
		codigoLibro INT not null auto_increment,
    PRIMARY KEY(codigoLibro),
    titulo VARCHAR(45),
    fechaPublicacion VARCHAR (10),
    precio DOUBLE,
    codigoGenero INT,
    CONSTRAINT FK_id_LibroGenero FOREIGN KEY (codigoGenero) REFERENCES Genero(codigoGenero)
);
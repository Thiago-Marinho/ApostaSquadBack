CREATE DATABASE ApostaSquad
GO

USE ApostaSquad
GO

CREATE TABLE Time (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome varchar(255) NOT NULL
)
GO

CREATE TABLE Estadio (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	descricao varchar(255) NOT NULL
)
GO

CREATE TABLE Cliente (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome varchar(255) NOT NULL
)
GO

CREATE TABLE Partida (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	data datetime NOT NULL,
	descricao varchar(255) NOT NULL,
	id_estadio int NOT NULL REFERENCES Estadio(id)
)
GO

CREATE TABLE Situacao (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	descricao varchar(255) NOT NULL
)
GO

CREATE TABLE Aposta (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	valor decimal NOT NULL,
	descricao varchar(255) NOT NULL,
	id_cliente int NOT NULL REFERENCES Cliente(id),
	id_situacao int NOT NULL REFERENCES Situacao(id)
)
GO
CREATE TABLE TimePartida (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	resultado bit NOT NULL,
	id_partida int NOT NULL REFERENCES Partida(id),
	id_time int NOT NULL REFERENCES Time(id)
)
GO

CREATE TABLE ResultadoAposta (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	status_time bit NOT NULL,
	id_aposta int NOT NULL REFERENCES Aposta(id),
	id_time_partida int NOT NULL REFERENCES TimePartida(id)
)
GO
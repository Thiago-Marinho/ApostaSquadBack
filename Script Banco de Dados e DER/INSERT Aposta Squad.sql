
/*Inserindo na tabela Time*/
INSERT INTO Time(nome)values('São Paulo')
INSERT INTO Time(nome)values('Corinthians')
INSERT INTO Time(nome)values('Palmeiras')
INSERT INTO Time(nome)values('Santos')

/*Inserindo na tabela Estadio*/
INSERT INTO Estadio(descricao)values('Vila Belmiro')
INSERT INTO Estadio(descricao)values('Morumbi')
INSERT INTO Estadio(descricao)values('Allianz Parque')
INSERT INTO Estadio(descricao)values('Neo Química Arena')

/*Inserindo na tabela Cliente*/
INSERT INTO Cliente(nome)values('Lucas ')
INSERT INTO Cliente(nome)values('Marcus')
INSERT INTO Cliente(nome)values('Thiago')
INSERT INTO Cliente(nome)values('Diogo')

/*Inserindo na tabela Partida*/
INSERT INTO Partida(descricao, data, id_estadio)values('Partida 1', '2021/10/10', 3)
INSERT INTO Partida(descricao, data, id_estadio)values('Partida 2', '2021/10/10', 2)

/*Inserindo na tabela Situacao*/
INSERT INTO Situacao(descricao)values('Pendente')
INSERT INTO Situacao(descricao)values('Acertou')
INSERT INTO Situacao(descricao)values('Errou')

/*Inserindo na tabela Situacao*/
INSERT INTO Aposta(descricao, valor, id_cliente, id_situacao)values('Jogo Palmeiras x Corinthians', 50, 1, 3)
INSERT INTO Aposta(descricao, valor, id_cliente, id_situacao)values('Jogo Santos x São Paulo', 100, 2, 2)

/*Inserindo na tabela TimePartida*/
INSERT INTO TimePartida(resultado, id_partida, id_time)values(0, 1, 3)
INSERT INTO TimePartida(resultado, id_partida, id_time)values(0, 1, 2)
INSERT INTO TimePartida(resultado, id_partida, id_time)values(0, 2, 1)
INSERT INTO TimePartida(resultado, id_partida, id_time)values(1, 2, 4)

/*Inserindo na tabela ResultadoAposta*/
INSERT INTO ResultadoAposta(id_aposta, id_time_partida, status_time)values(1, 1, 1)
INSERT INTO ResultadoAposta(id_aposta, id_time_partida, status_time)values(1, 2, 0)
INSERT INTO ResultadoAposta(id_aposta, id_time_partida, status_time)values(2, 3, 0)
INSERT INTO ResultadoAposta(id_aposta, id_time_partida, status_time)values(2, 4, 1)

select * from aposta
select * from Cliente
select * from Estadio
select * from Partida
select * from ResultadoAposta
select * from Situacao
select * from Time
select * from TimePartida


create table especialidade (id bigserial not null primary key, nome varchar(300), vagas integer default 0);
create table proprietario (cpf varchar(11) not null primary key, nome varchar(200), telefone varchar(20),
						   endereco varchar(300));
create table mecanico (id bigserial not null primary key, nome varchar(200), valor_hora numeric(18,2), 
					   id_especialidade bigint not null references especialidade(id));
create table veiculo (placa varchar(7) not null primary key, modelo varchar(300), ano integer,
					  cpf_proprietario varchar(11) not null references proprietario(cpf));
create table agendamento(id bigserial not null primary key, data date, 
						 periodo char(1) default 'I', reclamacao varchar(500), 
						 placa varchar(7) references veiculo(placa),
						 id_especialidade bigint not null references especialidade(id),
						 id_mecanico bigint not null references mecanico(id));
						 
insert into especialidade (nome, vagas) values ('Elétrica', 6);
insert into especialidade (nome, vagas) values ('Motor', 3);
insert into especialidade (nome, vagas) values ('Suspensão',4);
insert into especialidade (nome, vagas) values ('Freios', 6);

insert into proprietario values ('12345678900','Juca Silva','(45)3278-9384','Rua Tal, 1234 - Toledo - PR');
insert into proprietario values ('23456789101','Ana Cardoso','(45)99823-3961','Rua Qualquer, 784 - Toledo - PR');
insert into proprietario values ('34567891202','Bianca Soares','(45)3279-6473','Rua Sobedesce, 1221 - Toledo - PR');
insert into proprietario values ('45678912303','Zeca Pedreira','(45)99648-3174','Av Principal, 348 - Cascavel - PR');

insert into veiculo values ('ABC1234','Corsa Wind', 2014, '12345678900');
insert into veiculo values ('BCD2345','Ka+', 2018, '12345678900');
insert into veiculo values ('ACX1B35','Monza', 1994, '45678912303');
insert into veiculo values ('BAC3D81','Palio', 2016, '23456789101');
insert into veiculo values ('CBD9812','Sandero 1.6', 2017, '34567891202');

insert into mecanico (nome, valor_hora, id_especialidade) values ('Pedro Cardoso', 15.50, 1);
insert into mecanico (nome, valor_hora, id_especialidade) values ('João Silva', 17.50, 1);
insert into mecanico (nome, valor_hora, id_especialidade) values ('Ana Clara Soares', 17.40, 2);
insert into mecanico (nome, valor_hora, id_especialidade) values ('Paulo Silva', 15.50, 2);
insert into mecanico (nome, valor_hora, id_especialidade) values ('Carlos Oliveira', 18.90, 3);
insert into mecanico (nome, valor_hora, id_especialidade) values ('Antonio Carlos Machado', 17.50, 4);

insert into agendamento(data, periodo, placa, reclamacao, id_especialidade, id_mecanico) values ('2023-12-04','M','ABC1234','Luz alta nao funciona',1,1);
insert into agendamento(data, periodo, placa, reclamacao, id_especialidade, id_mecanico) values ('2023-12-04','I','BCD2345','Demora a pegar, falha',1,2);
insert into agendamento(data, periodo, placa, reclamacao, id_especialidade, id_mecanico) values ('2023-12-05','V','ACX1B35','Barulho quando passa uma lombada',3,4);

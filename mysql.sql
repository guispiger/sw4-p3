CREATE TABLE especialidade (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nome VARCHAR(300),
    vagas INT DEFAULT 0
);

CREATE TABLE proprietario (
    cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    nome VARCHAR(200),
    telefone VARCHAR(20),
    endereco VARCHAR(300)
);

CREATE TABLE mecanico (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nome VARCHAR(200),
    valor_hora NUMERIC(18,2),
    id_especialidade BIGINT NOT NULL,
    FOREIGN KEY (id_especialidade) REFERENCES especialidade(id)
);

CREATE TABLE veiculo (
    placa VARCHAR(7) NOT NULL PRIMARY KEY,
    modelo VARCHAR(300),
    ano INT,
    cpf_proprietario VARCHAR(11) NOT NULL,
    FOREIGN KEY (cpf_proprietario) REFERENCES proprietario(cpf)
);

CREATE TABLE agendamento (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    data DATE,
    periodo CHAR(1) DEFAULT 'I',
    reclamacao VARCHAR(500),
    placa VARCHAR(7),
    id_especialidade BIGINT NOT NULL,
    id_mecanico BIGINT NOT NULL,
    FOREIGN KEY (placa) REFERENCES veiculo(placa),
    FOREIGN KEY (id_especialidade) REFERENCES especialidade(id),
    FOREIGN KEY (id_mecanico) REFERENCES mecanico(id)
);

INSERT INTO especialidade (nome, vagas) VALUES ('Elétrica', 6);
INSERT INTO especialidade (nome, vagas) VALUES ('Motor', 3);
INSERT INTO especialidade (nome, vagas) VALUES ('Suspensão', 4);
INSERT INTO especialidade (nome, vagas) VALUES ('Freios', 6);

INSERT INTO proprietario VALUES ('12345678900', 'Juca Silva', '(45)3278-9384', 'Rua Tal, 1234 - Toledo - PR');
INSERT INTO proprietario VALUES ('23456789101', 'Ana Cardoso', '(45)99823-3961', 'Rua Qualquer, 784 - Toledo - PR');
INSERT INTO proprietario VALUES ('34567891202', 'Bianca Soares', '(45)3279-6473', 'Rua Sobedesce, 1221 - Toledo - PR');
INSERT INTO proprietario VALUES ('45678912303', 'Zeca Pedreira', '(45)99648-3174', 'Av Principal, 348 - Cascavel - PR');

INSERT INTO veiculo VALUES ('ABC1234', 'Corsa Wind', 2014, '12345678900');
INSERT INTO veiculo VALUES ('BCD2345', 'Ka+', 2018, '12345678900');
INSERT INTO veiculo VALUES ('ACX1B35', 'Monza', 1994, '45678912303');
INSERT INTO veiculo VALUES ('BAC3D81', 'Palio', 2016, '23456789101');
INSERT INTO veiculo VALUES ('CBD9812', 'Sandero 1.6', 2017, '34567891202');

INSERT INTO mecanico (nome, valor_hora, id_especialidade) VALUES ('Pedro Cardoso', 15.50, 1);
INSERT INTO mecanico (nome, valor_hora, id_especialidade) VALUES ('João Silva', 17.50, 1);
INSERT INTO mecanico (nome, valor_hora, id_especialidade) VALUES ('Ana Clara Soares', 17.40, 2);
INSERT INTO mecanico (nome, valor_hora, id_especialidade) VALUES ('Paulo Silva', 15.50, 2);
INSERT INTO mecanico (nome, valor_hora, id_especialidade) VALUES ('Carlos Oliveira', 18.90, 3);
INSERT INTO mecanico (nome, valor_hora, id_especialidade) VALUES ('Antonio Carlos Machado', 17.50, 4);

INSERT INTO agendamento (data, periodo, placa, reclamacao, id_especialidade, id_mecanico) VALUES ('2023-12-04', 'M', 'ABC1234', 'Luz alta nao funciona', 1, 1);
INSERT INTO agendamento (data, periodo, placa, reclamacao, id_especialidade, id_mecanico) VALUES ('2023-12-04', 'I', 'BCD2345', 'Demora a pegar, falha', 1, 2);
INSERT INTO agendamento (data, periodo, placa, reclamacao, id_especialidade, id_mecanico) VALUES ('2023-12-05', 'V', 'ACX1B35', 'Barulho quando passa uma lombada', 3, 4);


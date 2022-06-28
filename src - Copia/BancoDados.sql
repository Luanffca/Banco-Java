-- CRIACAO DE TABELA 
create table Cliente(
	id serial unique,
	nome text,
	usuario text unique,
	dataNascimento text,
	CPF text unique,
	RG text unique,
	endereco text,
	cep text,
	email text unique,
	celular text unique,
	senha text unique,
	primary key(id)
);

create table Conta(
	numero serial unique,
	pixnumero text unique,
	pixemail text unique,
	pixcpf text unique,
	agencia int,
	operacao char(3),
	saldo numeric (12,6),
	cheque numeric  (12,6),
	cliente bigint unique,
	senha char(4) unique,
	datahora_Atualiza date,
	primary key(numero),
	foreign key(cliente) references Cliente(id),
	foreign key(pixnumero) references Cliente(celular),
	foreign key(pixemail) references Cliente(email),
	foreign key(pixcpf) references Cliente(cpf)
);

CREATE TABLE Extrato(
	idExtrato serial unique,
	idTransacao int not null,
	idConta serial,
	datahora date,
	transacao text,
	valor numeric,
	saldoCheque numeric,
	saldoAnterior numeric,
	saldoAtualizado numeric,
	detalhes text,
	foreign key(idConta) references Conta(numero)
);

create table emprestimo(
	idemprestimo serial unique,
	parcelas integer,
	emprestimo_valor numeric (12,6),
	valorparcela numeric (12,6),
	datahora_emprestimo date,
	agencia_dev integer,
	operacao_dev char(3),
	cliente_dev integer,
	numconta_dev integer,
	agencia_cre integer,
	operacao_cre integer,
	cliente_cre integer,
	numconta_cre integer,

	primary key(idemprestimo),
	foreign key(agencia_dev) references Conta(agencia),
	foreign key(cliente_dev) references Cliente(id),
	foreign key(operacao_dev) references Conta(operacao),
	foreign key(numconta_dev) references Conta(numero),
	foreign key(agencia_cre) references Conta(agencia),
	foreign key(cliente_cre) references Cliente(id),
	foreign key(operacao_dev) references Conta(operacao),
	foreign key(numconta_cre) references Conta(numero)
);









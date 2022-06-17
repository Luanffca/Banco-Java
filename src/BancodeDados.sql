create table Cliente(
	id serial unique,
	tipo text,
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
	agencia int,
	operacao int,
	saldo numeric,
	cheque numeric,
	cliente bigint unique,
	senha char(4) unique,
	primary key(numero),
	foreign key(cliente) references Cliente(id)
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
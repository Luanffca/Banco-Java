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
	operacao int,
	saldo numeric,
	cheque numeric,
	cliente bigint unique,
	senha bigint unique,
	primary key(numero),
	foreign key(cliente) references Cliente(id),
	foreign key(pixnumero) references Cliente(celular),
	foreign key(pixemail) references Cliente(email),
	foreign key(pixcpf) references Cliente(cpf)
);
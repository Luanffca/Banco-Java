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
	operacao char(3),
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

-- ATUALIZACAO DIARIA DO JUROS NA CONTA POUPANCA
CREATE OR REPLACE FUNCTION saldo_atualizado() RETURNS TRIGGER AS $saldo_atual$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
			UPDATE conta SET saldo = (saldo * 1.0023) FROM extrato e WHERE e.idconta = numero AND operacao = '013' AND e.datahora_Atualiza != current_date;
		END IF;
		RETURN NEW;
    END;
$saldo_atual$ LANGUAGE plpgsql;

CREATE TRIGGER saldo_atual BEFORE INSERT OR UPDATE ON extrato
    FOR EACH ROW EXECUTE FUNCTION saldo_atualizado();


CREATE OR REPLACE FUNCTION data_atualizado() RETURNS TRIGGER AS $data_atual$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
			UPDATE extrato SET datahora_Atualiza = current_date FROM conta co WHERE idconta = co.numero AND co.operacao = '013';
		END IF;
		RETURN NEW;
    END;
$data_atual$ LANGUAGE plpgsql;

CREATE TRIGGER data_atual AFTER INSERT OR UPDATE ON extrato
    FOR EACH ROW EXECUTE FUNCTION data_atualizado();



-- ATUALIZACAO DIARIA DA TAXA DE ATUALIZACAO DOS JUROS DO CRETIDO ESPECIAL
CREATE OR REPLACE FUNCTION cheque_juros() RETURNS TRIGGER AS $juros_atual$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
			UPDATE conta SET saldo = (saldo - ((300 - cheque) * 0.23)) FROM extrato e WHERE e.idconta = numero AND operacao = '001' AND cheque != 300 AND e.datahora_Atualiza != current_date;
		END IF;
		RETURN NEW;
    END;
$juros_atual$ LANGUAGE plpgsql;

CREATE TRIGGER juros_atual BEFORE INSERT OR UPDATE ON extrato
    FOR EACH ROW EXECUTE FUNCTION cheque_juros();

CREATE OR REPLACE FUNCTION chequedata_atualizado() RETURNS TRIGGER AS $chequedata_atual$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
			UPDATE extrato SET datahora_Atualiza = current_date FROM conta co WHERE idconta = co.numero AND co.operacao = '001' AND co.cheque != 300;
		END IF;
		RETURN NEW;
    END;
$chequedata_atual$ LANGUAGE plpgsql;

CREATE TRIGGER chequedata_atual AFTER INSERT OR UPDATE ON extrato
    FOR EACH ROW EXECUTE FUNCTION chequedata_atualizado();


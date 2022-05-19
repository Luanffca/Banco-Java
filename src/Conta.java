import java.time.Instant;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public  class Conta {

	private String agencia;
	private String numero;
	private float saldo;

	List<Cliente> clienteCP = new ArrayList<>();
	List<Historico> historicos = new ArrayList<>();

	public Conta(String agencia, String numero, float saldo){
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
	}

	public float getSaldo(){
		return this.saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo=saldo;
	}

	public String getAgencia(){
		return this.agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero(){
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void depositar(float valor) {
		if (valor < 0) {
			System.out.println("** Depósito: Operação inválida");
		} else {
			saldo = saldo + valor;
			System.out.println("** Depósito: Operação realizada com sucessor!");
			Historico h = new Historico();
			h.setOperacao("Depósito");
			float depositor = valor;
			h.setDetalhes("Valor do depósito: " + depositor);
			h.setData(Instant.now().toString());
			historicos.add(h);
		}	
		
	}

	public void sacar(float valor) {
		if (valor > saldo || valor < 0) {
			System.out.println("** Saque: Saldo insuficiente");
		} else {
			saldo = saldo - valor;
			System.out.println("** Saque: Operação realizada com sucessor");
			Historico h = new Historico();
			h.setOperacao("Saque");
			float sacar = valor;
			h.setDetalhes("Valor do saque: " + sacar);
			h.setData(Instant.now().toString());
			historicos.add(h);
		}
		
	}

	public void verExtrato() {
		System.out.println("-------------------------");
		for (int i = 0; i < historicos.size(); i++) {
			System.out.println(historicos.get(i).getOperacao());
			System.out.println(historicos.get(i).getDetalhes());
			System.out.println(historicos.get(i).getData());
		}
		System.out.println("-------------------------");
		System.out.println("Saldo: " + saldo);
		System.out.println("-------------------------");
	}
}

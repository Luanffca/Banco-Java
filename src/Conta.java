import java.time.Instant;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public  class Conta {

	private String agencia;
	private int operacao;
	private int numero;
	private double saldo;
	private int senha;

	List<Cliente> clienteCP = new ArrayList<>();
	List<Historico> historicos = new ArrayList<>();

	Conta(){}

	public Conta(String agencia, int numero, int operacao, double saldo, int senha){
		this.agencia = agencia;
		this.numero = numero;
		this.operacao = operacao;
		this.saldo = saldo;
		this.senha = senha;
	}
	public Conta(String agencia, int numero, int operacao, double saldo){
		this.agencia = agencia;
		this.numero = numero;
		this.operacao = operacao;
		this.saldo = saldo;
	}

	public Conta(String agencia, int numero,int operacao, int senha){
		this.agencia = agencia;
		this.numero = numero;
		this.operacao = operacao;
		this.senha = senha;
	}

	public double getSaldo(){
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo=saldo;
	}

	public int getSenha(){
		return this.senha;
	}

	public void setSenha(int senha) {
		this.senha=senha;
	}

	public String getAgencia(){
		return this.agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public int getNumero(){
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getOperacao(){
		return this.operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	public void depositar(double valor) {
		if (valor < 0) {
			System.out.println("** Depósito: Operação inválida");
		} else {
			saldo = saldo + valor;
			System.out.println("** Depósito: Operação realizada com sucessor!");
			Historico h = new Historico();
			h.setOperacao("Depósito");
			double depositor = valor;
			h.setDetalhes("Valor do depósito: " + depositor);
			h.setData(Instant.now().toString());
			historicos.add(h);
		}	
		
	}

	public void sacar(double valor) {
		if (valor > saldo || valor < 0) {
			System.out.println("** Saque: Saldo insuficiente");
		} else {
			saldo = saldo - valor;
			System.out.println("** Saque: Operação realizada com sucessor");
			Historico h = new Historico();
			h.setOperacao("Saque");
			double sacar = valor;
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

	public float nextFloat() {
		return 0;
	}

	@Override
	public String toString() {
		return "\n Agencia: " + getAgencia() + " Operacao: " + getOperacao() + " Numero Conta: "+ getNumero();
	}
}

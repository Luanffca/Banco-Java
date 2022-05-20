import java.time.Instant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Poupanca extends Conta {
	Scanner sc = new Scanner(System.in);
	//Cliente clinte = new Cliente();
    List<Historico> historicos = new ArrayList<>();
	//List<Cliente> clientesCP = new ArrayList<>();

	Poupanca(){}
	
    public Poupanca(String agencia, int numero, int operacao, double saldo,int senha) {
        super(agencia, numero,operacao, saldo, senha);
    }

	public Poupanca(String agencia, int numero,int operacao, int senha) {
        super(agencia, numero, operacao, senha);
    }

	public boolean conferesenha(int senha){
		if(getSenha() == senha){
			return true;
		}else{
			return false;
		}
	}
    public void depositar(double valor) {
		if (valor < 0) {
			System.out.println("** Depósito: Operação inválida");
		} else {
			setSaldo(valor);
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
		if (valor > getSaldo() || valor < 0) {
			System.out.println("** Saque: Saldo insuficiente");	
		} else {
			setSaldo(valor);
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
		System.out.println("Saldo: " + getSaldo());
		System.out.println("-------------------------");
	}
    
	
}

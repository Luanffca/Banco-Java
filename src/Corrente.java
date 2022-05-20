import java.time.Instant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Corrente extends Conta{
	Scanner sc = new Scanner(System.in);
	List<Cliente> clienteCC = new ArrayList<>();
	List<Historico> historicos = new ArrayList<>();

	public Corrente(){

	}
	
    public Corrente(String agencia, int numero,int operacao, double saldo, int senha) {
        super(agencia, numero, operacao, saldo, senha);
    }
	public Corrente(String agencia, int numero, int operacao, int senha) {
        super(agencia, numero, operacao, senha);
    }
    public void depositar(double valor) {
		if (valor < 0) {
			System.out.println("** Depósito: Operação inválida");
		} else {
			setSaldo(valor);
			System.out.println("** Depósito: Operação realizada com sucesso!");
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Corrente extends Conta{
	Scanner sc = new Scanner(System.in);
	List<Cliente> clienteCC = new ArrayList<>();
	List<Historico> historicos = new ArrayList<>();
	private double taxa;
	private double limite;

	Corrente(int numero, int senha){
		super(710, 001, numero, senha);
	}

	public double getTaxa(){
		return this.taxa;
	}

	public void setTaxa(double taxa){
		this.taxa = taxa;
	}

	public double getLimite(){
		return this.limite;
	}

	public void setLimite(double limite){
		this.limite = limite;
	}

	@Override
	public String toString() {
		return super.toString() + "\n Conta Corrente - Taxa: "+ getTaxa() + " Limite: "+ getLimite();
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
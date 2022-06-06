import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public  class Conta{

	private int numero;
	private String pixnumero;
	private String pixemail;
	private String pixcpf;
	private int agencia;
	private String operacao;
	private double saldo;
	protected double cheque;
	private int cliente;
	private String senha;

	List<Extrato> historicos = new ArrayList<>();

	Conta(int numero, String pixnumero, String pixemail, String pixcpf, int agencia, String operacao, Double saldo, Double cheque, int cliente, String senha){
		this.numero = numero;
		this.pixnumero = pixnumero;
		this.pixemail = pixemail;
		this.pixcpf = pixcpf;
		this.agencia = agencia;
		this.operacao = operacao;
		this.saldo = saldo;
		this.cheque = cheque;
		this.cliente = cliente;
		this.senha = senha;
	}

	public void setCheque(double cheque) {
		this.cheque = cheque;
	}

	public double getCheque() {
		return cheque;
	}
	
	public double getSaldo(){
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo=saldo;
	}

	public int getAgencia(){
		return this.agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getNumero(){
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getOperacao(){
		return this.operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getSenha() {
		return senha;
	}

	/*
	public void depositar(double valor) {
		if (valor < 0) {
			System.out.println("** Depósito: Operação inválida");
		} else {
			saldo = saldo + valor;
			System.out.println("** Depósito: Operação realizada com sucessor!");
			//Extrato h = new Extrato();
			//h.setOperacao("Depósito");
			double depositor = valor;
			//h.setDetalhes("Valor do depósito: " + depositor);
			//h.setData(Instant.now().toString());
			//historicos.add(h);
		}	
	}

	public void sacar(double valor) {
		if (valor > saldo || valor < 0) {
			System.out.println("** Saque: Saldo insuficiente");
		} else {
			saldo = saldo - valor;
			System.out.println("** Saque: Operação realizada com sucessor");
			//Extrato h = new Extrato();
			//h.setOperacao("Saque");
			double sacar = valor;
			//h.setDetalhes("Valor do saque: " + sacar);
			//h.setData(Instant.now().toString());
			//historicos.add(h);
		}
		
	}

	//public Boolean transferir(double valor, Conta operacao, Conta numero) {
   //     if (sacar(valor) && getOperacao() == operacao && getNumero() == numero){
	//		return true;} 
    //    return false;
    //}

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

	
	@Override
	public String toString() {
		return super.toString() + "\n Agencia: " + getAgencia() + " Operacao: " + getOperacao() + " Numero Conta: "+ getNumero();
	}
	*/
}
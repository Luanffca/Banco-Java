
public class Conta {

	private int numero;
	private int agencia;
	private String operacao;
	private double saldo;
	protected double cheque;
	private int cliente;
	private String senha;

	Conta(int numero, int agencia, String operacao, Double saldo, Double cheque, int cliente, String senha) {
		this.numero = numero;
		this.agencia = agencia;
		this.operacao = operacao;
		this.saldo = saldo;
		this.cheque = cheque;
		this.cliente = cliente;
		this.senha = senha;
	}

    public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCheque(double cheque) {
		this.cheque = cheque;
	}

	public double getCheque() {
		return cheque;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getAgencia() {
		return this.agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getOperacao() {
		return this.operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getSenha() {
		return senha;
	}

}
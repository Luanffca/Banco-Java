
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
	public void setPixnumero(String pixnumero) {
		this.pixnumero = pixnumero;
	}
	public String getPixnumero() {
		return pixnumero;
	}
	public void setPixemail(String pixemail) {
		this.pixemail = pixemail;
	}
	public String getPixemail() {
		return pixemail;
	}
	public void setPixcpf(String pixcpf) {
		this.pixcpf = pixcpf;
	}
	public String getPixcpf() {
		return pixcpf;
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

}
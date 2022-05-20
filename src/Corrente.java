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

	Corrente(){}

	Corrente(String nome, String rg, String cpf, String endereco, String dataNascimento, String email, int senha,int agencia, int numero,int operacao){
		super(nome, rg, cpf, endereco, dataNascimento, email, senha, agencia, operacao, numero);
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
	
	public boolean EntarConta(String email, int senha){
		if (getEmail() == email && getSenha() == senha){
			return true;
		}
		else{
			return false;
		}
	}

	public void CadastraCC(){
		String nome,rg,cpf,endereco,dataNascimento, email;
		int senha;
		System.out.println("_________________________________________"); 
		System.out.println("Informe seu Nome Completo: ");
		nome = sc.nextLine();
		System.out.println("Informe seu RG: ");
		rg = sc.nextLine();
		System.out.println("Informe seu CPF: ");
		cpf = sc.nextLine();
		System.out.println("Informe seu Endereco: ");
		endereco = sc.nextLine();
		System.out.println("Informe sua Data de Nascimento: ");
		dataNascimento = sc.nextLine();
		System.out.println("Informe seu email: ");
		email = sc.nextLine();
		System.out.println("Informe sua Senha: ");
		senha = sc.nextInt();
		System.out.println("_________________________________________"); 
		Random num = new Random();
        int numero = num.nextInt(9999);
		Corrente cc = new Corrente(nome, rg, cpf, endereco, dataNascimento, email, senha, 0742, 012, numero);
		System.out.println(cc.toString());
	}

	
}

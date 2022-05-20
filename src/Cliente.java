import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cliente {
	Scanner sc = new Scanner(System.in);
	List<Cliente> clienteCC = new ArrayList<>();

	private String nome;
	private String rg;
	private String cpf;
	private String endereco;
	private String dataNascimento;
	private String email;
	private int senha;
	private boolean corrente;
	private boolean poupanca;
	
	Cliente(){}
	Cliente(String nome, String rg, String cpf, String endereco, String dataNascimento, String email, int senha){
		this.nome = nome;
		this.rg = rg;
		if(isCPF(cpf) == true){
			this.cpf = imprimeCPF(cpf);
		}
		else{
			System.out.println("Erro, CPF invalido !!!\n");
		}
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
	}

	public String getNome(){
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRG(){
		return this.rg;
	}

	public void setRG(String rg) {
		this.rg = rg;
	}

	public String getCPF(){
		return this.cpf;
	}

	public void setCPF(String cpf) {
		if(isCPF(cpf) == true){
			this.cpf = imprimeCPF(cpf);
		}
		else{
			System.out.println("Erro, CPF invalido !!!\n");
		}
	}

	public String getEndereco(){
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDatanascimento(){
		return this.dataNascimento;
	}

	public void setDatanascimento(String dataNascimento){
		this.dataNascimento = dataNascimento;
	}

    public boolean isCPF(String CPF) {
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
       
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); 

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }

    public String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }

	public String toString(){
		return " Nome: " + getNome() +" \nRG: "+ getRG()+"\n CPF: "+getCPF()+"\n Endereco: "+getEndereco()+"\n Data Nascimento: "+ getDatanascimento();

	}

	
    
	public void CadastraCP(){
		System.out.println("_________________________________________"); 
		String nome,rg,cpf,endereco,dataNascimento;
		System.out.println("Informe seu Nome Completo: ");
		nome = sc.nextLine();
		System.out.println("Informe seu RG: ");
		rg = sc.nextLine();
		System.out.println("Informe seu CPF: ");
		cpf = sc.nextLine();
		System.out.println("Informe seu Endereco: ");
		endereco = sc.nextLine();
		System.out.println("Informe sua Data de Naascimento: ");
		dataNascimento = sc.nextLine();
		System.out.println("Informe seu email: ");
		email = sc.nextLine();
		System.out.println("Informe sua Data de Nascimento: ");
		dataNascimento = sc.nextLine();
		System.out.println("_________________________________________"); 
		Cliente ccp = new Cliente(nome,rg,cpf,endereco,dataNascimento,email, senha);
		System.out.println(ccp.toString());
		Random numero = new Random();
        int contanum = numero.nextInt(9999);
		Conta cp = new Conta("0742", contanum, 13, 0.0);
		System.out.println(cp.toString());
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
		Cliente cc = new Cliente(nome,rg,cpf,endereco,dataNascimento, email, senha);
		System.out.println(cc.toString());
		Random numero = new Random();
        int contanum = numero.nextInt(9999);
		Conta cp = new Conta("0742", contanum, 12, 0.0);
		System.out.println(cp.toString());
	}
}

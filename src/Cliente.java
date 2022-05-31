
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	Scanner sc = new Scanner(System.in);
	List<Cliente> clienteCC = new ArrayList<>();

	private int id;
	private String nome;
	private String usuario;
	private String dataNascimento;
	private String cpf;
	private String rg;
	private String endereco;
	private String cep;
	private String email;
	private String celular;
	private String senha;

	Cliente(){}
	Cliente(int id, String nome, String usuario, String dataNascimento, String cpf, String rg, String endereco, String cep, String email, String celular, String senha){
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.cep = cep;
		this.email = email;
		this.celular = celular;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome(){
		return this.nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario(){
		return this.usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSenha(){
		return this.senha;
	}

	public void setSenha(String senha){
		this.senha = senha;
	}
	public Poupanca criaCp(){
		Poupanca cp = new Poupanca(013, 071);
		return cp;
	}
	public Corrente criaCc(){
		Corrente cc = new Corrente(001, 071);
		return cc;
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

	
    @Override
	public String toString(){
		return "ID: " + getId() + " Nome: " + getNome() +" \nRG: "+ getRG()+"\n CPF: "+getCPF()+"\n Endereco: "+getEndereco();
	}
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	public static void main(String[] args)throws InterruptedException {
		dadosConta();
	}
	
	private static void dadosConta() {
		Scanner sc = new Scanner(System.in);
		Corrente contaCorrente = null;
		Poupanca contaPoupanca = null;
		List<Poupanca> ListaPoupanca = new ArrayList<Poupanca>();
		List<Corrente> ListaCorrente = new ArrayList<Corrente>();
		int opcao = 0;
		do{
			System.out.println("## Escolha uma das opções abaixo. ##");
			System.out.println("________________________________________"); 
			System.out.println("Opção 0 - Cancelar operacao");
			System.out.println("Opção 1 - Consulta sua conta.");
			System.out.println("Opção 2 - Criar sua Conta");
			System.out.println("_________________________________________"); 
			System.out.println("Digite aqui sua opção: ");
			opcao = sc.nextInt();
			switch (opcao){
			case 1:
				System.out.println("Numero da operacao:");
				int operacao = sc.nextInt();
				System.out.println("Seu email:");
				String email = sc.next();
				System.out.println("Numero da senha:");
				int senha = sc.nextInt();
				switch (operacao){
					case 12: // Corrente
						Corrente cc = new Corrente();
						cc.EntarConta(email, senha);
						System.out.println("## Escolha uma das opções abaixo. ##");
						System.out.println("________________________________________"); 
						System.out.println("Opção 0 - Cancelar operacao");
						System.out.println("Opção 1 - Depositar.");
						System.out.println("Opção 2 - Sacar");
						System.out.println("Opção 3 - Extrato");
						System.out.println("_________________________________________"); 
						System.out.println("Digite aqui sua opção: ");
						int opc = sc.nextInt();
					switch (opc){
						case 1:
							System.out.println("Valor a Depositar");
							float valor_Desposito = sc.nextFloat();
							cc.depositar(valor_Desposito);
						break;
						case 2:
							System.out.println("Valor de saque");
							float valor_saque = sc.nextFloat();
							cc.depositar(valor_saque);
						break;
						case 3:
							if(ListaCorrente.isEmpty()==false){
								System.out.println("Historico:");
								contaCorrente.verExtrato();
								sc.nextLine();
							}else{
								System.out.println(ListaCorrente.toString());  
								System.out.println("Pressione um tecla para continuar.");
								sc.nextLine();
							}
							//cc.verExtrato();;
						break;
					}
				break;
				case 13: // poupanca
						Poupanca cp = new Poupanca();
						int op;	
					do {
						if(cp.EntarConta(email, senha) == true){
								System.out.println("## Escolha uma das opções abaixo. ##");
								System.out.println("________________________________________"); 
								System.out.println("Opção 0 - Cancelar operacao");
								System.out.println("Opção 1 - Depositar.");
								System.out.println("Opção 2 - Sacar");
								System.out.println("Opção 3 - Extrato");
								System.out.println("_________________________________________"); 
								System.out.println("Digite aqui sua opção: ");
								op = sc.nextInt();
								switch (op){
									case 1:
										System.out.println("Valor a Depositar");
										float valor_Desposito = sc.nextFloat();
										cp.depositar(valor_Desposito);
									break;
									case 2:
										System.out.println("Valor de saque");
										float valor_saque = sc.nextFloat();
										cp.depositar(valor_saque);
									break;
									case 3:
										if(ListaPoupanca.isEmpty()==false){
											System.out.println("Historico:");
											contaPoupanca.verExtrato();
											sc.nextLine();
										}else{
											System.out.println(ListaPoupanca.toString());  
											System.out.println("Pressione um tecla para continuar.");
											sc.nextLine();
										}
											//cp.verExtrato();
									break;
								}
						}
						else{
							System.out.println("Senha ou Email invalido");
							//return false;
							break;
						}
					break;
				}while(op != 0);
			}
			break;
			case 2:
				System.out.println("_________________________________________"); 
				System.out.println("Bem Vindo ao Banco Coin");
				System.out.println("Informe o tipo de conta que deseja criar: \n 12 - Conta Corrente \n 13 - Conta Poupanca");
				System.out.println("_________________________________________"); 
				System.out.println("Digite aqui sua opção: ");
				int op = sc.nextInt();
				switch (op){
					case 13:
						Corrente cc = new Corrente();
						cc.CadastraCC();
						ListaCorrente.add(contaCorrente);
						break;
					case 12:
						Poupanca cp = new Poupanca();
						cp.CadastraCP();
						ListaPoupanca.add(contaPoupanca);
						break;
					default:
						System.out.println("Opcao invalida!");
						break;
					}
				break;
			default:
					System.out.println("Opcao invalida!");
					break;
			}//Fim do switch case
		}while(opcao != 0);
		sc.close();
	}
}

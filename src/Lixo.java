import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Lixo {
	public static void main(String[] args)throws InterruptedException {
		dadosConta();
	}
	
	private static void dadosConta() {
		Scanner sc = new Scanner(System.in);
		Conta conta = null;
		List<Conta> listaConta = new ArrayList<Conta>();
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
				System.out.println("_________________________________________"); 
				System.out.println("Bem Vindo ao Banco POO");
				System.out.println("Informe o tipo de conta que deseja consulta: \n CC - Conta Corrente \n CP - Conta Poupanca");
				System.out.println("_________________________________________"); 
				System.out.println("Digite aqui sua opção: ");
				String ops = sc.next();
				switch (ops.toUpperCase()){
					case "CP":
						System.out.println("## Escolha uma das opções abaixo. ##");
						System.out.println("________________________________________"); 
						System.out.println("Opção 0 - Cancelar operacao");
						System.out.println("Opção 1 - Depositar.");
						System.out.println("Opção 2 - Sacar");
						System.out.println("Opção 3 - Extrato");
						System.out.println("_________________________________________"); 
						System.out.println("Digite aqui sua opção: ");
						int opc = sc.nextInt();
						System.out.println("Numero da Agencia");
						String agencia = sc.next();
                        System.out.println("Numero da operacao");
				        int operacao = sc.nextInt();
						System.out.println("Numero da Conta");
						int numero_conta = sc.nextInt();
						System.out.println("Numero da senha");
						int senhacp = sc.nextInt();
						Conta cp = new Poupanca(agencia, numero_conta,operacao, senhacp);
						switch (opc){
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
								cp.verExtrato();;
							break;
						}
					break;
					case "CC":
						System.out.println("## Escolha uma das opções abaixo. ##");
						System.out.println("________________________________________"); 
						System.out.println("Opção 0 - Cancelar operacao");
						System.out.println("Opção 1 - Depositar.");
						System.out.println("Opção 2 - Sacar");
						System.out.println("Opção 3 - Extrato");
						System.out.println("_________________________________________"); 
						System.out.println("Digite aqui sua opção: ");
						int o = sc.nextInt();
						System.out.println("Numero da Agencia");
						String agenciacc = sc.next();
                        System.out.println("Numero da operacao");
				        int operacaoc = sc.nextInt();
						System.out.println("Numero da Conta");
						int  numero_contacc = sc.nextInt();
						System.out.println("Numero da senha");
						int senhacc = sc.nextInt();
						Conta cc = new Corrente(agenciacc, numero_contacc,operacaoc, senhacc);
					switch (o){
						case 1:
							System.out.println("Valor a Depositar");
							float valorccDesposito = cc.nextFloat();
							cc.depositar(valorccDesposito);
						break;
						case 2:
							System.out.println("Valor de saque");
							float valorccsaque = cc.nextFloat();
							cc.depositar(valorccsaque);
						break;
						case 3:
							cc.verExtrato();;
						break;
					}
						break;
					default:
						System.out.println("Opcao invalida!");
						break;
				}
			case 2:
				System.out.println("_________________________________________"); 
				System.out.println("Bem Vindo ao Banco CAJU");
				System.out.println("Informe o tipo de conta que deseja criar: \n CC - Conta Corrente \n CP - Conta Poupanca");
				System.out.println("_________________________________________"); 
				System.out.println("Digite aqui sua opção: ");
				String op = sc.next();
				switch (op.toUpperCase()){
					case "CP":
						Cliente cliente = new Cliente();
						cliente.CadastraCP();
						break;
					case "CC":
						Cliente clientes = new Cliente();
						clientes.CadastraCC();
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



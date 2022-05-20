import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
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
				System.out.println("Numero da Agencia:");
				String agencia = sc.next();
				System.out.println("Numero da operacao:");
				int operacao = sc.nextInt();
				System.out.println("Numero da Conta:");
				int numero_conta = sc.nextInt();
				System.out.println("Numero da senha:");
				int senha = sc.nextInt();
				switch (operacao){
					case 12: // Corrente
						Conta cc = new Corrente(agencia, numero_conta, operacao, senha);
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
							cc.verExtrato();;
						break;
					}
				break;
				case 13: // poupanca
					Conta cp = new Poupanca(agencia, numero_conta, operacao, senha);
						System.out.println("## Escolha uma das opções abaixo. ##");
						System.out.println("________________________________________"); 
						System.out.println("Opção 0 - Cancelar operacao");
						System.out.println("Opção 1 - Depositar.");
						System.out.println("Opção 2 - Sacar");
						System.out.println("Opção 3 - Extrato");
						System.out.println("_________________________________________"); 
						System.out.println("Digite aqui sua opção: ");
						int op = sc.nextInt();
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
							cp.verExtrato();
						break;
					}
				break;
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
						Cliente cliente = new Cliente();
						cliente.CadastraCP();
						break;
					case 12:
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

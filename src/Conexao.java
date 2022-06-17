
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
	private String url;
	private String usuario;
	private String senha;
	private Connection con;

	Conexao() {
		url = "jdbc:postgresql://localhost:5432/Banco";
		usuario = "postgres";
		senha = "alissonadmin";
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Cadastra(String tipo, String nome, String usuario, String dataNascimento, String cpf, String rg,
			String endereco, String cep, String email, String celular, String senha) {
		try {
			String sql = "INSERT INTO Cliente(tipo, nome, usuario, datanascimento, cpf, rg, endereco, cep, email, celular, senha) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tipo);
			statement.setString(2, nome);
			statement.setString(3, usuario);
			statement.setString(4, dataNascimento);
			statement.setString(5, cpf);
			statement.setString(6, rg);
			statement.setString(7, endereco);
			statement.setString(8, cep);
			statement.setString(9, email);
			statement.setString(10, celular);
			statement.setString(11, senha);
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public ResultSet getUsuario(String dados) {
		try {
			String sql = "SELECT * FROM cliente WHERE usuario = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet getConta(int dados) {
		try {
			String sql = "SELECT * FROM conta WHERE cliente = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet getContaTransferencia(int numero, int agencia, String operacao) {
		try {
			String sql = "SELECT * FROM cliente WHERE id = (SELECT cliente FROM conta WHERE numero = ? and agencia = ? and operacao = ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, numero);
			statement.setInt(2, agencia);
			statement.setString(3, operacao);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet getExtrato(int idconta, int idtransacao) {
		try {
			String sql = "SELECT * FROM extrato WHERE idconta = ? AND idtransacao = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, idconta);
			statement.setInt(2, idtransacao);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet BuscaUsuario(String dados) {
		try {
			String sql = "Select senha From Cliente Where usuario = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet BuscaUsuarioEmail(String dados) {
		try {
			String sql = "Select email From Cliente Where usuario = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet qntOperacao(int dados) {
		try {
			String sql = "select count(idconta) from extrato where idconta = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void criaConta(int id, Double cheque, String operacao, String senha) {
		try {
			String sql = "INSERT INTO conta(agencia , operacao , saldo, cheque, cliente, senha) VALUES(701, ?, 0.0, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, operacao);
			statement.setDouble(2, cheque);
			statement.setInt(3, id);
			statement.setString(4, senha);
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void atualizaSaldo(double saldo, int id) {
		try {
			String sql = "UPDATE CONTA SET SALDO = ? WHERE CLIENTE = ?;";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDouble(1, saldo);
			statement.setInt(2, id);
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void atualizaSaldoEmprestimo(double saldo, int id) {
		try {
			String sql = "UPDATE CONTA SET SALDO = ? WHERE NUMERO = ?;";			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDouble(1, saldo);
			statement.setInt(2, id);
			statement.execute();

		} catch (Exception e){
			e.printStackTrace();

		}
    }

	public void atualizaCheque(double cheque, int id) {
		try {
			String sql = "UPDATE CONTA SET CHEQUE = ? WHERE CLIENTE = ?; ";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDouble(1, cheque);
			statement.setInt(2, id);
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public ResultSet BuscaPix(String Buscadado, String dado) {
		try {
			if(Buscadado.equals("cpf")){
				String sql = "SELECT * from cliente where cpf = ?;";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, dado);
				ResultSet rs = statement.executeQuery();
				return rs;
			}else{
				if(Buscadado.equals("email")){
					String sql = "SELECT * from cliente where email = ?;";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, dado);
					ResultSet rs = statement.executeQuery();
					return rs;
				}else{
					if(Buscadado.equals("celular")){
						String sql = "SELECT * from cliente where celular = ?;";
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, dado);
						ResultSet rs = statement.executeQuery();
						return rs;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void InsereExtrato(int idconta, String transacao, Double valor, Double saldocheque, Double saldoanterior,
			Double saldoatualizado, String detalhes) {
		try {
			String sql = "INSERT INTO extrato(idTransacao, idconta, datahora, transacao, valor, saldocheque, saldoanterior, saldoatualizado, detalhes) VALUES((SELECT COALESCE(MAX(idTransacao),0)+ 1 FROM extrato WHERE idconta = ?), ?, current_timestamp, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, idconta);
			statement.setInt(2, idconta);
			statement.setString(3, transacao);
			statement.setDouble(4, valor);
			statement.setDouble(5, saldocheque);
			statement.setDouble(6, saldoanterior);
			statement.setDouble(7, saldoatualizado);
			statement.setString(8, detalhes);
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void atualizaSenha(String senha, String usuario, String email) {
		try {
			String sql = "UPDATE CLIENTE SET SENHA = ? WHERE USUARIO = ? AND EMAIL = ?;";			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, senha);
			statement.setString(2, usuario);
			statement.setString(3, email);
			statement.execute();

		} catch (Exception e){
			e.printStackTrace();

		}
    }
	public ResultSet BuscaCredorEmprestimo(int dados) {
		try {
			String sql = "SELECT numero FROM conta WHERE numero = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet BuscaCredorEmprestimoSaldo(int dados) {
		try {
			String sql = "SELECT saldo FROM conta WHERE numero = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}

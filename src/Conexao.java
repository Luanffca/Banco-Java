
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
	private String url;
	private String usuario;
	private String senha;
	private Connection con;
	
	Conexao(){
		url = "jdbc:postgresql://localhost:5432/Banco";
		usuario = "postgres";
		senha = "alissonadmin";
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexão realizada com sucesso");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void Cadastra(String nome, String usuario, String dataNascimento, String cpf, String rg, String endereco, String cep, String email, String celular, String senha) {
		try {
			String sql = "INSERT INTO Cliente(nome, usuario, datanascimento, cpf, rg, endereco, cep, email, celular, senha) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nome);
			statement.setString(2, usuario);
			statement.setString(3, dataNascimento);
			statement.setString(4, cpf);
			statement.setString(5, rg);
			statement.setString(6, endereco);
			statement.setString(7, cep);
			statement.setString(8, email);
			statement.setString(9, celular);
			statement.setString(10, senha);
			statement.execute();

		} catch (Exception e){
			e.printStackTrace();

		}
	}

	public ResultSet getUsuario(String dados){
		try {
			String sql = "SELECT * FROM cliente WHERE usuario = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet getConta(int dados){
		try {
			String sql = "SELECT * FROM conta WHERE cliente = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, dados);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet getExtrato(int idconta, int idtransacao){
		try {
			String sql = "SELECT * FROM extrato WHERE idconta = ? AND idtransacao = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, idconta);
			statement.setInt(2, idtransacao);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (Exception e){
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
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void criaConta(int id,String cpf, String email, String celular, Double cheque, String operacao, String senha) {
		try {
			String sql = "INSERT INTO conta( pixnumero, pixemail, pixcpf, agencia , operacao , saldo, cheque, cliente, senha) VALUES(?, ?, ?, 701, ?, 0.0, ?, ?, ?)";			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, celular);
			statement.setString(2, email);
			statement.setString(3, cpf);
			statement.setString(4, operacao);
			statement.setDouble(5, cheque);
			statement.setInt(6, id);
			statement.setString(7, senha);
			statement.execute();

		} catch (Exception e){
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

		} catch (Exception e){
			e.printStackTrace();

		}
    }
	public void atualizaCheque(double cheque, int id) {
		try {
			String sql = "UPDATE CONTA SET CHEQUE = ? WHERE CLIENTE = ?;";			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDouble(1, cheque);
			statement.setInt(2, id);
			statement.execute();

		} catch (Exception e){
			e.printStackTrace();

		}
    }

	public void InsereExtrato(int idconta, String transacao, Double valor, Double saldocheque, Double saldoanterior, Double saldoatualizado, String detalhes) {
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

		} catch (Exception e){
			e.printStackTrace();

		}
	}

}
	


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			con.close();

		} catch (Exception e){
			e.printStackTrace();

		}
	}
	
	public ResultSet BuscaUsuario(String dados) {
		try {
			String sql = "Select senha From Cliente Where usuario = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, dados);
			ResultSet rs = statement.executeQuery();
			con.close();
			return rs;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

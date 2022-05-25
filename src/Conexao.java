
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
	
	public int Cadastra(String dados) {
		try {
			Statement stm = con.createStatement();
			int res = stm.executeUpdate(dados);
			con.close();
			return res;
		} catch (Exception e){
			e.printStackTrace();
			return 0;
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

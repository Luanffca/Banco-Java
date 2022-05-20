import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao {
	
	private final String url = "jdbc:postgresql://localhost:5432/pessoa";
    private final String user = "postgres";
    private final String password = "admin";
    private String driver = "org.postgresql.Driver";
    Connection conn = null;
    private Statement stm;

    public static void main(String[] args){
        new Conexao();
    }

    public Connection connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    
    public void retornaPessoa(){
    	try (Connection conn = connect();
    			Statement stmt = conn.createStatement()) {

    		try {
    			ResultSet rs = stmt.executeQuery("select * from pessoa");
    			while (rs.next()) {
    				String nome = rs.getString("firstname");
    				System.out.println(nome);
    			}
    		} catch (SQLException e ) {
    			throw new Error("Problem", e);
    		} 

    	} catch (SQLException e) {
    		throw new Error("Problem", e);
    	}
    }

    public void Conexao(){  
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            stm = conn.createStatement();
            String sql = "insert int pessoa (NOME,RG,CPF,DT_NASCIMENTO,SENHA, AGENCIA,OPERACAO,NUMERO_CONTA,SALDO)" + 
                        "values(Luan Fernandes de Franca, 123456789, , 06/02/2004, 1939, 0742, 13, 2456, 15000.00)";
            stm.executeUpdate(sql);
            conn.close(); 
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}
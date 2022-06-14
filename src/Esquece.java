import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Esquece {
    static Color corAzul = new Color(34,92,150);
    static Color corCinza = new Color(67,87,114);
    static Color corAmarelo = new Color(254,170,58);
    static Color corVermelho = new Color(234,100,30);
    static JPanel window_tela;
    static JPanel window;
    static Texto textos = new Texto();
    static Botao botoes = new Botao();
    static campoTxt campos = new campoTxt();
    static String loginUsuario, loginEmail, loginSenha,loginCSenha;
    static Conexao con = new Conexao();
    static Conta conta = null;
    public JPanel painel(JPanel anterior){
        JTextField getEmail, getUser;
        window = new JPanel();
        window.setBackground(corCinza);
        window.setVisible(false);
        window.setLayout(null);
        
        //JTextField getEmail = campos.campoCadastrar(40, 490, 240, 30);
        getUser = new JTextField();
        getUser.setBounds(20, 150, 460, 50);
        getUser.setFont(new Font("Arial", Font.PLAIN, 30));
        getUser.setHorizontalAlignment(SwingConstants.CENTER);
        getUser.setForeground(Color.white);
        getUser.setBackground(corCinza);
        getUser.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        
        getEmail = new JTextField();
        getEmail.setBounds(20, 350, 460, 50);
        getEmail.setFont(new Font("Arial", Font.PLAIN, 30));
        getEmail.setHorizontalAlignment(SwingConstants.CENTER);
        getEmail.setForeground(Color.white);
        getEmail.setBackground(corCinza);
        getEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        JButton segueEmail = botoes.botao("Prosseguir", corAzul, Color.white, 100, 500, 300, 40, 30, 1, 3, 3, 1, Color.white);
        
        segueEmail.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loginUsuario = getUser.getText();
                loginEmail = getEmail.getText();
                try {
            		ResultSet rs = con.BuscaUsuarioEmail(loginUsuario);
                    if (rs.next()){
                        String bancoEmail = rs.getString("email");
                        System.out.println(bancoEmail);
                        System.out.println(loginUsuario);
                        if(bancoEmail.equals(loginEmail) ) {
                            window_tela.setVisible(true);
                            window.setVisible(false);
                        }
                    }else System.out.println("Usuario ou Senha incorreta");
                } catch (Exception es) {
                    es.printStackTrace();
                }
            }
        });

        
        window.add(textos.textos(" Recupera Senha ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        window.add(textos.textosAlinhados("Digite o Usuário", 0, 100, 520, 50, 25, Color.white));
        window.add(textos.textosAlinhados("Digite o E-mail", 0, 300, 520, 50, 25, Color.white));
        window.add(getUser);
        window.add(getEmail);
        window.add(botoes.botaoVoltar(window, anterior));
        window.add(segueEmail);  
        
        return window;
    }
    public JPanel telasenha(JPanel proxima){
        JTextField getSenha, getCsenha;
        window_tela = new JPanel();
        window_tela.setBackground(corCinza);
        window_tela.setVisible(false);
        window_tela.setLayout(null);
        
        getSenha = new JTextField();
        getSenha.setBounds(20, 150, 460, 50);
        getSenha.setFont(new Font("Arial", Font.PLAIN, 30));
        getSenha.setHorizontalAlignment(SwingConstants.CENTER);
        getSenha.setForeground(Color.white);
        getSenha.setBackground(corCinza);
        getSenha.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        
        getCsenha = new JTextField();
        getCsenha.setBounds(20, 350, 460, 50);
        getCsenha.setFont(new Font("Arial", Font.PLAIN, 30));
        getCsenha.setHorizontalAlignment(SwingConstants.CENTER);
        getCsenha.setForeground(Color.white);
        getCsenha.setBackground(corCinza);
        getCsenha.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        JButton segueSenha = botoes.botao("Prosseguir", corAzul, Color.white, 100, 500, 300, 40, 30, 1, 3, 3, 1, Color.white);

        segueSenha.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loginSenha = getSenha.getText();
                loginCSenha = getCsenha.getText();
                try {
                    if (loginSenha.equals(loginCSenha)){
                        System.out.println(loginSenha+' ' +loginUsuario+' ' +loginEmail);
                        con.atualizaSenha(loginSenha, loginUsuario, loginEmail);
                        proxima.setVisible(true);
                        window_tela.setVisible(false);
                    }else{
                        window_tela.setVisible(false);
                        window.setVisible(true);
                        System.out.println("Nao atualizou");
                    }
                    //atualizaSenha(loginSenha, loginCSenha, loginUsuario, loginEmail);
                    
                } catch (Exception es) {
                    es.printStackTrace();
                }
            }
        });

        window_tela.add(textos.textos(" Recupera Senha ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        window_tela.add(textos.textosAlinhados("Digite a Senha", 0, 100, 520, 50, 25, Color.white));
        window_tela.add(textos.textosAlinhados("Confirmar Senha", 0, 300, 520, 50, 25, Color.white));
        window_tela.add(getSenha);
        window_tela.add(getCsenha);
        window_tela.add(segueSenha);

        return window_tela;
    } 

}

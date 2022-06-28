import javax.swing.*;
import java.awt.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class App {
    static Cliente cliente = null;
    static Conta conta = null;

    public static void main(String[] args) {
        Color corAzul = new Color(34, 92, 150);
        Color corCinza = new Color(67, 87, 114);
        Color corAmarelo = new Color(254, 170, 58);

        JPanel telaInicial = new JPanel();
        telaInicial.setLayout(null);
        telaInicial.setVisible(true);

        Texto textos = new Texto();
        Botao botoes = new Botao();
        campoTxt campos = new campoTxt();
        Conexao con = new Conexao();
        Esquece panel = new Esquece();

        JPanel panelRecupera = panel.painel(telaInicial);
        JPanel panelSenha = panel.telasenha(telaInicial);
        TelaPrincipal TelaPrincipal = new TelaPrincipal();
        JPanel telaPrincipal = TelaPrincipal.TelaPrincipal(telaInicial);

        // TELA ENTRAR
        JPanel Entrar = new JPanel();
        Entrar.setBackground(Color.white);
        Entrar.setLayout(null);
        Entrar.setVisible(true);

        JButton botaoRegister = botoes.botao(" Cadastrar", corAmarelo, Color.black, 300, 110, 210, 40, 25);
        JLabel borderRegister = textos.textos(485, 150, 30, 680, corAmarelo);
        JButton recuperar = botoes.botao("Esqueci a senha", Color.white, corAzul, 100, 340, 300, 20);
        JButton logar = botoes.botao("Entrar", corAmarelo, Color.black, 100, 370, 300, 40, 30);
        JTextField usuario = campos.campo(100, 230, 300, 30, 15, Color.white, Color.black, 1, 2, 2, 1, Color.black);
        JPasswordField senha = new JPasswordField();
        senha.setBounds(100, 300, 300, 30);
        senha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        senha.setFont(new Font("Arial", Font.PLAIN, 15));
        senha.setBackground(Color.white);
        senha.setForeground(Color.black);

        JLabel labelEntrar = textos.textosAlinhados("Entrar", 0, 110, 300, 40, 30, corAzul);
        Entrar.add(labelEntrar);
        Entrar.add(textos.textos("Usuário", 100, 200, 300, 30, 20, corAzul));
        Entrar.add(textos.textos("Senha", 100, 270, 300, 30, 20, corAzul));
        Entrar.add(borderRegister);
        Entrar.add(recuperar);
        Entrar.add(logar);
        Entrar.add(senha);
        Entrar.add(botaoRegister);
        Entrar.add(usuario);

        // TELA CADASTRAR
        JPanel Cadastrar = new JPanel();
        JButton registrar = botoes.botao("Cadastrar", corAmarelo, Color.black, 100, 610, 310, 40, 30);
        JButton botaoLogin = botoes.botao("Entrar", corAmarelo, Color.black, 0, 110, 210, 40, 25);
        JLabel borderLogin = textos.textos(0, 150, 15, 680, corAmarelo);

        MaskFormatter mascaraCPF = null;
        MaskFormatter mascaraCEP = null;
        MaskFormatter mascaraNumero = null;
        MaskFormatter mascaraData = null;
        MaskFormatter mascaraSenha = null;
        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
            mascaraCEP = new MaskFormatter("#####-###");
            mascaraNumero = new MaskFormatter("(##) # ####-####");
            mascaraData = new MaskFormatter("##/##/####");
            mascaraSenha = new MaskFormatter("####");
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        JTextField getNome = campos.campoCadastrar(100, 210, 300, 30);
        JTextField getUsuario = campos.campoCadastrar(40, 280, 200, 30);
        JTextField getEndereco = campos.campoCadastrar(40, 420, 240, 30);
        JTextField getRG = campos.campoCadastrar(260, 350, 200, 30);
        JTextField getEmail = campos.campoCadastrar(40, 490, 240, 30);

        JFormattedTextField getData = new JFormattedTextField(mascaraData);
        getData.setFont(new Font("Arial", Font.PLAIN, 20));
        getData.setBounds(260, 280, 120, 30);
        getData.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCEP = new JFormattedTextField(mascaraCEP);
        getCEP.setFont(new Font("Arial", Font.PLAIN, 20));
        getCEP.setBounds(300, 420, 160, 30);
        getCEP.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCPF = new JFormattedTextField(mascaraCPF);
        getCPF.setFont(new Font("Arial", Font.PLAIN, 20));
        getCPF.setBounds(40, 350, 200, 30);
        getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCelular = new JFormattedTextField(mascaraNumero);
        getCelular.setFont(new Font("Arial", Font.PLAIN, 20));
        getCelular.setBounds(300, 490, 160, 30);
        getCelular.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JPasswordField getSenha = new JPasswordField();
        getSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        getSenha.setBounds(100, 560, 300, 30);
        getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));

        Cadastrar.setBackground(Color.white);
        Cadastrar.setLayout(null);
        Cadastrar.setVisible(false);
        JLabel labelCadastrar = textos.textosAlinhados("Cadastrar", 210, 110, 300, 40, 30, corCinza);
        Cadastrar.add(labelCadastrar);
        Cadastrar.add(textos.textos("Nome Completo", 100, 180, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("CEP", 300, 390, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("Endereço", 40, 390, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("Data de Nascimento", 260, 250, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("Usuário", 40, 250, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("CPF", 40, 320, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("RG", 260, 320, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("Email", 40, 460, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("Numero de celular", 300, 460, 300, 30, 20, corCinza));
        Cadastrar.add(textos.textos("Senha", 100, 530, 300, 30, 20, corCinza));
        Cadastrar.add(botaoLogin);
        Cadastrar.add(borderLogin);
        Cadastrar.add(registrar);
        Cadastrar.add(getSenha);
        Cadastrar.add(getCelular);
        Cadastrar.add(getEmail);
        Cadastrar.add(getCPF);
        Cadastrar.add(getRG);
        Cadastrar.add(getNome);
        Cadastrar.add(getUsuario);
        Cadastrar.add(getData);
        Cadastrar.add(getEndereco);
        Cadastrar.add(getCEP);

        // TELA ESCOLHER CONTA

        JRadioButton tituloCorrente = new JRadioButton("Conta Corrente");
        tituloCorrente.setBounds(20, 70, 460, 30);
        tituloCorrente.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloCorrente.setBackground(Color.white);
        tituloCorrente.setForeground(corCinza);
        JTextArea descrCorrente = new JTextArea(
                "Movimente seu dinheiro da forma como desejar, com direito a um cheque especial para utilizar quando quiser");
        descrCorrente.setBounds(20, 100, 460, 100);
        descrCorrente.setFont(new Font("Arial", Font.PLAIN, 20));
        descrCorrente.setForeground(Color.white);
        descrCorrente.setBackground(corAzul);
        descrCorrente.setEditable(false);
        descrCorrente.setLineWrap(true);
        descrCorrente.setBorder(BorderFactory.createCompoundBorder(descrCorrente.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JRadioButton tituloPoupanca = new JRadioButton("Conta Poupança");
        tituloPoupanca.setBounds(20, 220, 460, 30);
        tituloPoupanca.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloPoupanca.setForeground(corCinza);
        tituloPoupanca.setBackground(Color.white);
        JTextArea descrPoupanca = new JTextArea(
                "Ideal para quem deseja guardar dinheiro, com rendimento de 6,17% ao ano");
        descrPoupanca.setBounds(20, 250, 460, 100);
        descrPoupanca.setFont(new Font("Arial", Font.PLAIN, 20));
        descrPoupanca.setForeground(Color.white);
        descrPoupanca.setBackground(corAzul);
        descrPoupanca.setEditable(false);
        descrPoupanca.setLineWrap(true);
        descrPoupanca.setBorder(BorderFactory.createCompoundBorder(descrPoupanca.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(tituloPoupanca);
        grupo.add(tituloCorrente);

        JButton finalizar = botoes.botao("Finalizar", corAzul, Color.white, 100, 550, 300, 40, 30, 1, 3, 3, 1,
                Color.white);

        JFormattedTextField senhaConta = new JFormattedTextField(mascaraSenha);
        senhaConta.setBounds(200, 410, 100, 50);
        senhaConta.setFont(new Font("Arial", Font.PLAIN, 30));
        senhaConta.setHorizontalAlignment(SwingConstants.CENTER);
        senhaConta.setForeground(Color.white);
        senhaConta.setBackground(corCinza);
        senhaConta.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        JPanel tpConta = new JPanel();
        tpConta.setBackground(corCinza);
        tpConta.setLayout(null);
        tpConta.setVisible(false);
        tpConta.add(textos.textos(" Selecione o tipo de conta", 50, 0, 470, 50, 30, Color.white, corAzul, 0, 0, 3, 0,
                Color.white));
        tpConta.add(textos.textosAlinhados("Defina uma senha de 4 digitos para a conta", 0, 370, 520, 50, 20,
                Color.white, corCinza));
        tpConta.add(tituloCorrente);
        tpConta.add(descrCorrente);
        tpConta.add(tituloPoupanca);
        tpConta.add(descrPoupanca);
        tpConta.add(botoes.botaoVoltar(tpConta, Cadastrar));
        tpConta.add(finalizar);
        tpConta.add(senhaConta);

        recuperar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelRecupera.setVisible(true);
                telaInicial.setVisible(false);
            }
        });
        botaoRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Entrar.setVisible(false);
                Cadastrar.setVisible(true);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                botaoRegister.setText("Cadastrar");
                borderRegister.setBounds(490, 150, 30, 680);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                botaoRegister.setText(" Cadastrar");
                borderRegister.setBounds(485, 150, 35, 680);
            }
        });
        botaoLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Cadastrar.setVisible(false);
                Entrar.setVisible(true);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                botaoLogin.setText(" Entrar");
                borderLogin.setBounds(0, 150, 10, 680);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                botaoLogin.setText("Entrar");
                borderLogin.setBounds(0, 150, 15, 680);
            }
        });
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(getCPF.getText().length());

                if (getNome.getText().isEmpty()) {
                    getNome.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getNome.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getUsuario.getText().isEmpty()) {
                    getUsuario.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getUsuario.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getData.getText().equals("  /  /    ")) {
                    getData.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getData.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getCPF.getText().equals("   .   .   -  ")) {
                    getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getRG.getText().isEmpty()) {
                    getRG.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getRG.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getEndereco.getText().isEmpty()) {
                    getEndereco.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getEndereco.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getCEP.getText().equals("     -   ")) {
                    getCEP.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getCEP.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getEmail.getText().isEmpty()) {
                    getEmail.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getEmail.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getCelular.getText().equals("(  )       -    ")) {
                    getCelular.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getCelular.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if (getSenha.getText().isEmpty()) {
                    getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else
                    getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));

                if (getNome.getText().isEmpty() || getUsuario.getText().isEmpty()
                        || getData.getText().equals("  /  /    ") || getCPF.getText().equals("   .   .   -  ")
                        || getRG.getText().isEmpty() || getEndereco.getText().isEmpty()
                        || getCEP.getText().equals("     -   ") || getEmail.getText().isEmpty()
                        || getCelular.getText().equals("(  )       -    ") || getSenha.getText().isEmpty()) {
                } else {
                    telaInicial.setVisible(false);
                    tpConta.setVisible(true);
                }

            }
        });
        tituloPoupanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tituloPoupanca.setBackground(corAmarelo);
                tituloPoupanca.setForeground(Color.black);
                tituloCorrente.setBackground(Color.white);
                tituloCorrente.setForeground(corCinza);
                if (senhaConta.getText().equals("    ")) {
                    finalizar.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.white));
                } else {
                    finalizar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
                    finalizar.setBackground(corAmarelo);
                    finalizar.setForeground(Color.black);
                }

            }
        });
        tituloCorrente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tituloPoupanca.setBackground(Color.white);
                tituloPoupanca.setForeground(corCinza);
                tituloCorrente.setBackground(corAmarelo);
                tituloCorrente.setForeground(Color.black);
                if (senhaConta.getText().equals("    ")) {
                    finalizar.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.white));
                } else {
                    finalizar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
                    finalizar.setBackground(corAmarelo);
                    finalizar.setForeground(Color.black);
                }
            }
        });
        logar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loginUsuario = usuario.getText();
                String loginSenha = new String(senha.getPassword());

                try {
                    ResultSet rs = con.BuscaUsuario(loginUsuario);
                    if (rs.next()) {
                        String bancoSenha = rs.getString("senha");
                        System.out.println(bancoSenha);
                        if (bancoSenha.equals(loginSenha)) {
                            System.out.println("Login realizado com sucesso");
                            ResultSet rsCliente = con.getUsuario(loginUsuario);
                            try {
                                while (rsCliente.next()) {
                                    cliente = new Cliente(rsCliente.getString("tipo"), rsCliente.getInt("id"),
                                            rsCliente.getString("nome"), rsCliente.getString("usuario"),
                                            rsCliente.getString("datanascimento"), rsCliente.getString("cpf"),
                                            rsCliente.getString("rg"), rsCliente.getString("endereco"),
                                            rsCliente.getString("cep"), rsCliente.getString("email"),
                                            rsCliente.getString("celular"), rsCliente.getString("senha"));
                                }
                            } catch (Exception eb) {
                                eb.printStackTrace();
                            }
                            ResultSet rsConta = con.getConta(cliente.getId());
                            try {
                                while (rsConta.next()) {
                                    conta = new Conta(rsConta.getInt("numero"), rsConta.getInt("agencia"),
                                            rsConta.getString("operacao"), rsConta.getDouble("saldo"),
                                            rsConta.getDouble("cheque"), rsConta.getInt("cliente"),
                                            rsConta.getString("senha"));
                                }
                            } catch (Exception en) {
                                en.printStackTrace();
                            }
                            senha.setText("");
                            usuario.setText("");
                            TelaPrincipal.setCliente(cliente);
                            TelaPrincipal.setConta(conta);
                            TelaPrincipal.atualizaTela();
                            telaInicial.setVisible(false);
                            telaPrincipal.setVisible(true);
                        } else
                            System.out.println("senha incorreta");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (senhaConta.getText().equals("    ")) {
                    senhaConta.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                } else {
                    if (tituloCorrente.isSelected() || tituloPoupanca.isSelected()) {
                        String operacao;
                        Double cheque;
                        if (tituloCorrente.isSelected()) {
                            operacao = "001";
                            cheque = 300.0;
                        } else {
                            operacao = "013";
                            cheque = 0.0;
                        }

                        String newSenha = new String(getSenha.getPassword());
                        con.Cadastra("cliente", getNome.getText(), getUsuario.getText(), getData.getText(),
                                getCPF.getText(), getRG.getText(), getEndereco.getText(), getCEP.getText(),
                                getEmail.getText(), getCelular.getText(), newSenha);

                        try {
                            ResultSet rsCliente = con.getUsuario(getUsuario.getText());
                            while (rsCliente.next()) {
                                cliente = new Cliente(rsCliente.getString("tipo"), rsCliente.getInt("id"),
                                        rsCliente.getString("nome"), rsCliente.getString("usuario"),
                                        rsCliente.getString("datanascimento"), rsCliente.getString("cpf"),
                                        rsCliente.getString("rg"), rsCliente.getString("endereco"),
                                        rsCliente.getString("cep"), rsCliente.getString("email"),
                                        rsCliente.getString("celular"), rsCliente.getString("senha"));
                            }
                            con.criaConta(cliente.getId(), cheque, operacao, senhaConta.getText());
                            ResultSet rsConta = con.getConta(cliente.getId());
                            try {
                                while (rsConta.next()) {
                                    conta = new Conta(rsConta.getInt("numero"), rsConta.getInt("agencia"),
                                            rsConta.getString("operacao"), rsConta.getDouble("saldo"),
                                            rsConta.getDouble("cheque"), rsConta.getInt("cliente"),
                                            rsConta.getString("senha"));
                                }
                            } catch (Exception el) {
                                el.printStackTrace();
                            }
                            Cadastrar.setVisible(false);
                            getNome.setText(""); 
                            getUsuario.setText(""); 
                            getData.setText("");
                            getCPF.setText("");
                            getRG.setText("");
                            getEndereco.setText("");
                            getCEP.setText("");
                            getEmail.setText("");
                            getCelular.setText("");
                            getSenha.setText(""); 
                            senhaConta.setText("");
                            Entrar.setVisible(true);
                            TelaPrincipal.setCliente(cliente);
                            TelaPrincipal.setConta(conta);
                            TelaPrincipal.atualizaTela();
                            tpConta.setVisible(false);
                            telaPrincipal.setVisible(true);

                        } catch (Exception es) {
                            es.printStackTrace();
                        }

                    }
                }
            }
        });

        telaInicial.setBounds(0, 0, 520, 720);
        Entrar.setBounds(0, 0, 520, 720);
        Cadastrar.setBounds(0, 0, 520, 720);

        telaInicial.add(textos.textos("C", 15, 10, 300, 60, 65, corAmarelo));
        telaInicial.add(textos.textos("oin", 60, 10, 300, 50, 40, corAmarelo));
        telaInicial.add(textos.textos("B", 50, 40, 300, 70, 60, corAmarelo));
        telaInicial.add(textos.textos("ag", 85, 30, 300, 70, 40, corAmarelo));
        telaInicial.add(textos.textos("Seu dinheiro", 150, 20, 300, 50, 30, Color.white));
        telaInicial.add(textos.textos("sempre com você!!", 170, 45, 300, 50, 30, Color.white));
        telaInicial.add(textos.textos(0, 0, 520, 110, corAzul, 0, 5, 0, 0, corAmarelo));
        telaInicial.add(Entrar);
        telaInicial.add(Cadastrar);

        JFrame janela = new JFrame();
        janela.setTitle("CoinBag");
        janela.setSize(520, 720);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setLayout(null);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);

        telaInicial.setSize(janela.getSize());

        telaPrincipal.setBounds(0, 0, 520, 720);
        tpConta.setBounds(0, 0, 520, 720);
        panelRecupera.setBounds(0, 0, 520, 720);
        panelSenha.setBounds(0, 0, 520, 720);
        Container Pane = janela.getContentPane();
        Pane.add(telaInicial);
        Pane.add(tpConta);
        Pane.add(panelRecupera);
        Pane.add(panelSenha);
        Pane.add(telaPrincipal);
        
    }
}
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.text.MaskFormatter;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class App {
    static Cliente cliente = null;
    static Conta conta = null;
	public static void main(String[] args) {
		Conexao con = new Conexao();
        Color corVerde = new Color(34,92,100);
        //Color corVerde = new Color(10,150,130);
        Color corAzul = new Color(34,92,150);
        Color corAmarelo = new Color(239,209,50);
        //Color corAzul = new Color(30,144,200);
        Color corCinza = new Color(240,240,240);
        Texto textos = new Texto();
        Botao botoes = new Botao();
        campoTxt campos = new campoTxt();
        JButton botaoLogin = botoes.botao("Entrar", corAmarelo, Color.black, 0, 110, 210, 40, 25);
        JButton botaoRegister = botoes.botao("Cadastrar", corAmarelo, Color.black, 300, 110, 210, 40, 25);
        JButton borderRegister = botoes.botao(corAmarelo, 485, 140, 30, 680);
        JButton borderLogin = botoes.botao(corAmarelo, 0, 140, 20, 680);
        JButton recuperar = botoes.botao("Esqueci a senha", Color.white, corAzul, 100, 340, 300, 20);
        JButton logar = botoes.botao("Entrar", corAmarelo, Color.black, 100, 370, 300, 40, 30);
        JButton registrar = botoes.botao("Cadastrar", corAmarelo, Color.black, 100, 610, 300, 40, 30);
        JButton finalizar = botoes.botao("Finalizar", corVerde, Color.white, 100, 500, 300, 40, 30);
        JButton voltar = botoes.botao("<", corVerde, Color.white, 0, 0, 50, 50, 30, 0, 0, 0, 2, Color.white);
        JButton voltarDepositar = botoes.botao("<", corAmarelo, corVerde, 0, 0, 50, 50, 30, 0, 0, 0, 2, Color.white);
        JButton voltarSacar = botoes.botao("<", corVerde, Color.white, 0, 0, 50, 50, 30, 0, 0, 0, 2, Color.white);
        JButton Prosseguir = botoes.botao("Prosseguir", corAmarelo, corVerde, 100, 500, 300, 40, 30);
        JButton botaoDepositar = botoes.botao("Depositar", corAmarelo, corVerde, 100, 500, 300, 40, 30);
        
        JButton sair = botoes.botao("Sair", corAmarelo, corVerde, 410, 0, 100, 50, 25, 0, 0, 0, 0, Color.white);

        JButton depositar = botoes.botao("Depositar",  Color.white, corVerde, 50, 140, 350, 50, 40, 1, 1, 5, 1, corVerde);
        JButton sacar = botoes.botao("Sacar",  Color.white, corVerde, 50, 210, 350, 50, 40, 1, 1, 5, 1, corVerde);
        JButton extrato = botoes.botao("Extrato",  Color.white, corVerde, 50, 280, 350, 50, 40, 1, 1, 5, 1, corVerde);
        JButton transferir = botoes.botao("Transferir",  Color.white, corVerde, 50, 350, 350, 50, 40, 1, 1, 5, 1, corVerde);
        JButton pix = botoes.botao("Pix",  Color.white, corVerde, 50, 420, 350, 50, 40, 1, 1, 5, 1, corVerde);
        JButton minhaConta = botoes.botao("Conta",  Color.white, corVerde, 50, 490, 350, 50, 40, 1, 1, 5, 1, corVerde);
        JButton iconDepositar = botoes.botao(corVerde, 0, 140, 50, 50, 50, 1, 1, 5, 1, corVerde);
        JButton iconSacar = botoes.botao(corVerde, 0, 210, 50, 50, 50, 1, 1, 5, 1, corVerde);
        JButton iconExtrato = botoes.botao(corVerde, 0, 280, 50, 50, 50, 1, 1, 5, 1, corVerde);
        JButton iconTransferir = botoes.botao(corVerde, 0, 350, 50, 50, 50, 1, 1, 5, 1, corVerde);
        JButton iconPix = botoes.botao(corVerde, 0, 420, 50, 50, 50, 1, 1, 5, 1, corVerde);
        JButton iconMinhaConta = botoes.botao(corVerde, 0, 490, 50, 50, 50, 1, 1, 5, 1, corVerde);
        
        JLabel erroNome = textos.textosInvisiveis("*Nome invalido*", 250, 180, 300, 30, 15, Color.red);
        JLabel erroEndereco = textos.textosInvisiveis("*Endereço invalido*", 250, 250, 300, 30, 15, Color.red);
        JLabel erroCPF = textos.textosInvisiveis("*CPF invalido*", 250, 320, 300, 30, 15, Color.red);
        JLabel erroRG = textos.textosInvisiveis("*RG invalido*", 250, 390, 300, 30, 15, Color.red);
        JLabel erroEmail = textos.textosInvisiveis("*Email invalido*", 250, 460, 300, 30, 15, Color.red);
        JLabel erroSenha = textos.textosInvisiveis("*Senha invalida*", 250, 530, 300, 30, 15, Color.red);
        JLabel bemVindo = textos.textos(" Bem Vindo Francisco Alisson", 0, 0, 410, 50, 25, corAmarelo, corVerde, 0, 0, 3, 0, corAmarelo);
        JLabel showSaldo = textos.textos("R$ 3.50", 400, 50, 110, 50, 25, corVerde, Color.white, 0, 0, 3, 10, corAmarelo);
        JPanel Entrar = new JPanel();
        JPanel Cadastrar = new JPanel();
        JPanel tpConta = new JPanel();
        JPanel Inicio = new JPanel();
        JPanel Carregamento = new JPanel();
        JPanel panelVerificaSenha = new JPanel();
        JPanel panelDepositar = new JPanel();

        

        JTextField usuario = campos.campo(100, 230, 300, 30, 15, Color.white, Color.black, 1, 2, 2, 1, Color.black);

        JPasswordField senha = new JPasswordField();
        senha.setBounds(100, 300, 300, 30);
        senha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        senha.setFont(new Font("Arial", Font.PLAIN, 15));
        senha.setBackground(Color.white);
        senha.setForeground(Color.black);
        

            MaskFormatter mascaraCPF = null;
            MaskFormatter mascaraCEP = null;
            MaskFormatter mascaraNumero = null;
            MaskFormatter mascaraData = null;
            MaskFormatter mascaraSenha = null;
            MaskFormatter mascaraValor = null;

        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
            mascaraCEP = new MaskFormatter("#####-###");
            mascaraNumero = new MaskFormatter("(##) # ####-####");
            mascaraData = new MaskFormatter("##/##/####");
            mascaraSenha = new MaskFormatter("####");
            mascaraValor = new MaskFormatter("###############");
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        JTextField getNome = campos.campo(100, 210, 300, 30, 15, Color.white, Color.black, 1, 2, 2, 1, Color.black);
        new JTextField();
        getNome.setFont(new Font("Arial", Font.PLAIN, 20));
        getNome.setBounds(100, 210, 300, 30);
        getNome.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JTextField getUsuario = new JTextField();
        getUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
        getUsuario.setBounds(40, 280, 200, 30);
        getUsuario.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getData = new JFormattedTextField(mascaraData);
        getData.setFont(new Font("Arial", Font.PLAIN, 20));
        getData.setBounds(260, 280, 120, 30);
        getData.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JTextField getEndereco = new JTextField();
        getEndereco.setFont(new Font("Arial", Font.PLAIN, 20));
        getEndereco.setBounds(40, 420, 240, 30);
        getEndereco.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCEP = new JFormattedTextField(mascaraCEP);
        getCEP.setFont(new Font("Arial", Font.PLAIN, 20));
        getCEP.setBounds(300, 420, 160, 30);
        getCEP.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCPF = new JFormattedTextField(mascaraCPF);
        getCPF.setFont(new Font("Arial", Font.PLAIN, 20));
        getCPF.setBounds(40, 350, 200, 30);
        getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JTextField getRG = new JTextField();
        getRG.setFont(new Font("Arial", Font.PLAIN, 20));
        getRG.setBounds(260, 350, 200, 30);
        getRG.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JTextField getEmail = new JTextField();
        getEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        getEmail.setBounds(40, 490, 240, 30);
        getEmail.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCelular = new JFormattedTextField(mascaraNumero);
        getCelular.setFont(new Font("Arial", Font.PLAIN, 20));
        getCelular.setBounds(300, 490, 160, 30);
        getCelular.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JPasswordField getSenha = new JPasswordField();
        getSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        getSenha.setBounds(100, 560, 300, 30);
        getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));


        JRadioButton tituloCorrente = new JRadioButton("Conta Corrente");
        tituloCorrente.setBounds(20, 70, 460, 30);
        tituloCorrente.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloCorrente.setBackground(corVerde);
        tituloCorrente.setForeground(Color.white);
        JTextArea descrCorrente = new JTextArea("Movimente seu dinheiro da forma como desejar, com direito a um cheque especial para utilizar quando quiser");
        descrCorrente.setBounds(20, 100, 460, 100);
        descrCorrente.setFont(new Font("Arial", Font.PLAIN, 20));
        descrCorrente.setForeground(corVerde);
        descrCorrente.setBackground(Color.white);
        descrCorrente.setEditable(false);
        descrCorrente.setLineWrap(true);
        JRadioButton tituloPoupanca = new JRadioButton("Conta Poupança");
        tituloPoupanca.setBounds(20, 220, 460, 30);
        tituloPoupanca.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloPoupanca.setForeground(Color.white);
        tituloPoupanca.setBackground(corVerde);
        JTextArea descrPoupanca = new JTextArea("Ideal para quem deseja guardar dinheiro, com rendimento de 6,17% ao ano");
        descrPoupanca.setBounds(20, 250, 460, 100);
        descrPoupanca.setFont(new Font("Arial", Font.PLAIN, 20));
        descrPoupanca.setForeground(corVerde);
        descrPoupanca.setBackground(Color.white);
        descrPoupanca.setEditable(false);
        descrPoupanca.setLineWrap(true);

        JFormattedTextField senhaConta = new JFormattedTextField(mascaraSenha);
        senhaConta.setBounds(0, 420, 520, 50);
        senhaConta.setFont(new Font("Arial", Font.PLAIN, 30));
        senhaConta.setHorizontalAlignment(SwingConstants.CENTER);
        senhaConta.setForeground(corVerde);
        senhaConta.setBackground(Color.WHITE);
        senhaConta.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));

        JFormattedTextField verificaSenha = new JFormattedTextField(mascaraSenha);
        verificaSenha.setBounds(200, 150, 100, 50);
        verificaSenha.setFont(new Font("Arial", Font.PLAIN, 30));
        verificaSenha.setHorizontalAlignment(SwingConstants.CENTER);
        verificaSenha.setForeground(Color.white);
        verificaSenha.setBackground(corAzul);
        verificaSenha.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        
        JFormattedTextField getValor = new JFormattedTextField(mascaraValor);
        getValor.setBounds(20, 150, 460, 50);
        getValor.setFont(new Font("Arial", Font.PLAIN, 30));
        getValor.setHorizontalAlignment(SwingConstants.CENTER);
        getValor.setForeground(Color.white);
        getValor.setBackground(corVerde);
        getValor.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));


        ButtonGroup grupo = new ButtonGroup();
        grupo.add(tituloPoupanca);
        grupo.add(tituloCorrente);


        

        Entrar.setBackground(Color.white);
        Entrar.setLayout(null);
        
        Entrar.setVisible(false);
        Entrar.add(textos.textosAlinhados("Entrar", 0, 110, 300, 40, 30, corAzul));
        Entrar.add(botaoRegister);
        Entrar.add(usuario);
        Entrar.add(textos.textos("Usuário", 100, 200, 300, 30, 20, corAzul));
        Entrar.add(senha);
        Entrar.add(textos.textos("Senha", 100, 270, 300, 30, 20, corAzul));
        Entrar.add(borderRegister);
        Entrar.add(recuperar);
        Entrar.add(logar);
        Entrar.add(textos.textos("C", 15, 10, 300, 60, 65, corAmarelo));
        Entrar.add(textos.textos("oin", 60, 10, 300, 50, 40, corAmarelo));
        Entrar.add(textos.textos("B", 50, 40, 300, 70, 60, corAmarelo));
        Entrar.add(textos.textos("ag", 85, 30, 300, 70, 40, corAmarelo));
        Entrar.add(textos.textos("Seu dinheiro", 150, 20, 300, 50, 30, Color.white));
        Entrar.add(textos.textos("sempre com você!!", 170, 45, 300, 50, 30, Color.white));

        Entrar.add(textos.textos(0, 0, 520, 110, corAzul, 0, 5, 0, 0, corAmarelo));
        
        Cadastrar.setBackground(Color.white);
        Cadastrar.setLayout(null);
        
        Cadastrar.setVisible(false);
        Cadastrar.add(textos.textosAlinhados("Cadastrar", 210, 110, 300, 40, 30, corVerde));
        Cadastrar.add(botaoLogin);
        Cadastrar.add(borderLogin);
        Cadastrar.add(textos.textos("Nome Completo", 100, 180, 300, 30, 20, corVerde));
        Cadastrar.add(getNome);
        Cadastrar.add(textos.textos("Usuário", 40, 250, 300, 30, 20, corVerde));
        Cadastrar.add(getUsuario);
        Cadastrar.add(textos.textos("Data de Nascimento", 260, 250, 300, 30, 20, corVerde));
        Cadastrar.add(getData);
        Cadastrar.add(textos.textos("Endereço", 40, 390, 300, 30, 20, corVerde));
        Cadastrar.add(getEndereco);
        Cadastrar.add(textos.textos("CEP", 300, 390, 300, 30, 20, corVerde));
        Cadastrar.add(getCEP);
        Cadastrar.add(textos.textos("CPF", 40, 320, 300, 30, 20, corVerde));
        Cadastrar.add(getCPF);
        Cadastrar.add(getRG);
        Cadastrar.add(textos.textos("RG", 260, 320, 300, 30, 20, corVerde));
        Cadastrar.add(getEmail);
        Cadastrar.add(textos.textos("Email", 40, 460, 300, 30, 20, corVerde));
        Cadastrar.add(getCelular);
        Cadastrar.add(textos.textos("Numero de celular", 300, 460, 300, 30, 20, corVerde));
        Cadastrar.add(getSenha);
        Cadastrar.add(textos.textos("Senha", 100, 530, 300, 30, 20, corVerde));
        Cadastrar.add(registrar);
        Cadastrar.add(erroNome);
        Cadastrar.add(erroCPF);
        Cadastrar.add(erroEmail);
        Cadastrar.add(erroEndereco);
        Cadastrar.add(erroRG);
        Cadastrar.add(erroSenha);
        Cadastrar.add(textos.textos("C", 15, 10, 300, 60, 65, corAmarelo));
        Cadastrar.add(textos.textos("oin", 60, 10, 300, 50, 40, corAmarelo));
        Cadastrar.add(textos.textos("B", 50, 40, 300, 70, 60, corAmarelo));
        Cadastrar.add(textos.textos("ag", 85, 30, 300, 70, 40, corAmarelo));
        Cadastrar.add(textos.textos("Seu dinheiro", 150, 20, 300, 50, 30, Color.white));
        Cadastrar.add(textos.textos("sempre com você!!", 170, 45, 300, 50, 30, Color.white));
        Cadastrar.add(textos.textos(0, 0, 520, 110, corVerde, 0, 5, 0, 0, corAmarelo));
        
        tpConta.setBackground(Color.white);
        tpConta.setLayout(null);
        
        tpConta.setVisible(false);
        tpConta.add(tituloCorrente);
        tpConta.add(descrCorrente);
        tpConta.add(tituloPoupanca);
        tpConta.add(descrPoupanca);
        tpConta.add(voltar);
        tpConta.add(finalizar);
        tpConta.add(textos.textos(" Selecione o tipo de conta", 50, 0, 470, 50, 30, Color.white, corVerde));
        tpConta.add(textos.textosAlinhados("Defina uma senha de 4 digitos para a conta", 0, 370, 520, 50, 20, Color.white, corVerde));
        tpConta.add(senhaConta);

        
        Inicio.setBackground(Color.white);
        Inicio.setLayout(null);
        Inicio.setVisible(true);
        Inicio.add(sair);
        Inicio.add(bemVindo);
        Inicio.add(textos.textos(" Saldo:", 0, 50, 400, 50, 25, corVerde, Color.white, 0, 0, 3, 0, corVerde));
        Inicio.add(showSaldo);
        Inicio.add(depositar);
        Inicio.add(sacar);
        Inicio.add(extrato);
        Inicio.add(transferir);
        Inicio.add(pix);
        Inicio.add(minhaConta);
        Inicio.add(iconDepositar);
        Inicio.add(iconSacar);
        Inicio.add(iconExtrato);
        Inicio.add(iconTransferir);
        Inicio.add(iconPix);
        Inicio.add(iconMinhaConta);
        JProgressBar barra = new JProgressBar(0, 100);

        barra.setBounds(200, 200, 300, 30);
        
        Carregamento.setBackground(corAzul);
        Carregamento.setLayout(null);
        
        Carregamento.setVisible(false);
        Carregamento.add(barra);

        panelVerificaSenha.setBackground(corAzul);
        panelVerificaSenha.setLayout(null);
        panelVerificaSenha.setVisible(false);
        panelVerificaSenha.add(textos.textosAlinhados(" Digite sua senha de 4 digitos ", 0, 100, 520, 50, 25, Color.white));
        panelVerificaSenha.add(verificaSenha);
        panelVerificaSenha.add(botaoDepositar);

        panelDepositar.setBackground(corVerde);
        panelDepositar.setLayout(null);
        panelDepositar.setVisible(false);
        panelDepositar.add(textos.textosAlinhados(" Digite o valor que deseja depositar ", 0, 100, 520, 50, 25, Color.white));
        panelDepositar.add(textos.textos(" Depositar ", 50, 0, 520, 50, 25, Color.white), corAmarelo);
        panelDepositar.add(getValor);
        panelDepositar.add(voltarDepositar);
        panelDepositar.add(Prosseguir);

        /*Timer tempo = new Timer(500,new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                barra.setVisible(true);
                if(barra.getValue() < 100){
                    Carregamento.setVisible(true);
                    
                }else{
                    Carregamento.setVisible(false);
                    Entrar.setVisible(true);
                }
                
            }
            
        });
        

        for(int i = 0; barra.getValue() < 100; i++){
            barra.setValue(i);
            System.out.println(barra.getValue());
            try { Thread.sleep(100); }
            catch (InterruptedException e) { return; }
        }*/

        tituloPoupanca.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                tituloPoupanca.setBackground(corAmarelo);
                tituloPoupanca.setForeground(Color.black);;
                tituloCorrente.setBackground(corVerde);
                tituloCorrente.setForeground(Color.white);;
                finalizar.setBackground(corAmarelo);
                finalizar.setForeground(Color.black);;
            }
        });
        tituloCorrente.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                tituloPoupanca.setBackground(corVerde);
                tituloPoupanca.setForeground(Color.white);;
                tituloCorrente.setBackground(corAmarelo);
                tituloCorrente.setForeground(Color.black);;
                finalizar.setBackground(corAmarelo);
                finalizar.setForeground(Color.black);
            }
        });

        botaoRegister.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Entrar.setVisible(false);
                Cadastrar.setVisible(true);
            }
        });
        borderRegister.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Entrar.setVisible(false);
                Cadastrar.setVisible(true);
            }
        });

        botaoLogin.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Cadastrar.setVisible(false);
                Entrar.setVisible(true);
            }
        });
        borderLogin.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Cadastrar.setVisible(false);
                Entrar.setVisible(true);
            }
        });

        sair.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Inicio.setVisible(false);
                Entrar.setVisible(true);
            }
        });

        registrar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(getNome.getText().isEmpty()){
                    getNome.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getNome.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getEndereco.getText().isEmpty()){
                    getEndereco.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getEndereco.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getRG.getText().isEmpty()){
                    getRG.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getRG.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getCPF.getText().isEmpty()){
                    getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getEmail.getText().isEmpty()){
                    getEmail.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getEmail.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getSenha.getText().isEmpty()){
                    getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getNome.getText().isEmpty() || getEndereco.getText().isEmpty() || getRG.getText().isEmpty() || getCPF.getText().isEmpty() || getEmail.getText().isEmpty() || getSenha.getText().isEmpty()){
                }else{
                    System.out.println(getCelular.getText() + "-" + getCPF.getText() + "-" + getCEP.getText() + "-" + getData.getText());
                    Cadastrar.setVisible(false);
                    tpConta.setVisible(true);
                }

            }
        });
        voltar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                tpConta.setVisible(false);
                Cadastrar.setVisible(true);
            }
        });
        voltarDepositar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelDepositar.setVisible(false);
                Inicio.setVisible(true);
            }
        });

        depositar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Inicio.setVisible(false);
                panelDepositar.setVisible(true);
            }
        });
        botaoDepositar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(cliente.getSenha() == verificaSenha.getText()){
                    con.DepositarPoupanca(Double.parseDouble( getValor.getText()) + conta.getSaldo(), cliente.getId());
                }
            }
        });

        Prosseguir.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelDepositar.setVisible(false);
                panelVerificaSenha.setVisible(true);
            }
        });

        logar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
            	String loginUsuario = usuario.getText();
            	String loginSenha = new String(senha.getPassword());

            	try {
            		ResultSet rs = con.BuscaUsuario(loginUsuario);
            		if(rs.next()) {
            			String bancoSenha = rs.getString("senha");
            			System.out.println(bancoSenha);
            			if(bancoSenha.equals(loginSenha) ) {
                            System.out.println("Login realizado com sucesso");
                            ResultSet rsCliente = con.getUsuario(loginUsuario);
                            try {
                                while (rsCliente.next()) {
                                    cliente = new Cliente(rsCliente.getInt("id"), rsCliente.getString("nome"), rsCliente.getString("usuario"), rsCliente.getString("datanascimento"), rsCliente.getString("cpf"), rsCliente.getString("rg"), rsCliente.getString("endereco"), rsCliente.getString("cep"), rsCliente.getString("email"), rsCliente.getString("celular"), rsCliente.getString("senha"));
                                }
                                bemVindo.setText(" Bem vindo " + cliente.getNome());
                            } catch (Exception eb) {
                                //TODO: handle exception
                            }
                            
            				Inicio.setVisible(true);
                            
            				Entrar.setVisible(false);
            			}else System.out.println("senha incorreta");
            		}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            }
        });
        finalizar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tituloCorrente.isSelected() || tituloPoupanca.isSelected()){
                    String newSenha = new String(getSenha.getPassword());
                    
                    /*System.out.println(getNome.getText());
                    System.out.println(getUsuario.getText());
                    System.out.println(getData.getText());
                    System.out.println(getCPF.getText());
                    System.out.println(getRG.getText());
                    System.out.println(getEndereco.getText());
                    System.out.println(getCEP.getText());
                    System.out.println(getEmail.getText());
                    System.out.println(getCelular.getText());
                    System.out.println(newSenha);*/
                    System.out.println(newSenha);
                    con.Cadastra(getNome.getText(), getUsuario.getText(), getData.getText(), getCPF.getText(), getRG.getText(), getEndereco.getText(), getCEP.getText(), getEmail.getText(), getCelular.getText(), newSenha);
                    
                    try {
                        System.out.println("entrou");
                        ResultSet rsCliente = con.getUsuario(getUsuario.getText());
                        while (rsCliente.next()) {
                            cliente = new Cliente(rsCliente.getInt("id"), rsCliente.getString("nome"), rsCliente.getString("usuario"), rsCliente.getString("datanascimento"), rsCliente.getString("cpf"), rsCliente.getString("rg"), rsCliente.getString("endereco"), rsCliente.getString("cep"), rsCliente.getString("email"), rsCliente.getString("celular"), rsCliente.getString("senha"));
                        }
                        bemVindo.setText("Bem Vindo " + cliente.getNome());
                        cliente.toString();
                        if(tituloCorrente.isSelected()){
                        }else{
                            try {
                                con.criaPoupanca(cliente.getId(), cliente.getCPF(), cliente.getEmail(), cliente.getCelular(), Integer.parseInt(senhaConta.getText()));
                            } catch (Exception el) {
                                el.printStackTrace();
                            }
                        } 
                        tpConta.setVisible(false);
                        Inicio.setVisible(true);

                    } catch (Exception es) {
                        es.printStackTrace();
                    }

                }else System.out.println("Selecione");
            }
        });
        

        JFrame janela = new JFrame();
        janela.setTitle("CoinBag");
        janela.setSize(520, 720);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setLayout(null);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);

        Entrar.setSize(janela.getSize());
        Cadastrar.setSize(janela.getSize());
        tpConta.setSize(janela.getSize());
        Inicio.setSize(janela.getSize());
        Carregamento.setSize(janela.getSize());
        panelVerificaSenha.setSize(janela.getSize());
        panelDepositar.setSize(janela.getSize());

        Container Pane = janela.getContentPane();
        Pane.add(Carregamento);
        Pane.add(Entrar);
        Pane.add(Cadastrar);
        Pane.add(tpConta);
        Pane.add(Inicio);
        Pane.add(panelVerificaSenha);
        Pane.add(panelDepositar);
    }


}
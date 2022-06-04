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
        //Color corVerde = new Color(34,92,100);
        //Color corVerde = new Color(10,150,130);
        Color corAzul = new Color(34,92,150);
        //Color corAmarelo = new Color(239,209,50);
        //Color corAzul = new Color(30,144,200);
        Color corVerde = new Color(67,87,114);
        Color corAmarelo = new Color(254,170,58);
        //Color corAzul = new Color(31,85,181);
        Color corCinza = new Color(240,240,240);
        Texto textos = new Texto();
        Botao botoes = new Botao();
        campoTxt campos = new campoTxt();
        JButton finalizar = botoes.botao("Finalizar", corVerde, Color.white, 100, 500, 300, 40, 30);
        JButton voltar = botoes.botao("<", corVerde, Color.white, 0, 0, 50, 50, 30, 0, 0, 0, 2, Color.white);
        JButton voltarDepositar = botoes.botao("<", corAmarelo, corVerde, 0, 0, 50, 50, 30, 0, 0, 0, 2, Color.white);
        JButton voltarSacar = botoes.botao("<", corVerde, Color.white, 0, 0, 50, 50, 30, 0, 0, 0, 2, Color.white);
        JButton Prosseguir = botoes.botao("Prosseguir", corAmarelo, corVerde, 100, 500, 300, 40, 30);
        JButton botaoDepositar = botoes.botao("Depositar", corAmarelo, corVerde, 100, 500, 300, 40, 30);
        
        JPanel Entrar = new JPanel();
        JPanel Cadastrar = new JPanel();
        JPanel tpConta = new JPanel();
        JPanel Inicio = new JPanel();
        JPanel Carregamento = new JPanel();
        JPanel panelVerificaSenha = new JPanel();
        JPanel panelDepositar = new JPanel();

        // TELA ENTRAR
        JButton registrar = botoes.botao("Cadastrar", corAmarelo, Color.black, 100, 610, 300, 40, 30);
        JButton botaoRegister = botoes.botao("Cadastrar", corAmarelo, Color.black, 300, 110, 210, 40, 25);
        JButton borderRegister = botoes.botao(corAmarelo, 485, 140, 30, 680);
        JButton recuperar = botoes.botao("Esqueci a senha", Color.white, corAzul, 100, 340, 300, 20);
        JButton logar = botoes.botao("Entrar", corAmarelo, Color.black, 100, 370, 300, 40, 30);
        JTextField usuario = campos.campo(100, 230, 300, 30, 15, Color.white, Color.black, 1, 2, 2, 1, Color.black);
        JPasswordField senha = new JPasswordField();
        senha.setBounds(100, 300, 300, 30);
        senha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        senha.setFont(new Font("Arial", Font.PLAIN, 15));
        senha.setBackground(Color.white);
        senha.setForeground(Color.black);

        Entrar.setBackground(Color.white);
        Entrar.setLayout(null);
        Entrar.setVisible(false);
        Entrar.add(textos.textosAlinhados("Entrar", 0, 110, 300, 40, 30, corAzul));
        Entrar.add(textos.textos("Usuário", 100, 200, 300, 30, 20, corAzul));
        Entrar.add(textos.textos("Senha", 100, 270, 300, 30, 20, corAzul));
        Entrar.add(textos.textos("C", 15, 10, 300, 60, 65, corAmarelo));
        Entrar.add(textos.textos("oin", 60, 10, 300, 50, 40, corAmarelo));
        Entrar.add(textos.textos("B", 50, 40, 300, 70, 60, corAmarelo));
        Entrar.add(textos.textos("ag", 85, 30, 300, 70, 40, corAmarelo));
        Entrar.add(textos.textos("Seu dinheiro", 150, 20, 300, 50, 30, Color.white));
        Entrar.add(textos.textos("sempre com você!!", 170, 45, 300, 50, 30, Color.white));
        Entrar.add(textos.textos(0, 0, 520, 110, corAzul, 0, 5, 0, 0, corAmarelo));
        Entrar.add(borderRegister);
        Entrar.add(recuperar);
        Entrar.add(logar);
        Entrar.add(senha);
        Entrar.add(botaoRegister);
        Entrar.add(usuario);

        // TELA CADASTRAR
        JButton botaoLogin = botoes.botao("Entrar", corAmarelo, Color.black, 0, 110, 210, 40, 25);
        JButton borderLogin = botoes.botao(corAmarelo, 0, 140, 20, 680);
        
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
        JTextField getNome = campos.campo(100, 210, 300, 30, 15, Color.white, Color.black, 1, 2, 2, 1, Color.black);
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

        Cadastrar.setBackground(Color.white);
        Cadastrar.setLayout(null);
        Cadastrar.setVisible(false);
        Cadastrar.add(textos.textosAlinhados("Cadastrar", 210, 110, 300, 40, 30, corVerde));
        Cadastrar.add(textos.textos("Nome Completo", 100, 180, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("CEP", 300, 390, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("Endereço", 40, 390, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("Data de Nascimento", 260, 250, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("Usuário", 40, 250, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("CPF", 40, 320, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("RG", 260, 320, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("Email", 40, 460, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("Numero de celular", 300, 460, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("Senha", 100, 530, 300, 30, 20, corVerde));
        Cadastrar.add(textos.textos("C", 15, 10, 300, 60, 65, corAmarelo));
        Cadastrar.add(textos.textos("oin", 60, 10, 300, 50, 40, corAmarelo));
        Cadastrar.add(textos.textos("B", 50, 40, 300, 70, 60, corAmarelo));
        Cadastrar.add(textos.textos("ag", 85, 30, 300, 70, 40, corAmarelo));
        Cadastrar.add(textos.textos("Seu dinheiro", 150, 20, 300, 50, 30, Color.white));
        Cadastrar.add(textos.textos("sempre com você!!", 170, 45, 300, 50, 30, Color.white));
        Cadastrar.add(textos.textos(0, 0, 520, 110, corVerde, 0, 5, 0, 0, corAmarelo));
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

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(tituloPoupanca);
        grupo.add(tituloCorrente);

        JFormattedTextField senhaConta = new JFormattedTextField(mascaraSenha);
        senhaConta.setBounds(0, 420, 520, 50);
        senhaConta.setFont(new Font("Arial", Font.PLAIN, 30));
        senhaConta.setHorizontalAlignment(SwingConstants.CENTER);
        senhaConta.setForeground(corVerde);
        senhaConta.setBackground(Color.WHITE);
        senhaConta.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        
        tpConta.setBackground(Color.white);
        tpConta.setLayout(null);
        tpConta.setVisible(false);
        tpConta.add(textos.textos(" Selecione o tipo de conta", 50, 0, 470, 50, 30, Color.white, corVerde));
        tpConta.add(textos.textosAlinhados("Defina uma senha de 4 digitos para a conta", 0, 370, 520, 50, 20, Color.white, corVerde));
        tpConta.add(tituloCorrente);
        tpConta.add(descrCorrente);
        tpConta.add(tituloPoupanca);
        tpConta.add(descrPoupanca);
        tpConta.add(voltar);
        tpConta.add(finalizar);
        tpConta.add(senhaConta);

        // TELA INICIAL
        JButton sair = botoes.botao(" Sair", corAmarelo, Color.black, 410, 0, 100, 50, 25, 0, 2, 0, 0, Color.white);
        JButton depositar = botoes.botaoAlinhado(" Depositar",  corVerde, Color.white, 50, 210, 350, 50, 40, 1, 1, 5, 1, corAmarelo);
        JButton sacar = botoes.botaoAlinhado(" Sacar",  corVerde, Color.white, 50, 280, 350, 50, 40, 1, 1, 5, 1, corAmarelo);
        JButton extrato = botoes.botaoAlinhado(" Extrato",  corVerde, Color.white, 50, 350, 350, 50, 40, 1, 1, 5, 1, corAmarelo);
        JButton transferir = botoes.botaoAlinhado(" Transferir",  corVerde, Color.white, 50, 420, 350, 50, 40, 1, 1, 5, 1, corAmarelo);
        JButton pix = botoes.botaoAlinhado(" Pix",  corVerde, Color.white, 50, 490, 350, 50, 40, 1, 1, 5, 1, corAmarelo);
        JButton minhaConta = botoes.botaoAlinhado(" Conta",  corVerde, Color.white, 50, 560, 350, 50, 40, 1, 1, 5, 1, corAmarelo);
        JButton iconDepositar = botoes.botao(corAmarelo, 0, 210, 50, 50, 50, 1, 1, 5, 1, corAmarelo);
        JButton iconSacar = botoes.botao(corAmarelo, 0, 280, 50, 50, 50, 1, 1, 5, 1, corAmarelo);
        JButton iconExtrato = botoes.botao(corAmarelo, 0, 350, 50, 50, 50, 1, 1, 5, 1, corAmarelo);
        JButton iconTransferir = botoes.botao(corAmarelo, 0, 420, 50, 50, 50, 1, 1, 5, 1, corAmarelo);
        JButton iconPix = botoes.botao(corAmarelo, 0, 490, 50, 50, 50, 1, 1, 5, 1, corAmarelo);
        JButton iconMinhaConta = botoes.botao(corAmarelo, 0, 560, 50, 50, 50, 1, 1, 5, 1, corAmarelo);
        
        JLabel bemVindo = textos.textos(" Bem Vindo Francisco Alisson", 0, 0, 410, 50, 25, Color.white, corAzul, 0, 5, 0, 0, corAmarelo);
        JLabel showSaldo = textos.textos(" R$ 3.50", 300, 70, 210, 50, 25, Color.black, corAmarelo, 0, 0, 0, 0, corVerde);
        JLabel showCheque = textos.textos(" R$ 300", 400, 120, 110, 40, 20, Color.white, corAzul, 0, 0, 3, 0, Color.white);
        
        Inicio.setBackground(corVerde);
        Inicio.setLayout(null);
        Inicio.setVisible(true);
        Inicio.add(sair);
        Inicio.add(bemVindo);
        Inicio.add(textos.textos(0, 50, 520, 20, corAzul, 0, 0, 0, 0, corAmarelo));
        Inicio.add(textos.textos(480, 160, 50, 700, Color.white, 0, 0, 0, 0, Color.white));
        Inicio.add(textos.textos(" Saldo:", 0, 70, 300, 50, 25, Color.black, corAmarelo, 0, 0, 0, 0, corVerde));
        Inicio.add(textos.textos(" Cheque especial:", 0, 120, 400, 40, 20, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        Inicio.add(showSaldo);
        Inicio.add(showCheque);
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
        // TELA DEPOSITAR


        
        JFormattedTextField verificaSenha = new JFormattedTextField(mascaraSenha);
        verificaSenha.setBounds(200, 150, 100, 50);
        verificaSenha.setFont(new Font("Arial", Font.PLAIN, 30));
        verificaSenha.setHorizontalAlignment(SwingConstants.CENTER);
        verificaSenha.setForeground(Color.white);
        verificaSenha.setBackground(corAzul);
        verificaSenha.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        
        JTextField getValor = new JTextField();
        getValor.setBounds(20, 150, 460, 50);
        getValor.setFont(new Font("Arial", Font.PLAIN, 30));
        getValor.setHorizontalAlignment(SwingConstants.CENTER);
        getValor.setForeground(Color.white);
        getValor.setBackground(corVerde);
        getValor.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

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
                System.out.println(getCPF.getText().length());
                
                if(getNome.getText().isEmpty()){
                    getNome.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getNome.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getUsuario.getText().isEmpty()){
                    getUsuario.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getUsuario.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getData.getText().equals("  /  /    ")){
                    getData.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getData.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getCPF.getText().equals("   .   .   -  ")){
                    getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getRG.getText().isEmpty()){
                    getRG.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getRG.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getEndereco.getText().isEmpty()){
                    getEndereco.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getEndereco.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getCEP.getText().equals("     -   ")){
                    getCEP.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getCEP.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getEmail.getText().isEmpty()){
                    getEmail.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getEmail.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getCelular.getText().equals("(  )       -    ")){
                    getCelular.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getCelular.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                if(getSenha.getText().isEmpty()){
                    getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
                
                if(getNome.getText().isEmpty() || getUsuario.getText().isEmpty() || getData.getText().equals("  /  /    ") || getCPF.getText().equals("   .   .   -  ") || getRG.getText().isEmpty() || getEndereco.getText().isEmpty() || getCEP.getText().equals("     -   ") || getEmail.getText().isEmpty() || getCelular.getText().equals("(  )       -    ") || getSenha.getText().isEmpty()){
                }else{
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
                System.out.println(conta.getSenha());
                System.out.println(verificaSenha.getText());
                System.out.println(getValor.getText());
                System.out.println(conta.getSaldo());
                if(conta.getSenha().equals(verificaSenha.getText())){
                    double valor = Double.parseDouble(getValor.getText()) + conta.getSaldo();
                    con.DepositarPoupanca(valor, cliente.getId());
                    conta.setSaldo(valor);
                    showSaldo.setText(Double.toString(conta.getSaldo()));
                    Inicio.setVisible(true);
                    panelVerificaSenha.setVisible(false);
                    getValor.setText("");
                    verificaSenha.setText("");
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
                                while(rsCliente.next()) {
                                    cliente = new Cliente(rsCliente.getInt("id"), rsCliente.getString("nome"), rsCliente.getString("usuario"), rsCliente.getString("datanascimento"), rsCliente.getString("cpf"), rsCliente.getString("rg"), rsCliente.getString("endereco"), rsCliente.getString("cep"), rsCliente.getString("email"), rsCliente.getString("celular"), rsCliente.getString("senha"));
                                }
                                bemVindo.setText(" Bem vindo " + cliente.getNome());
                            } catch (Exception eb) {
                                eb.printStackTrace();
                            }
                            ResultSet rsConta = con.getConta(cliente.getId());
                            try {
                                while(rsConta.next()){
                                    conta = new Conta(rsConta.getInt("numero"), rsConta.getString("pixnumero"), rsConta.getString("pixemail"), rsConta.getString("pixcpf"), rsConta.getInt("agencia"), rsConta.getInt("operacao"), rsConta.getDouble("saldo"), rsConta.getDouble("cheque"), rsConta.getInt("cliente"), rsConta.getString("senha"));
                                }
                                showSaldo.setText(Double.toString(conta.getSaldo()));
                                System.out.println(conta.getSaldo());
                            } catch (Exception en) {
                                en.printStackTrace();
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
                if(senhaConta.getText().equals("    ")){
                    senhaConta.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                }else{
                    if(tituloCorrente.isSelected() || tituloPoupanca.isSelected()){
                        String operacao;
                        if(tituloCorrente.isSelected()){
                            operacao = "001";
                        }else operacao = "013";

                        String newSenha = new String(getSenha.getPassword());
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
                            con.criaConta(cliente.getId(), cliente.getCPF(), cliente.getEmail(), cliente.getCelular(), operacao, senhaConta.getText());
                            ResultSet rsConta = con.getConta(cliente.getId());
                            try {
                                while(rsConta.next()){
                                    conta = new Conta(rsConta.getInt("numero"), rsConta.getString("pixnumero"), rsConta.getString("pixemail"), rsConta.getString("pixcpf"), rsConta.getInt("agencia"), rsConta.getInt("operacao"), rsConta.getDouble("saldo"), rsConta.getDouble("cheque"), rsConta.getInt("cliente"), rsConta.getString("senha"));
                                }
                            } catch (Exception el) {
                                el.printStackTrace();
                            }
                            tpConta.setVisible(false);
                            Inicio.setVisible(true);
    
                        } catch (Exception es) {
                            es.printStackTrace();
                        }
    
                    }else System.out.println("Selecione");
                }
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
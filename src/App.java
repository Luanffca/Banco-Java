import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Instant;
import java.sql.Date;

public class App {
    static Cliente cliente = null;
    static Conta conta = null;
    static Extrato extrato = null;
    static int qntExtratos;
    static JLabel saldoInicial;
    static JLabel chequeInicial;
    static JLabel saldoSaque;
    static JLabel chequeSaque;
    static JTextField getDeposito;

    static Conexao con = new Conexao();
	public static void main(String[] args) {
		
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
        JButton voltarExtrato = botoes.botao(" <", corAmarelo, Color.white, 0, 0, 50, 50, 30, 0, 0, 3, 2, Color.white);
        
        JPanel Entrar = new JPanel();
        JPanel Cadastrar = new JPanel();
        JPanel tpConta = new JPanel();
        JPanel Inicio = new JPanel();
        JPanel panelVerificaSenha = new JPanel();
        JPanel panelSenhaSaque = new JPanel();
        JPanel panelSenhaTranferencia = new JPanel();
        JPanel panelDepositar = new JPanel();
        JPanel panelSacar = new JPanel();
        JPanel panelTransferir = new JPanel();
        JPanel panelExtrato = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JPanel panelRoll = new JPanel();

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
        JLabel labelEntrar = textos.textosAlinhados("Entrar", 0, 110, 300, 40, 30, corAzul);
        Entrar.add(labelEntrar);
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
        JLabel labelCadastrar = textos.textosAlinhados("Cadastrar", 210, 110, 300, 40, 30, corVerde);
        Cadastrar.add(labelCadastrar);
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
        JButton depositar = botoes.botaoInicial(" Depositar",50, 210);
        JButton sacar = botoes.botaoInicial(" Sacar",50, 280);
        JButton extrato = botoes.botaoInicial(" Extrato",50, 350);
        JButton transferir = botoes.botaoInicial(" Transferir",50, 420);
        JButton pix = botoes.botaoInicial(" Pix",50, 490);
        JButton minhaConta = botoes.botaoInicial(" Conta",50, 560);
        JButton iconDepositar = botoes.botao(corAmarelo, 0, 210, 50, 50, 1, 1, 5, 1, Color.white);
        JButton iconSacar = botoes.botao(corAmarelo, 0, 280, 50, 50, 1, 1, 5, 1, Color.white);
        JButton iconExtrato = botoes.botao(corAmarelo, 0, 350, 50, 50, 1, 1, 5, 1, Color.white);
        JButton iconTransferir = botoes.botao(corAmarelo, 0, 420, 50, 50, 1, 1, 5, 1, Color.white);
        JButton iconPix = botoes.botao(corAmarelo, 0, 490, 50, 50, 1, 1, 5, 1, Color.white);
        JButton iconMinhaConta = botoes.botao(corAmarelo, 0, 560, 50, 50, 1, 1, 5, 1, Color.white);
        
        JLabel bemVindo = textos.textos(" Bem Vindo Francisco Alisson", 0, 0, 410, 50, 25, Color.white, corAzul, 0, 5, 0, 0, corAmarelo);
        saldoInicial = textos.textos(" R$ 3.50", 300, 70, 210, 50, 25, Color.black, corAmarelo, 0, 0, 0, 0, corVerde);
        chequeInicial = textos.textos(" R$ 300", 400, 120, 110, 40, 20, Color.white, corAzul, 0, 0, 3, 0, Color.white);
        
        Inicio.setBackground(corVerde);
        Inicio.setLayout(null);
        Inicio.setVisible(true);
        Inicio.add(sair);
        Inicio.add(bemVindo);
        Inicio.add(textos.textos(0, 50, 520, 20, corAzul, 0, 0, 0, 0, corAmarelo));
        Inicio.add(textos.textos(480, 160, 50, 700, Color.white, 0, 0, 0, 0, Color.white));
        Inicio.add(textos.textos(" Saldo:", 0, 70, 300, 50, 25, Color.black, corAmarelo, 0, 0, 0, 0, corVerde));
        Inicio.add(textos.textos(" Cheque especial:", 0, 120, 400, 40, 20, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        Inicio.add(saldoInicial);
        Inicio.add(chequeInicial);
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

        //TELA VERIFICAR SENHA
        JButton validar = botoes.botao("Validar", corAmarelo, corVerde, 100, 500, 300, 40, 30);
        JFormattedTextField verifica = new JFormattedTextField(mascaraSenha);
        verifica.setBounds(200, 150, 100, 50);
        verifica.setFont(new Font("Arial", Font.PLAIN, 30));
        verifica.setHorizontalAlignment(SwingConstants.CENTER);
        verifica.setForeground(Color.white);
        verifica.setBackground(corAzul);
        verifica.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        panelVerificaSenha.setBackground(corAzul);
        panelVerificaSenha.setLayout(null);
        panelVerificaSenha.setVisible(false);
        panelVerificaSenha.add(textos.textosAlinhados(" Digite sua senha de 4 digitos ", 0, 100, 520, 50, 25, Color.white));
        panelVerificaSenha.add(validar);
        panelVerificaSenha.add(verifica);
        //TELA VERIFICAR SENHA DEPOSITO
        //TELA VERIFICAR SENHA SAQUE
        JButton botaoSacar = botoes.botao("Sacar", corAmarelo, corVerde, 100, 500, 300, 40, 30);
        JFormattedTextField senhaSaque = new JFormattedTextField(mascaraSenha);
        senhaSaque.setBounds(200, 150, 100, 50);
        senhaSaque.setFont(new Font("Arial", Font.PLAIN, 30));
        senhaSaque.setHorizontalAlignment(SwingConstants.CENTER);
        senhaSaque.setForeground(Color.white);
        senhaSaque.setBackground(corAzul);
        senhaSaque.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        panelSenhaSaque.setBackground(corAzul);
        panelSenhaSaque.setLayout(null);
        panelSenhaSaque.setVisible(false);
        panelSenhaSaque.add(textos.textosAlinhados(" Digite sua senha de 4 digitos ", 0, 100, 520, 50, 25, Color.white));
        panelSenhaSaque.add(senhaSaque);
        panelSenhaSaque.add(botaoSacar);

        //TELA VERIFICAR SENHA TRANSFERENCIA
        JButton botaoTransferir = botoes.botao("Transferir", corAmarelo, corVerde, 100, 500, 300, 40, 30);
        JFormattedTextField senhaTranferencia = new JFormattedTextField(mascaraSenha);
        senhaTranferencia.setBounds(200, 150, 100, 50);
        senhaTranferencia.setFont(new Font("Arial", Font.PLAIN, 30));
        senhaTranferencia.setHorizontalAlignment(SwingConstants.CENTER);
        senhaTranferencia.setForeground(Color.white);
        senhaTranferencia.setBackground(corAzul);
        senhaTranferencia.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        panelSenhaTranferencia.setBackground(corAzul);
        panelSenhaTranferencia.setLayout(null);
        panelSenhaTranferencia.setVisible(false);
        panelSenhaTranferencia.add(textos.textosAlinhados(" Digite sua senha de 4 digitos ", 0, 100, 520, 50, 25, Color.white));
        panelSenhaTranferencia.add(senhaTranferencia);
        panelSenhaTranferencia.add(botaoTransferir);

        //TELA DEPOSITAR
        JButton segueDeposito = botoes.botao("Prosseguir", corAzul, Color.white, 100, 500, 300, 40, 30, 1, 3, 3, 1, Color.white);
        getDeposito = new JTextField();
        getDeposito.setBounds(20, 150, 460, 50);
        getDeposito.setFont(new Font("Arial", Font.PLAIN, 30));
        getDeposito.setHorizontalAlignment(SwingConstants.CENTER);
        getDeposito.setForeground(Color.white);
        getDeposito.setBackground(corVerde);
        getDeposito.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        panelDepositar.setBackground(corVerde);
        panelDepositar.setLayout(null);
        panelDepositar.setVisible(false);
        panelDepositar.add(textos.textosAlinhados(" Digite o valor que deseja depositar ", 0, 100, 520, 50, 25, Color.white));
        panelDepositar.add(textos.textos(" Depositar ", 50, 0, 520, 50, 25, Color.white));
        panelDepositar.add(getDeposito);
        panelDepositar.add(botoes.botaoVoltar(panelDepositar, Inicio));
        panelDepositar.add(segueDeposito);

        //TELA SACAR
        JButton segueSaque = botoes.botao("Prosseguir", corAzul, Color.white, 100, 500, 300, 40, 30, 1, 3, 3, 1, Color.white);
        segueSaque.setVisible(false);
        JLabel erroSaque = textos.textosAlinhados("Prosseguir", 100, 500, 300, 40, 30, corAzul, Color.white, 1, 3, 3, 1, Color.white);
        JTextField getSaque = new JTextField();
        getSaque.setBounds(20, 150, 460, 50);
        getSaque.setFont(new Font("Arial", Font.PLAIN, 30));
        getSaque.setHorizontalAlignment(SwingConstants.CENTER);
        getSaque.setForeground(Color.white);
        getSaque.setBackground(corVerde);
        getSaque.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        saldoSaque = textos.textos(" R$ 3.50", 330, 210, 150, 50, 25, corVerde, corAmarelo, 0, 0, 0, 0, corAmarelo);
        chequeSaque = textos.textos(" R$ 300", 340, 260, 140, 40, 20, corVerde, corAmarelo, 0, 0, 0, 0, corAmarelo);

        panelSacar.setBackground(corVerde);
        panelSacar.setLayout(null);
        panelSacar.setVisible(false);
        panelSacar.add(botoes.botaoVoltar(panelSacar, Inicio));
        panelSacar.add(getSaque);
        panelSacar.add(saldoSaque);
        panelSacar.add(chequeSaque);
        panelSacar.add(segueSaque);
        panelSacar.add(erroSaque);
        panelSacar.add(textos.textos(" Saldo:", 20, 210, 380, 50, 25, corVerde, corAmarelo, 0, 0, 0, 0, corAmarelo));
        panelSacar.add(textos.textos(" Cheque especial:", 20, 260, 380, 40, 20, corVerde, corAmarelo, 0, 0, 0, 0, corAmarelo));
        panelSacar.add(textos.textosAlinhados(" Digite o valor que deseja Sacar ", 0, 100, 520, 50, 25, Color.white));
        panelSacar.add(textos.textos(" Sacar ", 50, 0, 520, 50, 25, Color.white));
        //TELA TRANSFERIR
        panelTransferir.setBackground(corVerde);
        panelTransferir.setLayout(null);
        panelTransferir.setVisible(false);
        panelTransferir.add(botoes.botaoVoltar(panelTransferir, Inicio));
        panelTransferir.add(textos.textos(" Transferir ", 50, 0, 520, 50, 25, Color.white));
        //TELA EXTRATO

        panelRoll.setLayout(new GridLayout(0, 1, 10, 10));
        panelRoll.setVisible(true);
        panelRoll.setBackground(corVerde);
        panelRoll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        
        scroll.setBackground(corVerde);
        scroll.setViewportView(panelRoll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVisible(true);
        scroll.setLocation(0, 50);
        scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));

        panelExtrato.setBackground(corVerde);
        panelExtrato.setLayout(null);
        panelExtrato.setVisible(false);
        panelExtrato.setBackground(corVerde);
        panelExtrato.add(textos.textos(" Extrato ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        panelExtrato.add(scroll);
        panelExtrato.add(voltarExtrato);



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
        getSaque.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(KeyEvent e){
                if(getSaque.getText().isEmpty()){
                    saldoSaque.setText(Double.toString(conta.getSaldo()));
                    chequeSaque.setText(Double.toString(conta.getCheque()));
                    segueSaque.setVisible(false);
                    erroSaque.setVisible(true);
                }
                char lastChar = getSaque.getText().charAt(getSaque.getText().length() - 1);
                if(lastChar == '0' || lastChar == '1' || lastChar == '2' || lastChar == '3' || lastChar == '4' || lastChar == '5' || lastChar == '6' || lastChar == '7' || lastChar == '8' || lastChar == '9' || lastChar == '.'){
                    if(Double.parseDouble(getSaque.getText()) < conta.getSaldo()){
                        segueSaque.setVisible(true);
                        erroSaque.setVisible(false);
                        chequeSaque.setText(Double.toString(conta.getCheque()));
                        saldoSaque.setForeground(corVerde);
                        chequeSaque.setForeground(corVerde);
                        String valorSaldo = Double.toString(conta.getSaldo() - Double.parseDouble(getSaque.getText()));
                        if(valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)){
                            saldoSaque.setText(valorSaldo);
                        }else saldoSaque.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
    
                    }else{
                        if(Double.parseDouble(getSaque.getText()) < (conta.getSaldo() + conta.getCheque())){
                            segueSaque.setVisible(true);
                            erroSaque.setVisible(false);
                            String valor = Double.toString((conta.getSaldo() + conta.getCheque()) - Double.parseDouble(getSaque.getText()));
                            saldoSaque.setText("0.00");
                            saldoSaque.setForeground(Color.red);
                            chequeSaque.setForeground(corVerde);
                            if(valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)){
                                chequeSaque.setText(valor);
                            }else chequeSaque.setText(valor.substring(0, (valor.indexOf(".") + 3)));
                            
                        }else{
                            segueSaque.setVisible(false);
                            erroSaque.setVisible(true);
                            saldoSaque.setText("0.00");
                            saldoSaque.setForeground(Color.red);
                            chequeSaque.setForeground(Color.red);
                            chequeSaque.setText("0.00");
                        }
                        
                    }
                }else getSaque.setText(getSaque.getText().substring(0, getSaque.getText().length()-1));
                System.out.println(lastChar);
            }
        });

        voltar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                tpConta.setVisible(false);
                Cadastrar.setVisible(true);
            }
        });
        voltarExtrato.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelRoll.removeAll();
                panelExtrato.setVisible(false);
                Inicio.setVisible(true);
            }
        });

        depositar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Inicio.setVisible(false);
                panelDepositar.setVisible(true);
            }
        });
        depositar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                //depositar.setBounds(50, 210, 370, 50);
                //depositar.setHorizontalAlignment(SwingConstants.CENTER);
                depositar.setText("  Depositar");
                depositar.setForeground(corAmarelo);
                depositar.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, corAmarelo));
                iconDepositar.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, corAmarelo));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                //depositar.setBounds(50, 210, 350, 50);
                //depositar.setHorizontalAlignment(SwingConstants.LEFT);
                depositar.setText(" Depositar");
                depositar.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, Color.white));
                iconDepositar.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, Color.white));
                depositar.setForeground(Color.white);
            }
        });
        extrato.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                //extrato.setHorizontalAlignment(SwingConstants.CENTER);
                extrato.setText("  Extrato");
                extrato.setForeground(corAmarelo);
                extrato.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, corAmarelo));
                iconExtrato.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, corAmarelo));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                //extrato.setHorizontalAlignment(SwingConstants.LEFT);
                extrato.setText(" Extrato");
                extrato.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, Color.white));
                iconExtrato.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, Color.white));
                extrato.setForeground(Color.white);
                
            }
        });
        transferir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                //transferir.setHorizontalAlignment(SwingConstants.CENTER);
                transferir.setText("  Transferir");
                transferir.setForeground(corAmarelo);
                transferir.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, corAmarelo));
                iconTransferir.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, corAmarelo));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                transferir.setText(" Transferir");
                //transferir.setHorizontalAlignment(SwingConstants.LEFT);
                transferir.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, Color.white));
                iconTransferir.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, Color.white));
                transferir.setForeground(Color.white);
            }
        });
        sacar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                //sacar.setHorizontalAlignment(SwingConstants.CENTER);
                sacar.setText("  Sacar");
                sacar.setForeground(corAmarelo);
                sacar.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, corAmarelo));
                iconSacar.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, corAmarelo));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                //sacar.setHorizontalAlignment(SwingConstants.LEFT);
                sacar.setText(" Sacar");
                sacar.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, Color.white));
                iconSacar.setBorder(BorderFactory.createMatteBorder(1, 2, 5, 1, Color.white));
                sacar.setForeground(Color.white);
            }
        });
        botaoRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                labelEntrar.setBounds(0, 110, 210, 40);
                labelEntrar.setFont(new Font("Arial", Font.PLAIN, 25));
                botaoRegister.setBounds(210, 110, 300, 40);
                botaoRegister.setFont(new Font("Arial", Font.PLAIN, 30));
                borderRegister.setBounds(490, 140, 30, 680);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                labelEntrar.setBounds(0, 110, 300, 40);
                labelEntrar.setFont(new Font("Arial", Font.PLAIN, 30));
                botaoRegister.setBounds(300, 110, 210, 40);
                botaoRegister.setFont(new Font("Arial", Font.PLAIN, 25));
                borderRegister.setBounds(485, 140, 35, 680);
            }
        });
        botaoLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                botaoLogin.setBounds(0, 110, 300, 40);
                botaoLogin.setFont(new Font("Arial", Font.PLAIN, 30));
                labelCadastrar.setBounds(300, 110, 210, 40);
                labelCadastrar.setFont(new Font("Arial", Font.PLAIN, 25));
                borderLogin.setBounds(0, 140, 10, 680);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {

                botaoLogin.setBounds(0, 110, 210, 40);
                botaoLogin.setFont(new Font("Arial", Font.PLAIN, 25));
                labelCadastrar.setBounds(210, 110, 300, 40);
                labelCadastrar.setFont(new Font("Arial", Font.PLAIN, 30));
                borderLogin.setBounds(0, 140, 20, 680);
            }
        });
        segueSaque.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                segueSaque.setBackground(corAmarelo);
                segueSaque.setForeground(Color.black);
                segueSaque.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAmarelo));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                segueSaque.setBackground(corAzul);
                segueSaque.setForeground(Color.white);
                segueSaque.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.white));
            }
        });
        sacar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Inicio.setVisible(false);
                panelSacar.setVisible(true);
            }
        });
        transferir.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                Inicio.setVisible(false);
                panelTransferir.setVisible(true);
            }
        });
        extrato.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                ResultSet rsqnt = con.qntOperacao(conta.getNumero());
                try {
                    while(rsqnt.next()){
                        qntExtratos = rsqnt.getInt(1);
                        System.out.println(qntExtratos);
                        panelRoll.setPreferredSize(new DimensionUIResource(480, (qntExtratos * 200)));
                        for(int i = 1; i <= qntExtratos; i++){
                            panelRoll.add(showExtrato(i));
                        }
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                Inicio.setVisible(false);
                panelExtrato.setVisible(true);
            }
        });

        botaoSacar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(conta.getSenha().equals(senhaSaque.getText())){
                    if(Double.parseDouble(getSaque.getText()) < conta.getSaldo()){
                        Double valorSaldo = conta.getSaldo() - Double.parseDouble(getSaque.getText());
                        con.atualizaSaldo(valorSaldo, cliente.getId());
                        con.InsereExtrato(conta.getNumero(), "Saque", Double.parseDouble(getSaque.getText()), conta.getCheque(), conta.getSaldo(), valorSaldo, " ");
                        conta.setSaldo(valorSaldo);
                    }else{
                        Double valorCheque = (conta.getSaldo() + conta.getCheque()) - Double.parseDouble(getSaque.getText());
                        con.atualizaSaldo(0.0, cliente.getId());
                        con.atualizaCheque(valorCheque, cliente.getId());
                        con.InsereExtrato(conta.getNumero(), "Saque", Double.parseDouble(getSaque.getText()), valorCheque, conta.getSaldo(), 0.0, "Utilização do cheque especial");
                        conta.setSaldo(0.0);
                        conta.setCheque(valorCheque);
                    } 
                    Atualiza_valores(); 
                    Inicio.setVisible(true);
                    panelSenhaSaque.setVisible(false);
                    getSaque.setText("");
                    senhaSaque.setText("");
                }
            }
        });

        segueDeposito.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelDepositar.setVisible(false);
                panelVerificaSenha.setVisible(true);
                VerificaSenha(Inicio, panelVerificaSenha, validar, verifica, 1);
            }
        });

        segueSaque.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelSacar.setVisible(false);
                panelSenhaSaque.setVisible(true);
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
                                    conta = new Conta(rsConta.getInt("numero"), rsConta.getString("pixnumero"), rsConta.getString("pixemail"), rsConta.getString("pixcpf"), rsConta.getInt("agencia"), rsConta.getString("operacao"), rsConta.getDouble("saldo"), rsConta.getDouble("cheque"), rsConta.getInt("cliente"), rsConta.getString("senha"));
                                }
                                Atualiza_valores();
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
                        Double cheque;
                        if(tituloCorrente.isSelected()){
                            operacao = "001";
                            cheque = 300.0;
                        }else{
                            operacao = "013";
                            cheque = 0.0;
                        } 

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
                            con.criaConta(cliente.getId(), cliente.getCPF(), cliente.getEmail(), cliente.getCelular(), cheque, operacao, senhaConta.getText());
                            ResultSet rsConta = con.getConta(cliente.getId());
                            try {
                                while(rsConta.next()){
                                    conta = new Conta(rsConta.getInt("numero"), rsConta.getString("pixnumero"), rsConta.getString("pixemail"), rsConta.getString("pixcpf"), rsConta.getInt("agencia"), rsConta.getString("operacao"), rsConta.getDouble("saldo"), rsConta.getDouble("cheque"), rsConta.getInt("cliente"), rsConta.getString("senha"));
                                    Atualiza_valores();
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
        panelSenhaSaque.setSize(janela.getSize());
        panelSenhaTranferencia.setSize(janela.getSize());
        panelVerificaSenha.setSize(janela.getSize());
        panelDepositar.setSize(janela.getSize());
        panelSacar.setSize(janela.getSize());
        panelTransferir.setSize(janela.getSize());
        panelExtrato.setSize(janela.getSize());
        scroll.setSize(new DimensionUIResource(505, 630));

        Container Pane = janela.getContentPane();
        Pane.add(Entrar);
        Pane.add(Cadastrar);
        Pane.add(tpConta);
        Pane.add(Inicio);
        Pane.add(panelSenhaSaque);
        Pane.add(panelSenhaTranferencia);
        Pane.add(panelVerificaSenha);
        Pane.add(panelDepositar);
        Pane.add(panelSacar);
        Pane.add(panelTransferir);
        Pane.add(panelExtrato);
    }

    public static void VerificaSenha(JPanel Inicio, JPanel panelSenha, JButton validar, JFormattedTextField verifica, int funcao){
        validar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(conta.getSenha().equals(verifica.getText())){
                    switch (funcao) {
                        case 1:
                        deposita();
                        Inicio.setVisible(true);
                        panelSenha.setVisible(false);
                        verifica.setText("");
                        break;
                    
                        default:
                            break;
                    }
                    
                }
            }
        });

    }

    public static void deposita(){
        if(conta.getOperacao().equals("001")){
            double valor = (Double.parseDouble(getDeposito.getText()) + conta.getCheque() + conta.getSaldo());
            if(valor >= 300.0){
                con.atualizaCheque(300, cliente.getId());
                con.atualizaSaldo((valor - 300), cliente.getId());
                con.InsereExtrato(conta.getNumero(), "Depósito",Double.parseDouble(getDeposito.getText()), 300.0, conta.getSaldo(), (valor-300), " ");
                conta.setSaldo((valor - 300));
                conta.setCheque(300);
            }else{
                con.atualizaCheque(valor, cliente.getId());
                con.InsereExtrato(conta.getNumero(), "Depósito",Double.parseDouble(getDeposito.getText()), valor, conta.getSaldo(), conta.getSaldo(), "Cobrança de crédito especial");
                conta.setCheque(valor);
            }
        }else{
            double valor = Double.parseDouble(getDeposito.getText()) + conta.getSaldo();
            con.atualizaSaldo(valor, cliente.getId());
            con.InsereExtrato(conta.getNumero(), "Depósito",Double.parseDouble(getDeposito.getText()), conta.getCheque(), conta.getSaldo(), valor, " ");
            conta.setSaldo(valor);
        }
        Atualiza_valores();
        getDeposito.setText("");
    }

    public static JPanel showExtrato(int i){

        ResultSet rs = con.getExtrato(conta.getNumero(), i);
        try {
            while(rs.next()){
                extrato = new Extrato(rs.getString("datahora"), rs.getString("transacao"), rs.getDouble("valor"), rs.getDouble("saldocheque"), rs.getDouble("saldoanterior"), rs.getDouble("saldoAtualizado"), rs.getString("detalhes"));
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        String saldoA;
        String saldoN;
        String cheque;

        if(Double.toString(extrato.getSaldoAnterior()).indexOf(".") == -1 || Double.toString(extrato.getSaldoAnterior()).length() < (Double.toString(extrato.getSaldoAnterior()).indexOf(".") + 3)){
            saldoA = Double.toString(extrato.getSaldoAnterior());
        }else{
            saldoA = Double.toString(extrato.getSaldoAnterior()).substring(0, (Double.toString(extrato.getSaldoAnterior()).indexOf(".") + 3));
        }

        if(Double.toString(extrato.getSaldoAtualizado()).indexOf(".") == -1 || Double.toString(extrato.getSaldoAtualizado()).length() < (Double.toString(extrato.getSaldoAtualizado()).indexOf(".") + 3)){
            saldoN = Double.toString(extrato.getSaldoAtualizado());
        }else{
            saldoN = Double.toString(extrato.getSaldoAtualizado()).substring(0, (Double.toString(extrato.getSaldoAtualizado()).indexOf(".") + 3));
        }

        if(Double.toString(extrato.getCheque()).indexOf(".") == -1 || Double.toString(extrato.getCheque()).length() < (Double.toString(extrato.getCheque()).indexOf(".") + 3)){
            cheque = Double.toString(extrato.getCheque());
        }else{
            cheque = Double.toString(extrato.getCheque()).substring(0, (Double.toString(extrato.getCheque()).indexOf(".") + 3));
        }


        Color corAmarelo = new Color(254,170,58);
        JLabel data = new JLabel(extrato.getData());
        data.setFont(new Font("Arial", Font.PLAIN, 20));
        data.setBounds(10, 20, 150, 20);
        data.setForeground(Color.black);

        JLabel tipoOperacao = new JLabel(extrato.getTransacao());
        tipoOperacao.setBounds(200, 10, 150, 30);
        tipoOperacao.setFont(new Font("Arial", Font.PLAIN, 30));
        tipoOperacao.setForeground(Color.black);

        JLabel valor = new JLabel("R$ "+ extrato.getValor());
        valor.setFont(new Font("Arial", Font.PLAIN, 25));
        valor.setBounds(380, 50, 150, 30);
        valor.setForeground(Color.black);

        JLabel labelSaldoAnterior = new JLabel("Saldo Anterior:");
        labelSaldoAnterior.setFont(new Font("Arial", Font.PLAIN, 20));
        labelSaldoAnterior.setBounds(10, 60, 200, 20);
        labelSaldoAnterior.setForeground(Color.black);

        JLabel saldoAnterior = new JLabel("R$ " + saldoA);
        saldoAnterior.setFont(new Font("Arial", Font.PLAIN, 20));
        saldoAnterior.setBounds(170, 60, 200, 20);
        saldoAnterior.setForeground(Color.black);

        JLabel labelNovoSaldo = new JLabel("Novo Saldo:");
        labelNovoSaldo.setFont(new Font("Arial", Font.PLAIN, 20));
        labelNovoSaldo.setBounds(10, 90, 200, 20);
        labelNovoSaldo.setForeground(Color.black);

        JLabel novoSaldo = new JLabel("R$ " + saldoN);
        novoSaldo.setFont(new Font("Arial", Font.PLAIN, 20));
        novoSaldo.setBounds(170, 90, 200, 20);
        novoSaldo.setForeground(Color.black);

        JLabel labelCheque = new JLabel("Cheque especial:");
        labelCheque.setFont(new Font("Arial", Font.PLAIN, 20));
        labelCheque.setBounds(10, 120, 200, 20);
        labelCheque.setForeground(Color.black);

        JLabel Saldocheque = new JLabel("R$ " + cheque);
        Saldocheque.setFont(new Font("Arial", Font.PLAIN, 20));
        Saldocheque.setBounds(170, 120, 200, 20);
        Saldocheque.setForeground(Color.black);

        JLabel detalhes = new JLabel(extrato.getDetalhes());
        detalhes.setFont(new Font("Arial", Font.PLAIN, 20));
        detalhes.setBounds(10, 150, 300, 20);
        detalhes.setForeground(Color.black);

        JPanel operacao = new JPanel();
        operacao.setLayout(null);
        operacao.setSize(new DimensionUIResource(520, 400));
        operacao.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        operacao.add(data);
        operacao.add(tipoOperacao);
        operacao.add(valor);
        operacao.add(labelSaldoAnterior);
        operacao.add(labelNovoSaldo);
        operacao.add(saldoAnterior);
        operacao.add(novoSaldo);
        operacao.add(detalhes);
        operacao.add(labelCheque);
        operacao.add(Saldocheque);
        operacao.setBackground(corAmarelo);
        return operacao;
    }

    public static void Atualiza_valores(){
        if(Double.toString(conta.getSaldo()).indexOf(".") == -1 || Double.toString(conta.getSaldo()).length() < (Double.toString(conta.getSaldo()).indexOf(".") + 3)){
            saldoInicial.setText(Double.toString(conta.getSaldo()));
            saldoSaque.setText(Double.toString(conta.getSaldo()));
        }else{
            saldoInicial.setText(Double.toString(conta.getSaldo()).substring(0, (Double.toString(conta.getSaldo()).indexOf(".") + 3)));
            saldoSaque.setText(Double.toString(conta.getSaldo()).substring(0, (Double.toString(conta.getSaldo()).indexOf(".") + 3)));
        } 
        if(Double.toString(conta.getCheque()).indexOf(".") == -1 || Double.toString(conta.getCheque()).length() < (Double.toString(conta.getCheque()).indexOf(".") + 3)){
            chequeInicial.setText(Double.toString(conta.getCheque()));
            chequeSaque.setText(Double.toString(conta.getCheque()));
        }else{
            chequeInicial.setText(Double.toString(conta.getCheque()).substring(0, (Double.toString(conta.getCheque()).indexOf(".") + 3)));
            chequeSaque.setText(Double.toString(conta.getCheque()).substring(0, (Double.toString(conta.getCheque()).indexOf(".") + 3)));
        } 
    }
}
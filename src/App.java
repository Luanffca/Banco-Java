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


public class App {
    static Color corAzul = new Color(34,92,150);
    static Color corCinza = new Color(67,87,114);
    static Color corAmarelo = new Color(254,170,58);
    static Color corVermelho = new Color(234,100,30);
    static Cliente cliente = null;
    static Conta conta = null;
    static Extrato extrato = null;
    static int qntExtratos;
    static JLabel saldoInicial;
    static JLabel chequeInicial;
    static JLabel saldoSaque;
    static JLabel chequeSaque;
    static JLabel saldoTransferir;
    static JLabel chequeTransferir;
    static JTextField getDeposito;
    static JTextField getSaque;
    static JTextField getTransferencia;
    static JTextField getPix;
    static JTextField getAgencia;
    static JTextField getOperacao;
    static JTextField getNumeroConta;
    static JFormattedTextField getPixCPF;
    static JTextField getPixEmail;
    static JFormattedTextField getPixNumero;
    static JTextField getEmprestimo;
    static JLabel campoInicial;

    static Conexao con = new Conexao();
	public static void main(String[] args) {
		
        //Color corVerde = new Color(34,92,100);
        //Color corVerde = new Color(10,150,130);
        //Color corAzul = new Color(31,85,181);
        //Color corAmarelo = new Color(239,209,50);
        //Color corAzul = new Color(30,144,200);

        
        Texto textos = new Texto();
        Botao botoes = new Botao();
        campoTxt campos = new campoTxt();
        
        JPanel Entrar = new JPanel();
        JPanel Cadastrar = new JPanel();
        JPanel tpConta = new JPanel();
        JPanel Inicio = new JPanel();
        JPanel panelVerificaSenha = new JPanel();
        JPanel panelDepositar = new JPanel();
        JPanel panelSacar = new JPanel();
        JPanel panelTransferir = new JPanel();
        JPanel panelPix = new JPanel();
        JPanel panelTransferencia = new JPanel();
        JPanel panelExtrato = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JPanel panelRoll = new JPanel();

        // TELA ENTRAR
        JButton registrar = botoes.botao("Cadastrar", corAmarelo, Color.black, 100, 610, 310, 40, 30);
        JButton botaoRegister = botoes.botao("Cadastrar", corAmarelo, Color.black, 300, 110, 210, 40, 25);
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

        Entrar.setBackground(Color.white);
        Entrar.setLayout(null);
        Entrar.setVisible(true);
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
        Cadastrar.add(textos.textos("C", 15, 10, 300, 60, 65, corAmarelo));
        Cadastrar.add(textos.textos("oin", 60, 10, 300, 50, 40, corAmarelo));
        Cadastrar.add(textos.textos("B", 50, 40, 300, 70, 60, corAmarelo));
        Cadastrar.add(textos.textos("ag", 85, 30, 300, 70, 40, corAmarelo));
        Cadastrar.add(textos.textos("Seu dinheiro", 150, 20, 300, 50, 30, Color.white));
        Cadastrar.add(textos.textos("sempre com você!!", 170, 45, 300, 50, 30, Color.white));
        Cadastrar.add(textos.textos(0, 0, 520, 110, corCinza, 0, 5, 0, 0, corAmarelo));
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
        JTextArea descrCorrente = new JTextArea("Movimente seu dinheiro da forma como desejar, com direito a um cheque especial para utilizar quando quiser");
        descrCorrente.setBounds(20, 100, 460, 100);
        descrCorrente.setFont(new Font("Arial", Font.PLAIN, 20));
        descrCorrente.setForeground(Color.white);
        descrCorrente.setBackground(corAzul);
        descrCorrente.setEditable(false);
        descrCorrente.setLineWrap(true);
        descrCorrente.setBorder(BorderFactory.createCompoundBorder(descrCorrente.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JRadioButton tituloPoupanca = new JRadioButton("Conta Poupança");
        tituloPoupanca.setBounds(20, 220, 460, 30);
        tituloPoupanca.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloPoupanca.setForeground(corCinza);
        tituloPoupanca.setBackground(Color.white);
        JTextArea descrPoupanca = new JTextArea("Ideal para quem deseja guardar dinheiro, com rendimento de 6,17% ao ano");
        descrPoupanca.setBounds(20, 250, 460, 100);
        descrPoupanca.setFont(new Font("Arial", Font.PLAIN, 20));
        descrPoupanca.setForeground(Color.white);
        descrPoupanca.setBackground(corAzul);
        descrPoupanca.setEditable(false);
        descrPoupanca.setLineWrap(true);
        descrPoupanca.setBorder(BorderFactory.createCompoundBorder(descrPoupanca.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(tituloPoupanca);
        grupo.add(tituloCorrente);

        JButton finalizar = botoes.botao("Finalizar", corAzul, Color.white, 100, 550, 300, 40, 30, 1, 3, 3, 1, Color.white);

        JFormattedTextField senhaConta = new JFormattedTextField(mascaraSenha);
        senhaConta.setBounds(200, 410, 100, 50);
        senhaConta.setFont(new Font("Arial", Font.PLAIN, 30));
        senhaConta.setHorizontalAlignment(SwingConstants.CENTER);
        senhaConta.setForeground(Color.white);
        senhaConta.setBackground(corCinza);
        senhaConta.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        
        tpConta.setBackground(corCinza);
        tpConta.setLayout(null);
        tpConta.setVisible(false);
        tpConta.add(textos.textos(" Selecione o tipo de conta", 50, 0, 470, 50, 30, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        tpConta.add(textos.textosAlinhados("Defina uma senha de 4 digitos para a conta", 0, 370, 520, 50, 20, Color.white, corCinza));
        tpConta.add(tituloCorrente);
        tpConta.add(descrCorrente);
        tpConta.add(tituloPoupanca);
        tpConta.add(descrPoupanca);
        tpConta.add(botoes.botaoVoltar(tpConta, Cadastrar));
        tpConta.add(finalizar);
        tpConta.add(senhaConta);

        // TELA INICIAL
        JButton sair = botoes.botao(" Sair", corAmarelo, Color.black, 410, 0, 110, 50, 25, 0, 3, 0, 0, Color.white);
        JButton depositar = botoes.botaoInicial(" Depositar",0, 210, Inicio, panelDepositar);
        JButton sacar = botoes.botaoInicial(" Sacar",0, 280, Inicio, panelSacar);
        JButton extrato = botoes.botaoInicial(" Extrato",0, 350, Inicio, panelExtrato);
        JButton transferir = botoes.botaoInicial(" Transferir",0, 420, Inicio, panelTransferir);
        JButton pix = botoes.botaoInicial(" Empréstimo",0, 490, Inicio, panelTransferir);
        JButton minhaConta = botoes.botaoInicial(" Conta",0, 560, Inicio, panelTransferir);
        /*
        JButton iconDepositar = botoes.botao(corAmarelo, 0, 210, 50, 50);
        JButton iconSacar = botoes.botao(corAmarelo, 0, 280, 50, 50);
        JButton iconExtrato = botoes.botao(corAmarelo, 0, 350, 50, 50);
        JButton iconTransferir = botoes.botao(corAmarelo, 0, 420, 50, 50);
        JButton iconPix = botoes.botao(corAmarelo, 0, 490, 50, 50);
        JButton iconMinhaConta = botoes.botao(corAmarelo, 0, 560, 50, 50);
        */
        JLabel bemVindo = textos.textos(" Bem Vindo Francisco Alisson", 0, 0, 410, 50, 25, Color.white, corAzul, 0, 5, 0, 0, corAmarelo);
        saldoInicial = textos.textos(" R$ 3.50", 320, 70, 210, 50, 25, Color.black);
        chequeInicial = textos.textos(" R$ 300", 400, 120, 110, 40, 20, Color.white);
        JLabel labelCheque = textos.textos(" Cheque especial:", 0, 120, 400, 40, 20, Color.white);
        campoInicial = textos.textos(0, 70, 510, 50, corAmarelo);
        Inicio.setBackground(corCinza);
        Inicio.setLayout(null);
        Inicio.setVisible(false);
        Inicio.add(sair);
        Inicio.add(bemVindo);
        Inicio.add(textos.textos(0, 50, 520, 20, corAzul, 0, 0, 0, 0, corAmarelo));
        Inicio.add(textos.textos(480, 160, 50, 700, Color.white, 0, 0, 0, 0, Color.white));
        Inicio.add(textos.textos(" Saldo:", 0, 70, 300, 50, 25, Color.black));
        Inicio.add(labelCheque);
        Inicio.add(chequeInicial);
        Inicio.add(textos.textos(0, 120, 520, 40, corAzul, 0, 0, 3, 0, Color.white));
        Inicio.add(saldoInicial);
        Inicio.add(campoInicial);
        Inicio.add(depositar);
        Inicio.add(sacar);
        Inicio.add(extrato);
        Inicio.add(transferir);
        Inicio.add(pix);
        Inicio.add(minhaConta);
        /*
        Inicio.add(iconDepositar);
        Inicio.add(iconSacar);
        Inicio.add(iconExtrato);
        Inicio.add(iconTransferir);
        Inicio.add(iconPix);
        Inicio.add(iconMinhaConta);*/

        //TELA VERIFICAR SENHA
        JButton validar = botoes.botao("Validar", corAmarelo, corCinza, 100, 500, 300, 40, 30);
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

        //TELA VERIFICAR SENHA TRANSFERENCIA

        //TELA DEPOSITAR
        JButton segueDeposito = botoes.botao("Prosseguir", corAzul, Color.white, 100, 500, 300, 40, 30, 1, 3, 3, 1, Color.white);
        getDeposito = new JTextField();
        getDeposito.setBounds(20, 150, 460, 50);
        getDeposito.setFont(new Font("Arial", Font.PLAIN, 30));
        getDeposito.setHorizontalAlignment(SwingConstants.CENTER);
        getDeposito.setForeground(Color.white);
        getDeposito.setBackground(corCinza);
        getDeposito.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        panelDepositar.setBackground(corCinza);
        panelDepositar.setLayout(null);
        panelDepositar.setVisible(false);
        panelDepositar.add(textos.textosAlinhados(" Digite o valor que deseja depositar ", 0, 100, 520, 50, 25, Color.white));
        panelDepositar.add(textos.textos(" Depositar ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        panelDepositar.add(getDeposito);
        panelDepositar.add(botoes.botaoVoltar(panelDepositar, Inicio));
        panelDepositar.add(segueDeposito);

        //TELA SACAR
        JButton segueSaque = botoes.botao("Prosseguir", corAzul, Color.white, 100, 500, 300, 40, 30, 1, 3, 3, 1, Color.white);
        segueSaque.setVisible(false);
        JLabel erroSaque = textos.textosAlinhados("Prosseguir", 100, 500, 300, 40, 30, corAzul, Color.white, 1, 3, 3, 1, Color.white);
        getSaque = new JTextField();
        getSaque.setBounds(20, 150, 460, 50);
        getSaque.setFont(new Font("Arial", Font.PLAIN, 30));
        getSaque.setHorizontalAlignment(SwingConstants.CENTER);
        getSaque.setForeground(Color.white);
        getSaque.setBackground(corCinza);
        getSaque.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        saldoSaque = textos.textos(" R$ 3.50", 330, 210, 150, 50, 25, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        chequeSaque = textos.textos(" R$ 300", 340, 260, 140, 40, 20, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        JLabel labelChequeSaque = textos.textos(" Cheque especial:", 20, 260, 380, 40, 20, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        panelSacar.setBackground(corCinza);
        panelSacar.setLayout(null);
        panelSacar.setVisible(false);
        panelSacar.add(botoes.botaoVoltar(panelSacar, Inicio));
        panelSacar.add(getSaque);
        panelSacar.add(saldoSaque);
        panelSacar.add(chequeSaque);
        panelSacar.add(segueSaque);
        panelSacar.add(erroSaque);
        panelSacar.add(textos.textos(" Saldo:", 20, 210, 380, 50, 25, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo));
        panelSacar.add(labelChequeSaque);
        panelSacar.add(textos.textosAlinhados(" Digite o valor que deseja Sacar ", 0, 100, 520, 50, 25, Color.white));
        panelSacar.add(textos.textos(" Sacar ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        //TELA TRANSFERIR
        JComboBox<String> tipoChave = new JComboBox<String>();
        tipoChave.addItem("CPF");
        tipoChave.addItem("Email");
        tipoChave.addItem("Numero de celular");
        tipoChave.setBounds(280, 80, 170, 30);
        tipoChave.setFont(new Font("Arial", Font.PLAIN, 20));
        tipoChave.setBackground(Color.white);
        tipoChave.setForeground(corAzul);
        tipoChave.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));

        getPixCPF = new JFormattedTextField(mascaraCPF);
        getPixCPF.setBounds(20, 180, 200, 30);
        getPixCPF.setFont(new Font("Arial", Font.PLAIN, 20));
        getPixCPF.setForeground(corAzul);
        getPixCPF.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));
        getPixCPF.setVisible(true);

        getPixNumero = new JFormattedTextField(mascaraNumero);
        getPixNumero.setBounds(20, 180, 200, 30);
        getPixNumero.setFont(new Font("Arial", Font.PLAIN, 20));
        getPixNumero.setForeground(corAzul);
        getPixNumero.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));
        getPixNumero.setVisible(false);

        getPixEmail = new JTextField();
        getPixEmail.setBounds(20, 180, 200, 30);
        getPixEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        getPixEmail.setForeground(corAzul);
        getPixEmail.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));
        getPixEmail.setVisible(false);

        tipoChave.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(tipoChave.getSelectedIndex());
                switch (tipoChave.getSelectedIndex()) {
                    case 0:
                        getPixCPF.setVisible(true);
                        getPixEmail.setVisible(false);
                        getPixNumero.setVisible(false);
                        break;
                    case 1:
                        getPixEmail.setVisible(true);
                        getPixNumero.setVisible(false);
                        getPixCPF.setVisible(false);
                        break;
                    case 2:
                        getPixNumero.setVisible(true);
                        getPixCPF.setVisible(false);
                        getPixEmail.setVisible(false);
                    default:
                        break;
                }
            }
        });
        
        getPix = new JTextField();
        getPix.setBounds(20, 270, 200, 30);
        getPix.setFont(new Font("Arial", Font.PLAIN, 20));
        getPix.setForeground(corAzul);
        getPix.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));

        JTextArea getDescricao = new JTextArea();
        JScrollPane desc = new JScrollPane(getDescricao);
        desc.setBounds(260, 210, 200, 70);
        getDescricao.setFont(new Font("Arial", Font.PLAIN, 20));
        getDescricao.setForeground(corAzul);
        desc.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));
        getDescricao.setLineWrap(true);
        getDescricao.setEditable(true);
        
        desc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton EnviarPix = botoes.botao("Enviar pix", corCinza, Color.white, 100, 350, 300, 40, 30, 1, 3, 3, 1, Color.white);
        EnviarPix.setVisible(false);
        JLabel erroPix = textos.textosAlinhados("Enviar pix", 100, 350, 300, 40, 30, corCinza, Color.white, 1, 3, 3, 1, corCinza);
        JLabel labelPix = textos.textosAlinhados("Pix", 0, 0, 300, 40, 30, corAzul, Color.white);
        JButton botaoTransferencia = botoes.botao("Transferir", corAmarelo, Color.black, 300, 0, 210, 40, 25);
        JLabel bordaPix = textos.textos(490, 0, 20, 500, corAmarelo);
        panelPix.setLayout(null);
        panelPix.setBackground(Color.white);
        panelPix.setBounds(0, 180, 520, 500);
        panelPix.add(labelPix);
        panelPix.add(botaoTransferencia);
        panelPix.add(tipoChave);
        panelPix.add(getPixCPF);
        panelPix.add(getPixEmail);
        panelPix.add(getPixNumero);
        panelPix.add(getPix);
        panelPix.add(desc);
        panelPix.add(EnviarPix);
        panelPix.add(erroPix);
        panelPix.add(textos.textos(" Selecione o tipo de chave", 0, 70, 460, 50, 20, Color.white, corAzul));
        panelPix.add(textos.textos("Chave", 20, 130, 100, 50, 20, corAzul));
        panelPix.add(textos.textos("Descrição (Opcional)", 260, 170, 200, 50, 20, corAzul));
        panelPix.add(textos.textos("Valor", 20, 220, 100, 50, 20, corAzul));
        panelPix.add(bordaPix);

        JButton botaoPix = botoes.botao("Pix", corAmarelo, Color.black, 0, 0, 210, 40, 25);
        JLabel labelTransferir = textos.textosAlinhados("Transferir", 210, 0, 300, 40, 30, corAzul);
        
        JTextField getAgencia = new JTextField();
        getAgencia.setBounds(40, 120, 200, 30);
        getAgencia.setFont(new Font("Arial", Font.PLAIN, 20));
        getAgencia.setForeground(corAzul);
        getAgencia.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));

        JTextField getNConta = new JTextField();
        getNConta.setBounds(40, 200, 200, 30);
        getNConta.setFont(new Font("Arial", Font.PLAIN, 20));
        getNConta.setForeground(corAzul);
        getNConta.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));

        JComboBox<String> tipoConta = new JComboBox<String>();
        tipoConta.setBounds(300, 200, 150, 30);
        tipoConta.setFont(new Font("Arial", Font.PLAIN, 20));
        tipoConta.setForeground(corAzul);
        tipoConta.setBackground(Color.white);
        tipoConta.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, corAzul));
        tipoConta.addItem("Corrente");
        tipoConta.addItem("Poupança");

        getTransferencia = new JTextField();
        getTransferencia.setBounds(40, 280, 200, 30);
        getTransferencia.setFont(new Font("Arial", Font.PLAIN, 20));
        getTransferencia.setForeground(corAzul);
        getTransferencia.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));

        JButton BotaoTransferir = botoes.botao("Transferir", corCinza, Color.white, 100, 350, 300, 40, 30, 1, 3, 3, 1, Color.white);
        JLabel erroTransferir = textos.textosAlinhados("Transferir", 100, 350, 300, 40, 30, corCinza, Color.white, 1, 3, 3, 1, corCinza);
        BotaoTransferir.setVisible(false);
        JLabel bordaTransferencia = textos.textos(0, 0, 10, 500, corAmarelo);

        panelTransferencia.setLayout(null);
        panelTransferencia.setBackground(Color.white);
        panelTransferencia.setBounds(0, 180, 520, 500);
        panelTransferencia.setVisible(false);
        panelTransferencia.add(botaoPix);
        panelTransferencia.add(getNConta);
        panelTransferencia.add(getTransferencia);
        panelTransferencia.add(BotaoTransferir);
        panelTransferencia.add(tipoConta);
        panelTransferencia.add(erroTransferir);
        panelTransferencia.add(bordaTransferencia);
        panelTransferencia.add(textos.textos("Agencia", 40, 70, 100, 50, 20, corAzul));
        panelTransferencia.add(textos.textos("Conta", 40, 150, 100, 50, 20, corAzul));
        panelTransferencia.add(textos.textos("Valor", 40, 230, 100, 50, 20, corAzul));
        panelTransferencia.add(textos.textos("Tipo", 300, 150, 100, 50, 20, corAzul));
        panelTransferencia.add(labelTransferir);
        panelTransferencia.add(getAgencia);
        
        saldoTransferir = textos.textos(" R$ 3.50", 330, 70, 150, 50, 25, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        chequeTransferir = textos.textos(" R$ 300", 340, 120, 140, 40, 20, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        panelTransferir.setBackground(corCinza);
        panelTransferir.setLayout(null);
        panelTransferir.setVisible(false);
        panelTransferir.add(saldoTransferir);
        panelTransferir.add(chequeTransferir);
        panelTransferir.add(panelPix);
        panelTransferir.add(panelTransferencia);
        panelTransferir.add(textos.textos(" Saldo:", 0, 70, 380, 50, 25, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo));
        panelTransferir.add(textos.textos(" Cheque especial:", 0, 120, 380, 40, 20, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo));
        
        panelTransferir.add(botoes.botaoVoltar(panelTransferir, Inicio));
        panelTransferir.add(textos.textos(" Transferir ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        //TELA EXTRATO
        JButton voltarExtrato = botoes.botaoVoltar(panelExtrato, Inicio);
        panelRoll.setLayout(new GridLayout(0, 1, 10, 10));
        panelRoll.setVisible(true);
        panelRoll.setBackground(corCinza);
        panelRoll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        
        scroll.setBackground(corCinza);
        scroll.setViewportView(panelRoll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVisible(true);
        scroll.setLocation(0, 50);
        scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));

        panelExtrato.setBackground(corCinza);
        panelExtrato.setLayout(null);
        panelExtrato.setVisible(false);
        panelExtrato.setBackground(corCinza);
        panelExtrato.add(textos.textos(" Extrato ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        panelExtrato.add(scroll);
        panelExtrato.add(voltarExtrato);


        botaoPix.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                panelTransferencia.setVisible(false);
                panelPix.setVisible(true);
                getTransferencia.setText("");
                Atualiza_valores();
                erroPix.setVisible(true);
                EnviarPix.setVisible(false);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                botaoPix.setBounds(0, 0, 300, 40);
                botaoPix.setFont(new Font("Arial", Font.PLAIN, 30));
                labelTransferir.setBounds(300, 0, 210, 40);
                labelTransferir.setFont(new Font("Arial", Font.PLAIN, 25));
                bordaTransferencia.setBounds(0, 0, 5, 500);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {

                botaoPix.setBounds(0, 0, 210, 40);
                botaoPix.setFont(new Font("Arial", Font.PLAIN, 25));
                labelTransferir.setBounds(210, 0, 300, 40);
                labelTransferir.setFont(new Font("Arial", Font.PLAIN, 30));
                bordaTransferencia.setBounds(0, 0, 10, 500);
            }
        });

        botaoTransferencia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                panelPix.setVisible(false);
                panelTransferencia.setVisible(true);
                getPix.setText("");
                Atualiza_valores();
                erroTransferir.setVisible(true);
                botaoTransferencia.setVisible(false);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {

                labelPix.setBounds(0, 0, 210, 40);
                labelPix.setFont(new Font("Arial", Font.PLAIN, 25));
                botaoTransferencia.setBounds(210, 0, 300, 40);
                botaoTransferencia.setFont(new Font("Arial", Font.PLAIN, 30));
                bordaPix.setBounds(495, 0, 20, 500);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                labelPix.setBounds(0, 0, 300, 40);
                labelPix.setFont(new Font("Arial", Font.PLAIN, 30));
                botaoTransferencia.setBounds(300, 0, 210, 40);
                botaoTransferencia.setFont(new Font("Arial", Font.PLAIN, 25));
                bordaPix.setBounds(490, 0, 20, 500);
            }
        });

        tituloPoupanca.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                tituloPoupanca.setBackground(corAmarelo);
                tituloPoupanca.setForeground(Color.black);
                tituloCorrente.setBackground(Color.white);
                tituloCorrente.setForeground(corCinza);
                if(senhaConta.getText().equals("    ")){
                    finalizar.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.white));
                }else{
                    finalizar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
                    finalizar.setBackground(corAmarelo);
                    finalizar.setForeground(Color.black);
                }
                
            }
        });
        tituloCorrente.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                tituloPoupanca.setBackground(Color.white);
                tituloPoupanca.setForeground(corCinza);
                tituloCorrente.setBackground(corAmarelo);
                tituloCorrente.setForeground(Color.black);
                if(senhaConta.getText().equals("    ")){
                    finalizar.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.white));
                }else{
                    finalizar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
                    finalizar.setBackground(corAmarelo);
                    finalizar.setForeground(Color.black);
                }
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
        getPix.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(KeyEvent e){
                if(getPix.getText().isEmpty()){
                    Atualiza_valores();
                    EnviarPix.setVisible(false);
                    erroPix.setVisible(true);
                }
                char lastChar = getPix.getText().charAt(getPix.getText().length() - 1);
                if(lastChar == '0' || lastChar == '1' || lastChar == '2' || lastChar == '3' || lastChar == '4' || lastChar == '5' || lastChar == '6' || lastChar == '7' || lastChar == '8' || lastChar == '9' || lastChar == '.'){
                    subtraiValores(Double.parseDouble(getPix.getText()), saldoTransferir, chequeTransferir);
                    if(conta.getOperacao().equals("001")){
                        if(Double.parseDouble(chequeTransferir.getText()) >= 0){
                            EnviarPix.setVisible(true);
                            erroPix.setVisible(false);
                        }else{
                            EnviarPix.setVisible(false);
                            erroPix.setVisible(true);
                        }
                    }else{
                        if(Double.parseDouble(saldoTransferir.getText()) >= 0){
                            EnviarPix.setVisible(true);
                            erroPix.setVisible(false);
                        }else{
                            EnviarPix.setVisible(false);
                            erroPix.setVisible(true);
                        }
                    }
                }else getPix.setText(getPix.getText().substring(0, getPix.getText().length()-1));
                System.out.println(lastChar);

                if(Double.parseDouble(saldoTransferir.getText()) <= 0){
                    saldoTransferir.setForeground(Color.red);
                }else saldoTransferir.setForeground(corCinza);
                if(Double.parseDouble(chequeTransferir.getText()) < 300){
                    chequeTransferir.setForeground(Color.red);
                }else chequeTransferir.setForeground(corCinza);
            }
        });
        getTransferencia.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(KeyEvent e){
                if(getTransferencia.getText().isEmpty()){
                    Atualiza_valores();
                    BotaoTransferir.setVisible(false);
                    erroTransferir.setVisible(true);
                }
                char lastChar = getTransferencia.getText().charAt(getTransferencia.getText().length() - 1);
                if(lastChar == '0' || lastChar == '1' || lastChar == '2' || lastChar == '3' || lastChar == '4' || lastChar == '5' || lastChar == '6' || lastChar == '7' || lastChar == '8' || lastChar == '9' || lastChar == '.'){
                    subtraiValores(Double.parseDouble(getTransferencia.getText()), saldoTransferir, chequeTransferir);
                    if(conta.getOperacao().equals("001")){
                        if(Double.parseDouble(chequeTransferir.getText()) >= 0){
                            BotaoTransferir.setVisible(true);
                            erroTransferir.setVisible(false);
                        }else{
                            BotaoTransferir.setVisible(false);
                            erroTransferir.setVisible(true);
                        }
                    }else{
                        if(Double.parseDouble(saldoTransferir.getText()) >= 0){
                            BotaoTransferir.setVisible(true);
                            erroTransferir.setVisible(false);
                        }else{
                            BotaoTransferir.setVisible(false);
                            erroTransferir.setVisible(true);
                        }
                    }
                }else getTransferencia.setText(getTransferencia.getText().substring(0, getTransferencia.getText().length()-1));
                System.out.println(lastChar);

                if(Double.parseDouble(saldoTransferir.getText()) <= 0){
                    saldoTransferir.setForeground(Color.red);
                }else saldoTransferir.setForeground(corCinza);
                if(Double.parseDouble(chequeTransferir.getText()) < 300){
                    chequeTransferir.setForeground(Color.red);
                }else chequeTransferir.setForeground(corCinza);
            }
        });
        getSaque.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyReleased(KeyEvent e){
                if(getSaque.getText().isEmpty()){
                    Atualiza_valores();
                    segueSaque.setVisible(false);
                    erroSaque.setVisible(true);
                }
                char lastChar = getSaque.getText().charAt(getSaque.getText().length() - 1);
                if(lastChar == '0' || lastChar == '1' || lastChar == '2' || lastChar == '3' || lastChar == '4' || lastChar == '5' || lastChar == '6' || lastChar == '7' || lastChar == '8' || lastChar == '9' || lastChar == '.'){
                    subtraiValores(Double.parseDouble(getSaque.getText()), saldoSaque, chequeSaque);
                    if(conta.getOperacao().equals("001")){
                        if(Double.parseDouble(chequeSaque.getText()) >= 0){
                            segueSaque.setVisible(true);
                            erroSaque.setVisible(false);
                        }else{
                            segueSaque.setVisible(false);
                            erroSaque.setVisible(true);
                        }
                    }else{
                        if(Double.parseDouble(saldoSaque.getText()) >= 0){
                            segueSaque.setVisible(true);
                            erroSaque.setVisible(false);
                        }else{
                            segueSaque.setVisible(false);
                            erroSaque.setVisible(true);
                        }
                    }
                }else getSaque.setText(getSaque.getText().substring(0, getSaque.getText().length()-1));
                System.out.println(lastChar);

                if(Double.parseDouble(saldoSaque.getText()) <= 0){
                    saldoSaque.setForeground(Color.red);
                }else saldoSaque.setForeground(corCinza);
                if(Double.parseDouble(chequeSaque.getText()) < 300){
                    chequeSaque.setForeground(Color.red);
                }else chequeSaque.setForeground(corCinza);
            }
        });

        voltarExtrato.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelRoll.removeAll();
            }
        });
        sair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Inicio.setVisible(false);
                Entrar.setVisible(true);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                sair.setBounds(400, 0, 110, 50);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                sair.setBounds(410, 0, 120, 50);
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
                labelEntrar.setBounds(0, 110, 210, 40);
                labelEntrar.setFont(new Font("Arial", Font.PLAIN, 25));
                botaoRegister.setBounds(210, 110, 300, 40);
                botaoRegister.setFont(new Font("Arial", Font.PLAIN, 30));
                borderRegister.setBounds(490, 150, 30, 680);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                labelEntrar.setBounds(0, 110, 300, 40);
                labelEntrar.setFont(new Font("Arial", Font.PLAIN, 30));
                botaoRegister.setBounds(300, 110, 210, 40);
                botaoRegister.setFont(new Font("Arial", Font.PLAIN, 25));
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
                botaoLogin.setBounds(0, 110, 300, 40);
                botaoLogin.setFont(new Font("Arial", Font.PLAIN, 30));
                labelCadastrar.setBounds(300, 110, 210, 40);
                labelCadastrar.setFont(new Font("Arial", Font.PLAIN, 25));
                borderLogin.setBounds(0, 150, 10, 680);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {

                botaoLogin.setBounds(0, 110, 210, 40);
                botaoLogin.setFont(new Font("Arial", Font.PLAIN, 25));
                labelCadastrar.setBounds(210, 110, 300, 40);
                labelCadastrar.setFont(new Font("Arial", Font.PLAIN, 30));
                borderLogin.setBounds(0, 150, 15, 680);
            }
        });
        segueSaque.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                panelSacar.setVisible(false);
                panelVerificaSenha.setVisible(true);
                segueSaque.setVisible(false);
                erroSaque.setVisible(true);
                VerificaSenha(Inicio, panelVerificaSenha, validar, verifica, 2);
            }
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
            }
        });

        segueDeposito.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                panelDepositar.setVisible(false);
                panelVerificaSenha.setVisible(true);
                VerificaSenha(Inicio, panelVerificaSenha, validar, verifica, 1);
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
                                if(conta.getOperacao().equals("013")){
                                    chequeInicial.setVisible(false);
                                    labelCheque.setVisible(false);
                                    labelChequeSaque.setVisible(false);
                                    chequeSaque.setVisible(false);
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
                            chequeInicial.setVisible(false);
                            labelCheque.setVisible(false);
                            labelChequeSaque.setVisible(false);
                            chequeSaque.setVisible(false);
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
                            break;
                        case 2:
                            saca();
                            break;
                        default:
                            break;
                    }
                    Inicio.setVisible(true);
                    panelSenha.setVisible(false);
                    verifica.setText("");
                    
                }
            }
        });

    }

    public static void saca(){
        if(conta.getOperacao().equals("001")){
            if(Double.parseDouble(getSaque.getText()) < conta.getSaldo()){
                Double valorSaldo = conta.getSaldo() - Double.parseDouble(getSaque.getText());
                con.atualizaSaldo(valorSaldo, cliente.getId());
                con.InsereExtrato(conta.getNumero(), "Saque", Double.parseDouble(getSaque.getText()), conta.getCheque(), conta.getSaldo(), valorSaldo, " ");
                conta.setSaldo(valorSaldo);
            }else{
                if(conta.getSaldo() <= 0){
                    Double valorCheque = (conta.getCheque() - Double.parseDouble(getSaque.getText()));
                    con.atualizaSaldo((conta.getSaldo() - Double.parseDouble(getSaque.getText())), cliente.getId());
                    con.atualizaCheque(valorCheque, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Saque", Double.parseDouble(getSaque.getText()), valorCheque, conta.getSaldo(), (conta.getSaldo() - Double.parseDouble(getSaque.getText())), "Utilização do cheque especial");
                    conta.setSaldo(conta.getSaldo() - Double.parseDouble(getSaque.getText()));
                    conta.setCheque(valorCheque);
                }else{
                    Double valorCheque = (conta.getSaldo() + conta.getCheque()) - Double.parseDouble(getSaque.getText());
                    con.atualizaSaldo((conta.getSaldo() - Double.parseDouble(getSaque.getText())), cliente.getId());
                    con.atualizaCheque(valorCheque, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Saque", Double.parseDouble(getSaque.getText()), valorCheque, conta.getSaldo(), (conta.getSaldo() - Double.parseDouble(getSaque.getText())), "Utilização do cheque especial");
                    conta.setSaldo(conta.getSaldo() - Double.parseDouble(getSaque.getText()));
                    conta.setCheque(valorCheque);
                }
                
            }
        }else{
            Double valorSaldo = conta.getSaldo() - Double.parseDouble(getSaque.getText());
            con.atualizaSaldo(valorSaldo, cliente.getId());
            con.InsereExtrato(conta.getNumero(), "Saque", Double.parseDouble(getSaque.getText()), conta.getCheque(), conta.getSaldo(), valorSaldo, " ");
            conta.setSaldo(valorSaldo);
        }
        Atualiza_valores(); 
        getSaque.setText("");
    }

    public static void deposita(){
        if(conta.getOperacao().equals("001")){
            if(conta.getSaldo() < 0){
                double valor = (Double.parseDouble(getDeposito.getText()) + conta.getCheque());
                if(valor > 300.0){
                    con.atualizaCheque(300, cliente.getId());
                    con.atualizaSaldo(valor - 300, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Depósito",Double.parseDouble(getDeposito.getText()), 300.0, conta.getSaldo(), (valor-300), " ");
                    conta.setSaldo((valor - 300));
                    conta.setCheque(300);
                }else{
                    con.atualizaSaldo((conta.getSaldo() + (valor - conta.getCheque())), cliente.getId());
                    con.atualizaCheque(valor, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Depósito",Double.parseDouble(getDeposito.getText()), valor, conta.getSaldo(), (conta.getSaldo() + (valor - conta.getCheque())), "Cobrança de crédito especial");
                    conta.setSaldo((conta.getSaldo() + (valor - conta.getCheque())));
                    conta.setCheque(valor);
                }
            }else{
                double valor = Double.parseDouble(getDeposito.getText()) + conta.getSaldo();
                con.atualizaSaldo(valor, cliente.getId());
                con.InsereExtrato(conta.getNumero(), "Depósito",Double.parseDouble(getDeposito.getText()), conta.getCheque(), conta.getSaldo(), valor, " ");
                conta.setSaldo(valor);
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
    public static void subtraiValores(Double valorRetirada, JLabel saldo, JLabel cheque){
            if(valorRetirada < conta.getSaldo()){
                cheque.setText(Double.toString(conta.getCheque()));
                String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);
                if(valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)){
                    saldo.setText(valorSaldo);
                }else saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
            }else{
                if(conta.getSaldo() <= 0){
                    if(valorRetirada <= conta.getCheque()){
                        String valor = Double.toString(conta.getCheque() - valorRetirada);
                        String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);
                    
                        if(valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)){
                            saldo.setText(valorSaldo);
                        }else saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
                        if(valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)){
                            cheque.setText(valor);
                        }else cheque.setText(valor.substring(0, (valor.indexOf(".") + 3)));
                    }else{
                        String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);
                        if(valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)){
                            saldo.setText(valorSaldo);
                        }else saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
                        String valor = Double.toString(conta.getCheque() - valorRetirada);
                        if(valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)){
                            cheque.setText(valor);
                        }else cheque.setText(valor.substring(0, (valor.indexOf(".") + 3)));
                    }
                }else{
                    if(valorRetirada <= (conta.getSaldo() + conta.getCheque())){
                        String valor = Double.toString((conta.getSaldo() + conta.getCheque()) - valorRetirada);
                        String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);
                        
                        if(valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)){
                            saldo.setText(valorSaldo);
                        }else saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
                        if(valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)){
                            cheque.setText(valor);
                        }else cheque.setText(valor.substring(0, (valor.indexOf(".") + 3)));
                        
                    }else{
                        String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);
                        if(valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)){
                            saldo.setText(valorSaldo);
                        }else saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
                        String valor = Double.toString((conta.getSaldo() + conta.getCheque()) - valorRetirada);
                        if(valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)){
                            cheque.setText(valor);
                        }else cheque.setText(valor.substring(0, (valor.indexOf(".") + 3)));
                    }
                }
            }
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

        if(conta.getOperacao().equals("013")){
            labelCheque.setVisible(false);
            Saldocheque.setVisible(false);
        }

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
            saldoTransferir.setText(Double.toString(conta.getSaldo()));
        }else{
            saldoInicial.setText(Double.toString(conta.getSaldo()).substring(0, (Double.toString(conta.getSaldo()).indexOf(".") + 3)));
            saldoSaque.setText(Double.toString(conta.getSaldo()).substring(0, (Double.toString(conta.getSaldo()).indexOf(".") + 3)));
            saldoTransferir.setText(Double.toString(conta.getSaldo()).substring(0, (Double.toString(conta.getSaldo()).indexOf(".") + 3)));
        } 
        if(Double.toString(conta.getCheque()).indexOf(".") == -1 || Double.toString(conta.getCheque()).length() < (Double.toString(conta.getCheque()).indexOf(".") + 3)){
            chequeInicial.setText(Double.toString(conta.getCheque()));
            chequeSaque.setText(Double.toString(conta.getCheque()));
            chequeTransferir.setText(Double.toString(conta.getCheque()));
        }else{
            chequeInicial.setText(Double.toString(conta.getCheque()).substring(0, (Double.toString(conta.getCheque()).indexOf(".") + 3)));
            chequeSaque.setText(Double.toString(conta.getCheque()).substring(0, (Double.toString(conta.getCheque()).indexOf(".") + 3)));
            chequeTransferir.setText(Double.toString(conta.getCheque()).substring(0, (Double.toString(conta.getCheque()).indexOf(".") + 3)));
        } 
        if(conta.getSaldo() <= 0){
            saldoInicial.setForeground(Color.red);
            saldoSaque.setForeground(Color.red);
            saldoTransferir.setForeground(Color.red);
            campoInicial.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, corVermelho));
        }else{
            saldoInicial.setForeground(corCinza);
            saldoSaque.setForeground(corCinza);
            saldoTransferir.setForeground(corCinza);
            campoInicial.setBackground(corAmarelo);
            campoInicial.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, corVermelho));
        }
        if(conta.getCheque() < 300){
            chequeInicial.setForeground(corVermelho);
            chequeSaque.setForeground(Color.red);
            chequeTransferir.setForeground(Color.red);
        }else{
            chequeInicial.setForeground(Color.white);
            chequeSaque.setForeground(corCinza);
            chequeTransferir.setForeground(corCinza);
        }
    }
}
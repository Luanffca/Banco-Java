import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;

public class TelaOperacao {
    static Color corAzul = new Color(34, 92, 150);
    static Color corCinza = new Color(67, 87, 114);
    static Color corAmarelo = new Color(254, 170, 58);
    static Color corVermelho = new Color(234, 100, 30);
    static Texto textos = new Texto();
    static Botao botoes = new Botao();
    static campoTxt campos = new campoTxt();

    static Conexao con = new Conexao();
    static Cliente cliente = null;
    static Conta conta = null;
    static Cliente newCliente = null;
    static Conta newConta = null;
    static JTextField getAgencia;
    static JTextField getOperacao;
    static JTextField getNumeroConta;
    static JFormattedTextField getPixCPF;
    static JTextField getPixEmail;
    static JFormattedTextField getPixNumero;
    static JTextField getEmprestimo;
    static JLabel campoInicial;
    public static double ValorOperacao;
    static int qntExtratos;
    static String CadastrarTipoConta;

    static JLabel saldoSaque;
    static JLabel chequeSaque;
    static JLabel saldoTransferir;
    static JLabel chequeTransferir;
    static JLabel labelChequeSaque;
    static JLabel labelChequeTransferir;
    static int NumeroConta;
    static int Agencia;
    static String tipoTransferencia;
    static String tipoContaTransferencia;
    static String tipoPix;
    static String chavePix;

    static JLabel Nome;
    static JLabel DataNasc;
    static JLabel CPF;
    static JLabel RG;
    static JLabel Endereco;
    static JLabel CEP;
    static JLabel Email;
    static JLabel Celular;
    static JLabel Usuario;

    public static void atualizaTela() {
        if (conta.getOperacao().equals("013")) {
            chequeSaque.setVisible(false);
            chequeTransferir.setVisible(false);
            labelChequeSaque.setVisible(false);
            labelChequeTransferir.setVisible(false);
        }
        Nome.setText("Nome: " + cliente.getNome());
        DataNasc.setText("Data de nascimento: " + cliente.getDataNascimento());
        CPF.setText("CPF: " + cliente.getCPF());
        RG.setText("RG: " + cliente.getRG());
        Endereco.setText("Endereço: " + cliente.getEndereco());
        CEP.setText("CEP: " + cliente.getCep());
        Email.setText("Email: " + cliente.getEmail());
        Celular.setText("Celular: " + cliente.getCelular());
        Usuario.setText("Usuário: " + cliente.getUsuario());

        saldoSaque.setText(Double.toString(conta.getSaldo()));
        chequeSaque.setText(Double.toString(conta.getCheque()));
        saldoTransferir.setText(Double.toString(conta.getSaldo()));
        chequeTransferir.setText(Double.toString(conta.getCheque()));
    }

    public static void subtraiValores(Double valorRetirada, JLabel saldo, JLabel cheque) {
        if (valorRetirada < conta.getSaldo()) {
            cheque.setText(Double.toString(conta.getCheque()));
            String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);
            if (valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)) {
                saldo.setText(valorSaldo);
            } else
                saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
        } else {
            if (conta.getSaldo() <= 0) {
                if (valorRetirada <= conta.getCheque()) {
                    String valor = Double.toString(conta.getCheque() - valorRetirada);
                    String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);

                    if (valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)) {
                        saldo.setText(valorSaldo);
                    } else
                        saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
                    if (valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)) {
                        cheque.setText(valor);
                    } else
                        cheque.setText(valor.substring(0, (valor.indexOf(".") + 3)));
                } else {
                    String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);
                    if (valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)) {
                        saldo.setText(valorSaldo);
                    } else
                        saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
                    String valor = Double.toString(conta.getCheque() - valorRetirada);
                    if (valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)) {
                        cheque.setText(valor);
                    } else
                        cheque.setText(valor.substring(0, (valor.indexOf(".") + 3)));
                }
            } else {
                if (valorRetirada <= (conta.getSaldo() + conta.getCheque())) {
                    String valor = Double.toString((conta.getSaldo() + conta.getCheque()) - valorRetirada);
                    String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);

                    if (valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)) {
                        saldo.setText(valorSaldo);
                    } else
                        saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
                    if (valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)) {
                        cheque.setText(valor);
                    } else
                        cheque.setText(valor.substring(0, (valor.indexOf(".") + 3)));

                } else {
                    String valorSaldo = Double.toString(conta.getSaldo() - valorRetirada);
                    if (valorSaldo.indexOf(".") == -1 || valorSaldo.length() < (valorSaldo.indexOf(".") + 3)) {
                        saldo.setText(valorSaldo);
                    } else
                        saldo.setText(valorSaldo.substring(0, (valorSaldo.indexOf(".") + 3)));
                    String valor = Double.toString((conta.getSaldo() + conta.getCheque()) - valorRetirada);
                    if (valor.indexOf(".") == -1 || valor.length() < (valor.indexOf(".") + 3)) {
                        cheque.setText(valor);
                    } else
                        cheque.setText(valor.substring(0, (valor.indexOf(".") + 3)));
                }
            }
        }
    }
    public static JPanel TelaCadastrar(JPanel telaPrincipal, JPanel panelVerificaSenha){
        JPanel telaCadastrar = new JPanel();
        telaCadastrar.setBackground(corCinza);
        telaCadastrar.setLayout(null);
        telaCadastrar.setVisible(false);
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
        JComboBox<String> tipoCliente = new JComboBox<String>();
        tipoCliente.addItem("cliente");
        tipoCliente.addItem("caixa");
        tipoCliente.addItem("secretario");
        tipoCliente.addItem("gerente");
        tipoCliente.setBounds(350, 110, 110, 30);
        tipoCliente.setFont(new Font("Arial", Font.PLAIN, 20));
        tipoCliente.setBackground(Color.white);
        tipoCliente.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));

        JComboBox<String> CadastrarTipo = new JComboBox<String>();
        CadastrarTipo.addItem("Corrente");
        CadastrarTipo.addItem("Poupança");
        CadastrarTipo.setBounds(90, 540, 150, 30);
        CadastrarTipo.setFont(new Font("Arial", Font.PLAIN, 20));
        CadastrarTipo.setBackground(Color.white);
        CadastrarTipo.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));

        JTextField getNome = campos.campoCadastrar(40, 110, 300, 30);
        JTextField getUsuario = campos.campoCadastrar(40, 180, 200, 30);
        JTextField getEndereco = campos.campoCadastrar(40, 320, 240, 30);
        JTextField getRG = campos.campoCadastrar(260, 250, 200, 30);
        JTextField getEmail = campos.campoCadastrar(40, 390, 240, 30);

        JFormattedTextField getData = new JFormattedTextField(mascaraData);
        getData.setFont(new Font("Arial", Font.PLAIN, 20));
        getData.setBounds(260, 180, 200, 30);
        getData.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCEP = new JFormattedTextField(mascaraCEP);
        getCEP.setFont(new Font("Arial", Font.PLAIN, 20));
        getCEP.setBounds(300, 320, 160, 30);
        getCEP.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCPF = new JFormattedTextField(mascaraCPF);
        getCPF.setFont(new Font("Arial", Font.PLAIN, 20));
        getCPF.setBounds(40, 250, 200, 30);
        getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JFormattedTextField getCelular = new JFormattedTextField(mascaraNumero);
        getCelular.setFont(new Font("Arial", Font.PLAIN, 20));
        getCelular.setBounds(300, 390, 160, 30);
        getCelular.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JPasswordField getSenha = new JPasswordField();
        getSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        getSenha.setBounds(100, 460, 300, 30);
        getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JButton cadastrar = botoes.botao("Cadastrar", corAmarelo, Color.black, 100, 610, 310, 40, 30);
        JFormattedTextField senhaConta = new JFormattedTextField(mascaraSenha);
        senhaConta.setBounds(350, 540, 100, 30);
        senhaConta.setFont(new Font("Arial", Font.PLAIN, 20));
        senhaConta.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));

        telaCadastrar.add(getNome);
        telaCadastrar.add(getUsuario);
        telaCadastrar.add(getEndereco);
        telaCadastrar.add(getRG);
        telaCadastrar.add(getEmail);
        telaCadastrar.add(getData);
        telaCadastrar.add(getCPF);
        telaCadastrar.add(getCEP);
        telaCadastrar.add(getCelular);
        telaCadastrar.add(getSenha);
        telaCadastrar.add(tipoCliente);
        telaCadastrar.add(cadastrar);
        telaCadastrar.add(CadastrarTipo);
        telaCadastrar.add(senhaConta);
        telaCadastrar.add(textos.textos("Nome Completo", 40, 80, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Vínculo", 350, 80, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("CEP", 300, 290, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Endereço", 40, 290, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Data de Nascimento", 260, 150, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Usuário", 40, 150, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("CPF", 40, 220, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("RG", 260, 220, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Email", 40, 360, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Numero de celular", 300, 360, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Senha", 100, 430, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Dados da Conta", 200, 500, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Tipo", 40, 540, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos("Senha", 280, 540, 300, 30, 20, Color.white));
        telaCadastrar.add(textos.textos(0, 500, 520, 80, corAzul));
        telaCadastrar.add(botoes.botaoVoltar(telaCadastrar, telaPrincipal));
        telaCadastrar.add(textos.textos(" Cadastrar Conta ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));

        cadastrar.addActionListener(new java.awt.event.ActionListener() {
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
                    if (senhaConta.getText().equals("    ")) {
                        senhaConta.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.red));
                    } else {
                            String operacao;
                            String tipo = String.valueOf(tipoCliente.getSelectedItem());
                            Double cheque;
                            if (CadastrarTipo.getSelectedIndex() == 1) {
                                CadastrarTipoConta = "001";
                            } else {
                                CadastrarTipoConta = "001";
                                cheque = 0.0;
                            }
    
                            String newSenha = new String(getSenha.getPassword());
                            newCliente = new Cliente(tipo, 1, getNome.getText(), getUsuario.getText(), getData.getText(), getCPF.getText(), getRG.getText(), getEndereco.getText(), getCEP.getText(), getEmail.getText(), getCelular.getText(), newSenha);
    
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
                                panelVerificaSenha.setVisible(true);
    
                        
                    }
                }

            }
        });

        telaCadastrar.setBounds(0, 0, 520, 720);
        return telaCadastrar;
    }
    public static JPanel TelaConta(JPanel telaPrincipal){
        JPanel telaConta = new JPanel();

        Nome = textos.textos("Nome: ", 20, 100, 300, 50, 20, Color.white);
        Usuario = textos.textos("Usuário: ", 20, 150, 300, 50, 20, Color.white);
        DataNasc = textos.textos("Data de nascimento: ", 20, 200, 300, 50, 20, Color.white);
        CPF = textos.textos("CPF: ", 20, 250, 300, 50, 20, Color.white);
        RG = textos.textos("RG: ", 20, 300, 300, 50, 20, Color.white);
        CEP = textos.textos("CEP: ", 20, 350, 300, 50, 20, Color.white);
        Email = textos.textos("Email: ", 20, 400, 300, 50, 20, Color.white);
        Endereco = textos.textos("Endereço: ", 20, 450, 300, 50, 20, Color.white);
        Celular = textos.textos("Celular: ", 20, 500, 300, 50, 20, Color.white);
        
        telaConta.setBackground(corCinza);
        telaConta.setLayout(null);
        telaConta.setVisible(false);
        telaConta.add(Nome);
        telaConta.add(Usuario);
        telaConta.add(DataNasc);
        telaConta.add(CPF);
        telaConta.add(RG);
        telaConta.add(CEP);
        telaConta.add(Email);
        telaConta.add(Endereco);
        telaConta.add(Celular);
        telaConta.add(botoes.botaoVoltar(telaConta, telaPrincipal));
        telaConta.add(textos.textos(" Minha Conta ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        telaConta.setBounds(0, 0, 520, 720);
        return telaConta;
    }

    public static JPanel TelaDepositar(JPanel telaPrincipal, JPanel panelVerificaSenha) {
        JPanel telaDepositar = new JPanel();
        JButton segueDeposito = botoes.botao("Prosseguir", corAzul, Color.white, 100, 500, 300, 40, 30, 1, 3, 3, 1,
                Color.white);
        JTextField getDeposito = new JTextField();
        getDeposito.setBounds(20, 150, 460, 50);
        getDeposito.setFont(new Font("Arial", Font.PLAIN, 30));
        getDeposito.setHorizontalAlignment(SwingConstants.CENTER);
        getDeposito.setForeground(Color.white);
        getDeposito.setBackground(corCinza);
        getDeposito.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));

        telaDepositar.setBackground(corCinza);
        telaDepositar.setLayout(null);
        telaDepositar.setVisible(false);
        telaDepositar
                .add(textos.textosAlinhados(" Digite o valor que deseja depositar ", 0, 100, 520, 50, 25, Color.white));
        telaDepositar.add(textos.textos(" Depositar ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));
        telaDepositar.add(getDeposito);
        telaDepositar.add(botoes.botaoVoltar(telaDepositar, telaPrincipal));
        telaDepositar.add(segueDeposito);
        segueDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaDepositar.setVisible(false);
                panelVerificaSenha.setVisible(true);
                ValorOperacao = Double.parseDouble(getDeposito.getText());
                getDeposito.setText("");
            }
        });
        telaDepositar.setBounds(0, 0, 520, 720);
        return telaDepositar;
    }

    public static JPanel TelaSacar(JPanel telaPrincipal, JPanel panelVerificaSenha) {
        JPanel telaSacar = new JPanel();
        JButton segueSaque = botoes.botao("Prosseguir", corAzul, Color.white, 100, 500, 300, 40, 30, 1, 3, 3, 1,
                Color.white);
        segueSaque.setVisible(false);
        JLabel erroSaque = textos.textosAlinhados("Prosseguir", 100, 500, 300, 40, 30, corAzul, Color.white, 1, 3, 3, 1,
                Color.white);

        saldoSaque = textos.textos(" R$", 330, 210, 150, 50, 25, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        chequeSaque = textos.textos(" R$", 340, 260, 140, 40, 20, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        labelChequeSaque = textos.textos(" Cheque especial:", 20, 260, 380, 40, 20, corCinza, corAmarelo, 0, 0,
                0, 0, corAmarelo);
        JTextField getSaque = campoValor(SwingConstants.CENTER, 20, 150, 460, 50, 30, Color.white, corCinza, 0, 0, 3, 0, Color.white, erroSaque, segueSaque, saldoSaque, chequeSaque);
        telaSacar.setBackground(corCinza);
        telaSacar.setLayout(null);
        telaSacar.setVisible(false);
        telaSacar.add(botoes.botaoVoltar(telaSacar, telaPrincipal));
        telaSacar.add(getSaque);
        telaSacar.add(saldoSaque);
        telaSacar.add(chequeSaque);
        telaSacar.add(segueSaque);
        telaSacar.add(erroSaque);
        telaSacar.add(textos.textos(" Saldo:", 20, 210, 380, 50, 25, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo));
        telaSacar.add(labelChequeSaque);
        telaSacar.add(textos.textosAlinhados(" Digite o valor que deseja Sacar ", 0, 100, 520, 50, 25, Color.white));
        telaSacar.add(textos.textos(" Sacar ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));

        segueSaque.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                telaSacar.setVisible(false);
                panelVerificaSenha.setVisible(true);
                segueSaque.setVisible(false);
                erroSaque.setVisible(true);
                ValorOperacao = Double.parseDouble(getSaque.getText());
                getSaque.setText("");
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
        telaSacar.setBounds(0, 0, 520, 720);
        return telaSacar;
    }

    public static JPanel TelaTransferir(JPanel telaPrincipal, JPanel panelVerificaSenha) {
        JPanel telaTransferir = new JPanel();
        JPanel panelPix = new JPanel();
        JPanel panelTransferencia = new JPanel();

        telaTransferir.setBackground(corCinza);
        telaTransferir.setLayout(null);
        telaTransferir.setVisible(false);
        saldoTransferir = textos.textos("", 330, 70, 150, 50, 25, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        chequeTransferir = textos.textos("", 340, 120, 140, 40, 20, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        labelChequeTransferir = textos.textos(" Cheque especial:", 0, 120, 380, 40, 20, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo);
        telaTransferir.add(saldoTransferir);
        telaTransferir.add(chequeTransferir);
        telaTransferir.add(textos.textos(" Saldo:", 0, 70, 380, 50, 25, corCinza, corAmarelo, 0, 0, 0, 0, corAmarelo));
        telaTransferir.add(labelChequeTransferir);
        telaTransferir.setBounds(0, 0, 520, 720);

        MaskFormatter mascaraCPF = null;
        MaskFormatter mascaraNumero = null;
        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
            mascaraNumero = new MaskFormatter("(##) # ####-####");
        } catch (ParseException e2) {
            e2.printStackTrace();
        }

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

        tipoChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        JTextArea getDescricao = new JTextArea();
        JScrollPane desc = new JScrollPane(getDescricao);
        desc.setBounds(260, 210, 200, 70);
        getDescricao.setFont(new Font("Arial", Font.PLAIN, 20));
        getDescricao.setForeground(corAzul);
        desc.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, corAzul));
        getDescricao.setLineWrap(true);
        getDescricao.setEditable(true);

        desc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton EnviarPix = botoes.botao("Enviar pix", corCinza, Color.white, 100, 350, 300, 40, 30, 1, 3, 3, 1,
                Color.white);
        EnviarPix.setVisible(false);
        JLabel erroPix = textos.textosAlinhados("Enviar pix", 100, 350, 300, 40, 30, corCinza, Color.white, 1, 3, 3, 1,
                corCinza);
        JLabel labelPix = textos.textosAlinhados("Pix", 0, 0, 300, 40, 30, corAzul, Color.white);
        JButton botaoTransferencia = botoes.botao("Transferir", corAmarelo, Color.black, 300, 0, 210, 40, 25);
        JLabel bordaPix = textos.textos(490, 0, 20, 500, corAmarelo);
        JTextField getPix = campoValor(SwingConstants.LEFT, 20, 270, 200, 30, 20, corAzul, Color.white, 1, 3, 3, 1, corAzul, erroPix, EnviarPix, saldoTransferir, chequeTransferir);
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

        JButton BotaoTransferir = botoes.botao("Transferir", corCinza, Color.white, 100, 350, 300, 40, 30, 1, 3, 3, 1,
                Color.white);
        JLabel erroTransferir = textos.textosAlinhados("Transferir", 100, 350, 300, 40, 30, corCinza, Color.white, 1, 3,
                3, 1, corCinza);
        BotaoTransferir.setVisible(false);
        JLabel bordaTransferencia = textos.textos(0, 0, 10, 500, corAmarelo);
        JTextField getTransferencia = campoValor(SwingConstants.LEFT, 40, 280, 200, 30, 20, corAzul, Color.white, 1, 3, 3, 1, corAzul, erroTransferir, BotaoTransferir, saldoTransferir, chequeTransferir);

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

        telaTransferir.add(panelPix);
        telaTransferir.add(panelTransferencia);

        telaTransferir.add(botoes.botaoVoltar(telaTransferir, telaPrincipal));
        telaTransferir
                .add(textos.textos(" Transferir ", 50, 0, 520, 50, 25, Color.white, corAzul, 0, 0, 3, 0, Color.white));

        botaoPix.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                panelTransferencia.setVisible(false);
                panelPix.setVisible(true);
                getTransferencia.setText("");
                saldoTransferir.setText(Double.toString(conta.getSaldo()));
                chequeTransferir.setText(Double.toString(conta.getCheque()));
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
                saldoTransferir.setText(Double.toString(conta.getSaldo()));
                chequeTransferir.setText(Double.toString(conta.getCheque()));
                erroTransferir.setVisible(true);
                BotaoTransferir.setVisible(false);
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
        BotaoTransferir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                telaTransferir.setVisible(false);
                panelVerificaSenha.setVisible(true);
                ValorOperacao = Double.parseDouble(getTransferencia.getText());
                Agencia = Integer.parseInt(getAgencia.getText());
                NumeroConta = Integer.parseInt(getNConta.getText());
                if(tipoConta.getSelectedIndex() == 0){
                    tipoContaTransferencia = "001";
                }else tipoContaTransferencia = "013";
                tipoTransferencia = "Transferencia";
                getTransferencia.setText("");
                getNConta.setText("");
                getAgencia.setText("");
            }

        });

        EnviarPix.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                ValorOperacao = Double.parseDouble(getPix.getText());
                switch (tipoChave.getSelectedIndex()) {
                    case 0:
                        getPixCPF.setVisible(true);
                        tipoPix = "cpf";
                        chavePix = getPixCPF.getText();
                        break;
                    case 1:
                        getPixEmail.setVisible(true);
                        tipoPix = "email";
                        chavePix = getPixEmail.getText();
                        break;
                    case 2:
                        getPixNumero.setVisible(true);
                        tipoPix = "celular";
                        chavePix = getPixNumero.getText();
                    default:
                        break;
                }
                tipoTransferencia = "pix";
                getPix.setText("");
                getDescricao.setText("");
                telaTransferir.setVisible(false);
                panelVerificaSenha.setVisible(true);
            }

        });
        telaTransferir.setBounds(0, 0, 520, 720);
        return telaTransferir;
    }
    public static String getChavePix() {
        return chavePix;
    }
    public static String getTipoPix() {
        return tipoPix;
    }
    public static String getTipoContaTransferencia() {
        return tipoContaTransferencia;
    }
    public static String getTipoTransferencia() {
        return tipoTransferencia;
    }
    public static int getAgencia() {
        return Agencia;
    }
    public static int getNumeroConta() {
        return NumeroConta;
    }
    public static void setCliente(Cliente cliente) {
        TelaOperacao.cliente = cliente;
    }

    public static void setConta(Conta conta) {
        TelaOperacao.conta = conta;
    }

    public static double getValorOperacao() {
        return ValorOperacao;
    }
    public static Cliente getNewCliente() {
        return newCliente;
    }

    public static JTextField campoValor(int Align,int X, int Y, int W, int H, int F, Color corTexto, Color corFundo, int T, int L, int B, int R,
            Color corBorda, JLabel erro, JButton seguir, JLabel saldoLabel, JLabel chequeLabel) {
        JTextField cmp = new JTextField();
        cmp.setBounds(X, Y, W, H);
        cmp.setFont(new Font("Arial", Font.PLAIN, F));
        cmp.setForeground(corTexto);
        cmp.setBackground(corFundo);
        cmp.setHorizontalAlignment(Align);
        cmp.setBorder(BorderFactory.createMatteBorder(T, L, B, R, corBorda));

        cmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (cmp.getText().isEmpty()) {
                    seguir.setVisible(false);
                    erro.setVisible(true);
                    saldoLabel.setText(Double.toString(conta.getSaldo()));
                    chequeLabel.setText(Double.toString(conta.getCheque()));
                } else {
                    char lastChar = cmp.getText().charAt(cmp.getText().length() - 1);
                    if (lastChar == '0' || lastChar == '1' || lastChar == '2' || lastChar == '3' || lastChar == '4'
                            || lastChar == '5' || lastChar == '6' || lastChar == '7' || lastChar == '8'
                            || lastChar == '9' || lastChar == '.') {
                        subtraiValores(Double.parseDouble(cmp.getText()), saldoLabel, chequeLabel);
                        if (conta.getOperacao().equals("001")) {
                            if (Double.parseDouble(chequeLabel.getText()) >= 0) {
                                seguir.setVisible(true);
                                erro.setVisible(false);
                            } else {
                                seguir.setVisible(false);
                                erro.setVisible(true);
                            }
                        } else {
                            if (Double.parseDouble(saldoLabel.getText()) >= 0) {
                                seguir.setVisible(true);
                                erro.setVisible(false);
                            } else {
                                seguir.setVisible(false);
                                erro.setVisible(true);
                            }
                        }
                    } else
                        cmp.setText(cmp.getText().substring(0, cmp.getText().length() - 1));
                    System.out.println(lastChar);

                    if (Double.parseDouble(saldoLabel.getText()) <= 0) {
                        saldoLabel.setForeground(Color.red);
                    } else
                        saldoLabel.setForeground(corCinza);
                    if (Double.parseDouble(chequeLabel.getText()) < 300) {
                        chequeLabel.setForeground(Color.red);
                    } else
                        chequeLabel.setForeground(corCinza);
                }
            }
        });
        return cmp;
    }

}
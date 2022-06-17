import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class TelaPrincipal {
    static Color corAzul = new Color(34, 92, 150);
    static Color corCinza = new Color(67, 87, 114);
    static Color corAmarelo = new Color(254, 170, 58);
    static Color corVermelho = new Color(234, 100, 30);

    static Conexao con = new Conexao();
    static Cliente cliente;
    static Conta conta;

    static Extrato extrato = null;
    static JLabel saldoInicial;
    static JLabel chequeInicial;
    static JLabel campoInicial;
    static JLabel labelCheque;
    static int qntExtratos;

    static JLabel bemVindo;
    static JPanel inicioDiretor = new JPanel();
    static JPanel inicioGerente = new JPanel();
    static JPanel inicioCliente = new JPanel();
    static TelaOperacao telaOperacao;
    static JLabel op = new JLabel();

    public void atualizaTela() {
        TelaOperacao.setConta(conta);
        TelaOperacao.setCliente(cliente);
        TelaOperacao.atualizaTela();
        if (conta.getOperacao().equals("013")) {
            chequeInicial.setVisible(false);
            labelCheque.setVisible(false);
        }

        if (cliente.getTipo().equals("diretor")) {
            inicioCliente.setVisible(false);
            inicioGerente.setVisible(false);
            inicioDiretor.setVisible(true);
        } else {
            if (cliente.getTipo().equals("gerente")) {
                inicioDiretor.setVisible(false);
                inicioCliente.setVisible(false);
                inicioGerente.setVisible(true);
            } else {
                inicioDiretor.setVisible(false);
                inicioGerente.setVisible(false);
                inicioCliente.setVisible(true);
            }
        }
        bemVindo.setText("Bem vindo, " + cliente.getNome());
        Atualiza_valores();
    }

    public JPanel TelaPrincipal(JPanel Anterior) {
        Texto textos = new Texto();
        Botao botoes = new Botao();
        campoTxt campos = new campoTxt();

        JPanel panelExtrato = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JPanel panelRoll = new JPanel();
        JPanel panelVerificaSenha = new JPanel();

        JPanel window = new JPanel();
        window.setLayout(null);
        window.setVisible(false);
        window.setBackground(corCinza);

        JPanel telaPrincipal = new JPanel();
        telaPrincipal.setLayout(null);
        telaPrincipal.setVisible(true);
        telaPrincipal.setBackground(corCinza);
        Esquece panel = new Esquece();
        JPanel panelEmprestimo = panel.Emprestimo(telaPrincipal);
        JPanel panelEmprestimoAceito = panel.EmprestimoAceito(telaPrincipal);

        JPanel telaDepositar = TelaOperacao.TelaDepositar(telaPrincipal, panelVerificaSenha);
        JPanel telaSacar = TelaOperacao.TelaSacar(telaPrincipal, panelVerificaSenha);
        JPanel telaTransferir = TelaOperacao.TelaTransferir(telaPrincipal, panelVerificaSenha);
        JPanel telaConta = TelaOperacao.TelaConta(telaPrincipal);
        JPanel telaCadastrar = TelaOperacao.TelaCadastrar(telaPrincipal, panelVerificaSenha);

        // TELA INICIAL Diretor
        JButton contaDiretor = botoes.botaoInicial(" Minha Conta", 0, 210, telaPrincipal, telaConta, op);
        JButton EmprestimoDiretor = botoes.botaoInicial(" Empréstimo", 0, 280, telaPrincipal, telaDepositar, op);
        JButton CadastrarConta = botoes.botaoInicial(" Cadastrar conta", 0, 350, telaPrincipal, telaCadastrar, op);
        JButton showFunci = botoes.botaoInicial(" Funcionários", 0, 420, telaPrincipal, telaDepositar, op);

        inicioDiretor.setBackground(corCinza);
        inicioDiretor.setLayout(null);
        inicioDiretor.setVisible(false);
        inicioDiretor.setBounds(0, 0, 520, 720);
        inicioDiretor.add(CadastrarConta);
        inicioDiretor.add(EmprestimoDiretor);
        inicioDiretor.add(showFunci);
        inicioDiretor.add(contaDiretor);

        // TELA INICIAL GERENTE
        JButton minhaContaGerente = botoes.botaoInicial(" Minha Conta", 0, 210, telaPrincipal, telaConta, op);
        JButton Emprestimo = botoes.botaoInicial(" Empréstimo", 0, 280, telaPrincipal, telaDepositar, op);
        JButton CriarConta = botoes.botaoInicial(" Cadastrar conta", 0, 350, telaPrincipal, telaDepositar, op);
        JButton ShowContas = botoes.botaoInicial(" Mostrar contas", 0, 420, telaPrincipal, telaDepositar, op);

        inicioGerente.setBackground(corCinza);
        inicioGerente.setLayout(null);
        inicioGerente.setVisible(false);
        inicioGerente.setBounds(0, 0, 520, 720);
        inicioGerente.add(CriarConta);
        inicioGerente.add(ShowContas);
        inicioGerente.add(minhaContaGerente);
        inicioGerente.add(Emprestimo);

        // TELA INICIAL Cliente
        JButton depositar = botoes.botaoInicial(" Depositar", 0, 210, telaPrincipal, telaDepositar, op);
        JButton sacar = botoes.botaoInicial(" Sacar", 0, 280, telaPrincipal, telaSacar, op);
        JButton extrato = botoes.botaoInicial(" Extrato", 0, 350, telaPrincipal, panelExtrato, op);
        JButton transferir = botoes.botaoInicial(" Transferir", 0, 420, telaPrincipal, telaTransferir, op);
        JButton emprestimo = botoes.botaoInicial(" Empréstimo", 0, 490, telaPrincipal, panelEmprestimo, op);
        JButton minhaConta = botoes.botaoInicial(" Conta", 0, 560, telaPrincipal, telaConta, op);

        inicioCliente.setBackground(corCinza);
        inicioCliente.setLayout(null);
        inicioCliente.setVisible(false);
        inicioCliente.setBounds(0, 0, 520, 720);
        inicioCliente.add(depositar);
        inicioCliente.add(sacar);
        inicioCliente.add(extrato);
        inicioCliente.add(transferir);
        inicioCliente.add(emprestimo);
        inicioCliente.add(minhaConta);

        // TELA INICIAL
        JButton sair = botoes.botao("Sair", corAmarelo, Color.black, 410, 0, 110, 50, 25, 0, 3, 0, 0, Color.white);
        bemVindo = textos.textos(" Bem vindo ", 0, 0, 410, 50, 25, Color.white, corAzul, 0, 5, 0, 0, corAmarelo);
        saldoInicial = textos.textos(" R$ ", 320, 70, 210, 50, 25, Color.black);
        chequeInicial = textos.textos(" R$ ", 400, 120, 110, 40, 20, Color.white);
        labelCheque = textos.textos(" Cheque especial:", 0, 120, 400, 40, 20, Color.white);
        campoInicial = textos.textos(0, 70, 510, 50, corAmarelo);
        telaPrincipal.add(sair);
        telaPrincipal.add(bemVindo);
        telaPrincipal.add(textos.textos(0, 50, 520, 20, corAzul, 0, 0, 0, 0, corAmarelo));
        telaPrincipal.add(textos.textos(480, 160, 50, 700, Color.white, 0, 0, 0, 0, Color.white));
        telaPrincipal.add(textos.textos(" Saldo:", 0, 70, 300, 50, 25, Color.black));
        telaPrincipal.add(labelCheque);
        telaPrincipal.add(chequeInicial);
        telaPrincipal.add(textos.textos(0, 120, 520, 40, corAzul, 0, 0, 3, 0, Color.white));
        telaPrincipal.add(saldoInicial);
        telaPrincipal.add(campoInicial);
        telaPrincipal.add(inicioCliente);
        telaPrincipal.add(inicioGerente);
        telaPrincipal.add(inicioDiretor);

        // TELA VERIFICAR SENHA
        MaskFormatter mascaraSenha = null;
        try {
            mascaraSenha = new MaskFormatter("####");
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
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
        panelVerificaSenha
                .add(textos.textosAlinhados(" Digite sua senha de 4 digitos ", 0, 100, 520, 50, 25, Color.white));
        panelVerificaSenha.add(validar);
        panelVerificaSenha.add(verifica);

        // TELA EXTRATO
        JButton voltarExtrato = botoes.botaoVoltar(panelExtrato, telaPrincipal);
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

        voltarExtrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelRoll.removeAll();
            }
        });
        sair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                inicioCliente.setVisible(false);
                inicioDiretor.setVisible(false);
                inicioGerente.setVisible(false);
                window.setVisible(false);
                Anterior.setVisible(true);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                sair.setBounds(400, 0, 110, 50);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                sair.setBounds(410, 0, 110, 50);
            }
        });

        extrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultSet rsqnt = con.qntOperacao(conta.getNumero());
                try {
                    while (rsqnt.next()) {
                        qntExtratos = rsqnt.getInt(1);
                        System.out.println(qntExtratos);
                        panelRoll.setPreferredSize(new DimensionUIResource(480, (qntExtratos * 200)));
                        for (int i = 1; i <= qntExtratos; i++) {
                            panelRoll.add(showExtrato(i));
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        validar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (conta.getSenha().equals(verifica.getText())) {
                    System.out.println(op.getText());
                    if (op.getText().contains("Depositar")) {
                        deposita();
                    } else {
                        if (op.getText().contains("Sacar")) {
                            saca();
                        } else {
                            if (op.getText().contains("Transferir")) {
                                if (TelaOperacao.getTipoTransferencia().contains("Transferencia")) {
                                    transfere();
                                }else pix();
                            }
                        }
                    }
                    atualizaTela();
                    panelVerificaSenha.setVisible(false);
                    telaPrincipal.setVisible(true);
                    verifica.setText("");

                }
            }
        });
        telaPrincipal.setBounds(0, 0, 520, 720);
        panelVerificaSenha.setBounds(0, 0, 520, 720);
        panelExtrato.setBounds(0, 0, 520, 720);
        scroll.setSize(new DimensionUIResource(505, 630));
        panelEmprestimo.setSize(new DimensionUIResource(505, 630));
        panelEmprestimoAceito.setSize(new DimensionUIResource(505, 630));

        window.setBounds(0, 0, 520, 720);
        window.add(telaDepositar);
        window.add(telaSacar);
        window.add(telaTransferir);
        window.add(telaConta);
        window.add(telaPrincipal);
        window.add(panelExtrato);
        window.add(panelEmprestimo);
        window.add(panelEmprestimoAceito);
        window.add(telaCadastrar);
        window.add(panelVerificaSenha);
        return window;
    }

    public void Atualiza_valores() {
        if (Double.toString(conta.getSaldo()).indexOf(".") == -1
                || Double.toString(conta.getSaldo()).length() < (Double.toString(conta.getSaldo()).indexOf(".") + 3)) {
            saldoInicial.setText(Double.toString(conta.getSaldo()));
        } else {
            saldoInicial.setText(Double.toString(conta.getSaldo()).substring(0,
                    (Double.toString(conta.getSaldo()).indexOf(".") + 3)));
        }
        if (Double.toString(conta.getCheque()).indexOf(".") == -1 || Double.toString(conta.getCheque())
                .length() < (Double.toString(conta.getCheque()).indexOf(".") + 3)) {
            chequeInicial.setText(Double.toString(conta.getCheque()));
        } else {
            chequeInicial.setText(Double.toString(conta.getCheque()).substring(0,
                    (Double.toString(conta.getCheque()).indexOf(".") + 3)));
        }
        if (conta.getSaldo() <= 0) {
            saldoInicial.setForeground(Color.red);
            campoInicial.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, corVermelho));
        } else {
            saldoInicial.setForeground(corCinza);
            campoInicial.setBackground(corAmarelo);
            campoInicial.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, corVermelho));
        }
        if (conta.getCheque() < 300) {
            chequeInicial.setForeground(corVermelho);
        } else {
            chequeInicial.setForeground(Color.white);
        }
    }
    /*
    public static void criaConta(){
        Cliente newCliente = TelaOperacao.getNewCliente();
                String operacao;
                Double cheque;
                if () {
                    operacao = "001";
                    cheque = 300.0;
                } else {
                    operacao = "013";
                    cheque = 0.0;
                }
                con.Cadastra(newCliente.getTipo(), newCliente.getNome(), newCliente.getUsuario(), newCliente.getDataNascimento(),
                        newCliente.getCPF(), newCliente.getRG(), newCliente.getEndereco(), newCliente.getCep(),
                        newCliente.getEmail(), newCliente.getCelular(), newCliente.getSenha());
                try {
                    ResultSet rsCliente = con.getUsuario(newCliente.getUsuario());
                    while (rsCliente.next()) {
                        newCliente = new Cliente(rsCliente.getString("tipo"), rsCliente.getInt("id"),
                                rsCliente.getString("nome"), rsCliente.getString("usuario"),
                                rsCliente.getString("datanascimento"), rsCliente.getString("cpf"),
                                rsCliente.getString("rg"), rsCliente.getString("endereco"),
                                rsCliente.getString("cep"), rsCliente.getString("email"),
                                rsCliente.getString("celular"), rsCliente.getString("senha"));
                    }
                    con.criaConta(newCliente.getId(), cheque, operacao, senhaConta.getText());
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
*/
    public static void saca() {
        double ValorOperacao = TelaOperacao.getValorOperacao();
        if (conta.getOperacao().equals("001")) {
            if (ValorOperacao < conta.getSaldo()) {
                Double valorSaldo = conta.getSaldo() - ValorOperacao;
                con.atualizaSaldo(valorSaldo, cliente.getId());
                con.InsereExtrato(conta.getNumero(), "Saque", ValorOperacao, conta.getCheque(), conta.getSaldo(),
                        valorSaldo, " ");
                conta.setSaldo(valorSaldo);
            } else {
                if (conta.getSaldo() <= 0) {
                    Double valorCheque = (conta.getCheque() - ValorOperacao);
                    con.atualizaSaldo((conta.getSaldo() - ValorOperacao), cliente.getId());
                    con.atualizaCheque(valorCheque, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Saque", ValorOperacao, valorCheque, conta.getSaldo(),
                            (conta.getSaldo() - ValorOperacao), "Utilização do cheque especial");
                    conta.setSaldo(conta.getSaldo() - ValorOperacao);
                    conta.setCheque(valorCheque);
                } else {
                    Double valorCheque = (conta.getSaldo() + conta.getCheque()) - ValorOperacao;
                    con.atualizaSaldo((conta.getSaldo() - ValorOperacao), cliente.getId());
                    con.atualizaCheque(valorCheque, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Saque", ValorOperacao, valorCheque, conta.getSaldo(),
                            (conta.getSaldo() - ValorOperacao), "Utilização do cheque especial");
                    conta.setSaldo(conta.getSaldo() - ValorOperacao);
                    conta.setCheque(valorCheque);
                }

            }
        } else {
            Double valorSaldo = conta.getSaldo() - ValorOperacao;
            con.atualizaSaldo(valorSaldo, cliente.getId());
            con.InsereExtrato(conta.getNumero(), "Saque", ValorOperacao, conta.getCheque(), conta.getSaldo(),
                    valorSaldo, " ");
            conta.setSaldo(valorSaldo);
        }
    }
    public static void pix() {   
        try {
            System.out.println(TelaOperacao.getTipoPix());
            System.out.println(TelaOperacao.getChavePix());
            ResultSet rsCliente = con.BuscaPix(TelaOperacao.getTipoPix(), TelaOperacao.getChavePix());
            
            while (rsCliente.next()) {
                Cliente clienteTransfere = new Cliente(rsCliente.getString("tipo"), rsCliente.getInt("id"), rsCliente.getString("nome"), rsCliente.getString("usuario"), rsCliente.getString("datanascimento"), rsCliente.getString("cpf"), rsCliente.getString("rg"), rsCliente.getString("endereco"), rsCliente.getString("cep"), rsCliente.getString("email"), rsCliente.getString("celular"), rsCliente.getString("senha"));
                System.out.println(clienteTransfere.getNome());
                double ValorOperacao = TelaOperacao.getValorOperacao();
                if (conta.getOperacao().equals("001")) {
                    if (ValorOperacao < conta.getSaldo()) {
                        Double valorSaldo = conta.getSaldo() - ValorOperacao;
                        con.atualizaSaldo(valorSaldo, cliente.getId());
                        con.InsereExtrato(conta.getNumero(), "Pix", ValorOperacao, conta.getCheque(),
                                conta.getSaldo(),
                                valorSaldo, "Pix Enviado para " + clienteTransfere.getNome());
                        conta.setSaldo(valorSaldo);
                    } else {
                        if (conta.getSaldo() <= 0) {
                            Double valorCheque = (conta.getCheque() - ValorOperacao);
                            con.atualizaSaldo((conta.getSaldo() - ValorOperacao), cliente.getId());
                            con.atualizaCheque(valorCheque, cliente.getId());
                            con.InsereExtrato(conta.getNumero(), "Pix", ValorOperacao, valorCheque,
                                    conta.getSaldo(),
                                    (conta.getSaldo() - ValorOperacao),
                                    "Pix Enviado para " + clienteTransfere.getNome()
                                            + " - Utilização do cheque especial");
                            conta.setSaldo(conta.getSaldo() - ValorOperacao);
                            conta.setCheque(valorCheque);
                        } else {
                            Double valorCheque = (conta.getSaldo() + conta.getCheque()) - ValorOperacao;
                            con.atualizaSaldo((conta.getSaldo() - ValorOperacao), cliente.getId());
                            con.atualizaCheque(valorCheque, cliente.getId());
                            con.InsereExtrato(conta.getNumero(), "Pix", ValorOperacao, valorCheque,
                                    conta.getSaldo(),
                                    (conta.getSaldo() - ValorOperacao),
                                    "Pix Enviado para " + clienteTransfere.getNome()
                                            + " - Utilização do cheque especial");
                            conta.setSaldo(conta.getSaldo() - ValorOperacao);
                            conta.setCheque(valorCheque);
                        }

                    }
                } else {
                    Double valorSaldo = conta.getSaldo() - ValorOperacao;
                    con.atualizaSaldo(valorSaldo, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Pix", ValorOperacao, conta.getCheque(),
                            conta.getSaldo(),
                            valorSaldo, "Pix Enviado para " + clienteTransfere.getNome());
                    conta.setSaldo(valorSaldo);
                }

                ResultSet rsConta = con.getConta(clienteTransfere.getId());
                try {
                    while (rsConta.next()) {
                        Conta contaTransferencia = new Conta(rsConta.getInt("numero"), rsConta.getInt("agencia"), rsConta.getString("operacao"), rsConta.getDouble("saldo"), rsConta.getDouble("cheque"), rsConta.getInt("cliente"), rsConta.getString("senha"));
                        System.out.println(contaTransferencia.getAgencia());
                        if (contaTransferencia.getOperacao().equals("001")) {
                            if (contaTransferencia.getSaldo() < 0) {
                                double valor = (ValorOperacao + contaTransferencia.getCheque());
                                if (valor > 300.0) {
                                    con.atualizaCheque(300, clienteTransfere.getId());
                                    con.atualizaSaldo(valor - 300, clienteTransfere.getId());
                                    con.InsereExtrato(contaTransferencia.getNumero(), "Pix", ValorOperacao,
                                            300.0,
                                            contaTransferencia.getSaldo(), (valor - 300),
                                            "Pix enviado por " + cliente.getNome());
                                    contaTransferencia.setSaldo((valor - 300));
                                    contaTransferencia.setCheque(300);
                                } else {
                                    con.atualizaSaldo(
                                            (contaTransferencia.getSaldo() + (valor - contaTransferencia.getCheque())),
                                            clienteTransfere.getId());
                                    con.atualizaCheque(valor, clienteTransfere.getId());
                                    con.InsereExtrato(contaTransferencia.getNumero(), "Pix", ValorOperacao,
                                            valor,
                                            contaTransferencia.getSaldo(),
                                            (contaTransferencia.getSaldo() + (valor - contaTransferencia.getCheque())),
                                            "Pix enviado por " + cliente.getNome()
                                                    + " - Cobrança de crédito especial");
                                    contaTransferencia.setSaldo(
                                            (contaTransferencia.getSaldo() + (valor - contaTransferencia.getCheque())));
                                    contaTransferencia.setCheque(valor);
                                }
                            } else {
                                double valor = ValorOperacao + contaTransferencia.getSaldo();
                                con.atualizaSaldo(valor, clienteTransfere.getId());
                                con.InsereExtrato(contaTransferencia.getNumero(), "Pix", ValorOperacao,
                                        contaTransferencia.getCheque(), contaTransferencia.getSaldo(), valor,
                                        "Pix enviado por " + cliente.getNome());
                                contaTransferencia.setSaldo(valor);
                            }
                        } else {
                            double valor = ValorOperacao + contaTransferencia.getSaldo();
                            con.atualizaSaldo(valor, clienteTransfere.getId());
                            con.InsereExtrato(contaTransferencia.getNumero(), "Pix", ValorOperacao,
                                    contaTransferencia.getCheque(), contaTransferencia.getSaldo(), valor,
                                    "Pix enviado por " + cliente.getNome());
                            contaTransferencia.setSaldo(valor);
                        }
                    }
                } catch (Exception el) {
                    el.printStackTrace();
                }
            }

        } catch (Exception eb) {
            eb.printStackTrace();
        }
    }

    public static void transfere() {   
        try {
            ResultSet rsCliente = con.getContaTransferencia(TelaOperacao.getNumeroConta(), TelaOperacao.getAgencia(), TelaOperacao.getTipoContaTransferencia());
            
            while (rsCliente.next()) {
                Cliente clienteTransfere = new Cliente(rsCliente.getString("tipo"), rsCliente.getInt("id"), rsCliente.getString("nome"), rsCliente.getString("usuario"), rsCliente.getString("datanascimento"), rsCliente.getString("cpf"), rsCliente.getString("rg"), rsCliente.getString("endereco"), rsCliente.getString("cep"), rsCliente.getString("email"), rsCliente.getString("celular"), rsCliente.getString("senha"));
                System.out.println(clienteTransfere.getNome());
                double ValorOperacao = TelaOperacao.getValorOperacao();
                if (conta.getOperacao().equals("001")) {
                    if (ValorOperacao < conta.getSaldo()) {
                        Double valorSaldo = conta.getSaldo() - ValorOperacao;
                        con.atualizaSaldo(valorSaldo, cliente.getId());
                        con.InsereExtrato(conta.getNumero(), "Transferência", ValorOperacao, conta.getCheque(),
                                conta.getSaldo(),
                                valorSaldo, "Transferido para " + clienteTransfere.getNome());
                        conta.setSaldo(valorSaldo);
                    } else {
                        if (conta.getSaldo() <= 0) {
                            Double valorCheque = (conta.getCheque() - ValorOperacao);
                            con.atualizaSaldo((conta.getSaldo() - ValorOperacao), cliente.getId());
                            con.atualizaCheque(valorCheque, cliente.getId());
                            con.InsereExtrato(conta.getNumero(), "Transferência", ValorOperacao, valorCheque,
                                    conta.getSaldo(),
                                    (conta.getSaldo() - ValorOperacao),
                                    "Transferido para " + clienteTransfere.getNome()
                                            + " - Utilização do cheque especial");
                            conta.setSaldo(conta.getSaldo() - ValorOperacao);
                            conta.setCheque(valorCheque);
                        } else {
                            Double valorCheque = (conta.getSaldo() + conta.getCheque()) - ValorOperacao;
                            con.atualizaSaldo((conta.getSaldo() - ValorOperacao), cliente.getId());
                            con.atualizaCheque(valorCheque, cliente.getId());
                            con.InsereExtrato(conta.getNumero(), "Transferência", ValorOperacao, valorCheque,
                                    conta.getSaldo(),
                                    (conta.getSaldo() - ValorOperacao),
                                    "Transferido para " + clienteTransfere.getNome()
                                            + " - Utilização do cheque especial");
                            conta.setSaldo(conta.getSaldo() - ValorOperacao);
                            conta.setCheque(valorCheque);
                        }

                    }
                } else {
                    Double valorSaldo = conta.getSaldo() - ValorOperacao;
                    con.atualizaSaldo(valorSaldo, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Transferência", ValorOperacao, conta.getCheque(),
                            conta.getSaldo(),
                            valorSaldo, "Transferido para " + clienteTransfere.getNome());
                    conta.setSaldo(valorSaldo);
                }

                ResultSet rsConta = con.getConta(clienteTransfere.getId());
                try {
                    while (rsConta.next()) {
                        Conta contaTransferencia = new Conta(rsConta.getInt("numero"), rsConta.getInt("agencia"), rsConta.getString("operacao"), rsConta.getDouble("saldo"), rsConta.getDouble("cheque"), rsConta.getInt("cliente"), rsConta.getString("senha"));
                        System.out.println(contaTransferencia.getAgencia());
                        if (contaTransferencia.getOperacao().equals("001")) {
                            if (contaTransferencia.getSaldo() < 0) {
                                double valor = (ValorOperacao + contaTransferencia.getCheque());
                                if (valor > 300.0) {
                                    con.atualizaCheque(300, clienteTransfere.getId());
                                    con.atualizaSaldo(valor - 300, clienteTransfere.getId());
                                    con.InsereExtrato(contaTransferencia.getNumero(), "Transferência", ValorOperacao,
                                            300.0,
                                            contaTransferencia.getSaldo(), (valor - 300),
                                            "Transferência enviada por " + cliente.getNome());
                                    contaTransferencia.setSaldo((valor - 300));
                                    contaTransferencia.setCheque(300);
                                } else {
                                    con.atualizaSaldo(
                                            (contaTransferencia.getSaldo() + (valor - contaTransferencia.getCheque())),
                                            clienteTransfere.getId());
                                    con.atualizaCheque(valor, clienteTransfere.getId());
                                    con.InsereExtrato(contaTransferencia.getNumero(), "Transferência", ValorOperacao,
                                            valor,
                                            contaTransferencia.getSaldo(),
                                            (contaTransferencia.getSaldo() + (valor - contaTransferencia.getCheque())),
                                            "Transferência enviada por " + cliente.getNome()
                                                    + " - Cobrança de crédito especial");
                                    contaTransferencia.setSaldo(
                                            (contaTransferencia.getSaldo() + (valor - contaTransferencia.getCheque())));
                                    contaTransferencia.setCheque(valor);
                                }
                            } else {
                                double valor = ValorOperacao + contaTransferencia.getSaldo();
                                con.atualizaSaldo(valor, clienteTransfere.getId());
                                con.InsereExtrato(contaTransferencia.getNumero(), "Transferência", ValorOperacao,
                                        contaTransferencia.getCheque(), contaTransferencia.getSaldo(), valor,
                                        "Transferência enviada por " + cliente.getNome());
                                contaTransferencia.setSaldo(valor);
                            }
                        } else {
                            double valor = ValorOperacao + contaTransferencia.getSaldo();
                            con.atualizaSaldo(valor, clienteTransfere.getId());
                            con.InsereExtrato(contaTransferencia.getNumero(), "Transferência", ValorOperacao,
                                    contaTransferencia.getCheque(), contaTransferencia.getSaldo(), valor,
                                    "Transferência enviada por " + cliente.getNome());
                            contaTransferencia.setSaldo(valor);
                        }
                    }
                } catch (Exception el) {
                    el.printStackTrace();
                }
            }

        } catch (Exception eb) {
            eb.printStackTrace();
        }
    }

    public static void deposita() {
        double ValorOperacao = TelaOperacao.getValorOperacao();
        System.out.println(ValorOperacao);
        if (conta.getOperacao().equals("001")) {
            if (conta.getSaldo() < 0) {
                double valor = (ValorOperacao + conta.getCheque());
                if (valor > 300.0) {
                    con.atualizaCheque(300, cliente.getId());
                    con.atualizaSaldo(valor - 300, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Depósito", ValorOperacao, 300.0, conta.getSaldo(),
                            (valor - 300), " ");
                    conta.setSaldo((valor - 300));
                    conta.setCheque(300);
                } else {
                    con.atualizaSaldo((conta.getSaldo() + (valor - conta.getCheque())), cliente.getId());
                    con.atualizaCheque(valor, cliente.getId());
                    con.InsereExtrato(conta.getNumero(), "Depósito", ValorOperacao, valor, conta.getSaldo(),
                            (conta.getSaldo() + (valor - conta.getCheque())), "Cobrança de crédito especial");
                    conta.setSaldo((conta.getSaldo() + (valor - conta.getCheque())));
                    conta.setCheque(valor);
                }
            } else {
                double valor = ValorOperacao + conta.getSaldo();
                con.atualizaSaldo(valor, cliente.getId());
                con.InsereExtrato(conta.getNumero(), "Depósito", ValorOperacao, conta.getCheque(), conta.getSaldo(),
                        valor, " ");
                conta.setSaldo(valor);
            }
        } else {
            double valor = ValorOperacao + conta.getSaldo();
            con.atualizaSaldo(valor, cliente.getId());
            con.InsereExtrato(conta.getNumero(), "Depósito", ValorOperacao, conta.getCheque(), conta.getSaldo(), valor,
                    " ");
            conta.setSaldo(valor);
        }

    }

    public JPanel showExtrato(int i) {

        ResultSet rs = con.getExtrato(conta.getNumero(), i);
        try {
            while (rs.next()) {
                extrato = new Extrato(rs.getString("datahora"), rs.getString("transacao"), rs.getDouble("valor"),
                        rs.getDouble("saldocheque"), rs.getDouble("saldoanterior"), rs.getDouble("saldoAtualizado"),
                        rs.getString("detalhes"));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        String saldoA;
        String saldoN;
        String cheque;

        if (Double.toString(extrato.getSaldoAnterior()).indexOf(".") == -1
                || Double.toString(extrato.getSaldoAnterior())
                        .length() < (Double.toString(extrato.getSaldoAnterior()).indexOf(".") + 3)) {
            saldoA = Double.toString(extrato.getSaldoAnterior());
        } else {
            saldoA = Double.toString(extrato.getSaldoAnterior()).substring(0,
                    (Double.toString(extrato.getSaldoAnterior()).indexOf(".") + 3));
        }

        if (Double.toString(extrato.getSaldoAtualizado()).indexOf(".") == -1
                || Double.toString(extrato.getSaldoAtualizado())
                        .length() < (Double.toString(extrato.getSaldoAtualizado()).indexOf(".") + 3)) {
            saldoN = Double.toString(extrato.getSaldoAtualizado());
        } else {
            saldoN = Double.toString(extrato.getSaldoAtualizado()).substring(0,
                    (Double.toString(extrato.getSaldoAtualizado()).indexOf(".") + 3));
        }

        if (Double.toString(extrato.getCheque()).indexOf(".") == -1 || Double.toString(extrato.getCheque())
                .length() < (Double.toString(extrato.getCheque()).indexOf(".") + 3)) {
            cheque = Double.toString(extrato.getCheque());
        } else {
            cheque = Double.toString(extrato.getCheque()).substring(0,
                    (Double.toString(extrato.getCheque()).indexOf(".") + 3));
        }

        JLabel data = new JLabel(extrato.getData());
        data.setFont(new Font("Arial", Font.PLAIN, 20));
        data.setBounds(10, 20, 150, 20);
        data.setForeground(Color.black);

        JLabel tipoOperacao = new JLabel(extrato.getTransacao());
        tipoOperacao.setBounds(200, 10, 150, 30);
        tipoOperacao.setFont(new Font("Arial", Font.PLAIN, 30));
        tipoOperacao.setForeground(Color.black);

        JLabel valor = new JLabel("R$ " + extrato.getValor());
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

        if (conta.getOperacao().equals("013")) {
            labelCheque.setVisible(false);
            Saldocheque.setVisible(false);
        }

        JLabel detalhes = new JLabel(extrato.getDetalhes());
        detalhes.setFont(new Font("Arial", Font.PLAIN, 20));
        detalhes.setBounds(10, 150, 400, 20);
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

    public void setCliente(Cliente Cliente) {
        TelaPrincipal.cliente = Cliente;
    }

    public void setConta(Conta Conta) {
        TelaPrincipal.conta = Conta;
    }
}

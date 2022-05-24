import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ProgressBarUI;

import java.awt.*;

import java.awt.event.ActionEvent;


public class App {
    public static void main(String[] args) {
        Color corVerde = new Color(34,92,100);
        //Color corVerde = new Color(10,150,130);
        Color corAzul = new Color(34,92,150);
        Color corAmarelo = new Color(239,209,50);
        //Color corAzul = new Color(30,144,200);
        Color corCinza = new Color(250, 250, 250);
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
        JButton sair = botoes.botao("Sair >", corVerde, Color.white, 410, 0, 100, 50, 25, 0, 2, 0, 0, Color.white);
        JLabel erroNome = textos.textosInvisiveis("*Nome invalido*", 250, 180, 300, 30, 15, Color.red);
        JLabel erroEndereco = textos.textosInvisiveis("*Endereço invalido*", 250, 250, 300, 30, 15, Color.red);
        JLabel erroCPF = textos.textosInvisiveis("*CPF invalido*", 250, 320, 300, 30, 15, Color.red);
        JLabel erroRG = textos.textosInvisiveis("*RG invalido*", 250, 390, 300, 30, 15, Color.red);
        JLabel erroEmail = textos.textosInvisiveis("*Email invalido*", 250, 460, 300, 30, 15, Color.red);
        JLabel erroSenha = textos.textosInvisiveis("*Senha invalida*", 250, 530, 300, 30, 15, Color.red);
        JLabel showSaldo = textos.textos(" ", 400, 50, 110, 50, 25, Color.black, corAmarelo);
        JPanel Entrar = new JPanel();
        JPanel Cadastrar = new JPanel();
        JPanel tpConta = new JPanel();
        JPanel Inicio = new JPanel();
        JPanel Carregamento = new JPanel();

        

        JTextField usuario = campos.campo(100, 230, 300, 30, 15, Color.white, Color.black, 1, 2, 2, 1, Color.black);

        JPasswordField senha = new JPasswordField();
        senha.setBounds(100, 300, 300, 30);
        senha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        senha.setFont(new Font("Arial", Font.PLAIN, 15));
        senha.setBackground(Color.white);
        senha.setForeground(Color.black);
        

        JTextField getNome = new JTextField();
        getNome.setBounds(100, 210, 300, 30);
        getNome.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JTextField getEndereco = new JTextField();
        getEndereco.setBounds(100, 280, 300, 30);
        getEndereco.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JTextField getCPF = new JTextField();
        getCPF.setBounds(100, 350, 300, 30);
        getCPF.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JTextField getRG = new JTextField();
        getRG.setBounds(100, 420, 300, 30);
        getRG.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JTextField getEmail = new JTextField();
        getEmail.setBounds(100, 490, 300, 30);
        getEmail.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));
        JPasswordField getSenha = new JPasswordField();
        getSenha.setBounds(100, 560, 300, 30);
        getSenha.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.black));


        JRadioButton tituloCorrente = new JRadioButton("Conta Corrente");
        tituloCorrente.setBounds(50, 180, 400, 30);
        tituloCorrente.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloCorrente.setBackground(corVerde);
        tituloCorrente.setForeground(Color.white);
        JTextArea descrCorrente = new JTextArea("Movimente seu dinheiro da forma como desejar, com direito a um cheque especial para utilizar quando quiser");
        descrCorrente.setBounds(50, 210, 400, 100);
        descrCorrente.setFont(new Font("Arial", Font.PLAIN, 20));
        descrCorrente.setMargin(new java.awt.Insets(5, 5, 5, 5));
        descrCorrente.setForeground(corVerde);
        descrCorrente.setBackground(corCinza);
        descrCorrente.setEditable(false);
        descrCorrente.setLineWrap(true);
        JRadioButton tituloPoupanca = new JRadioButton("Conta Poupança");
        tituloPoupanca.setBounds(50, 310, 400, 30);
        tituloPoupanca.setFont(new Font("Arial", Font.PLAIN, 20));
        tituloPoupanca.setForeground(Color.white);
        tituloPoupanca.setBackground(corVerde);
        JTextArea descrPoupanca = new JTextArea("Ideal para quem deseja guardar dinheiro, com rendimento de 6,17% ao ano");
        descrPoupanca.setBounds(50, 340, 400, 100);
        descrPoupanca.setMargin(new java.awt.Insets(5, 5, 5, 5));descrPoupanca.setFont(new Font("Arial", Font.PLAIN, 20));
        descrPoupanca.setForeground(corVerde);
        descrPoupanca.setBackground(corCinza);
        descrPoupanca.setEditable(false);
        descrPoupanca.setLineWrap(true);

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
        Cadastrar.add(textos.textos("Endereço", 100, 250, 300, 30, 20, corVerde));
        Cadastrar.add(getEndereco);
        Cadastrar.add(textos.textos("CPF", 100, 320, 300, 30, 20, corVerde));
        Cadastrar.add(getCPF);
        Cadastrar.add(getRG);
        Cadastrar.add(textos.textos("RG", 100, 390, 300, 30, 20, corVerde));
        Cadastrar.add(getEmail);
        Cadastrar.add(textos.textos("Email", 100, 460, 300, 30, 20, corVerde));
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
        tpConta.add(textos.textos("Selecione o tipo de conta", 50, 0, 470, 50, 30, Color.white, corVerde));
        

        
        Inicio.setBackground(Color.white);
        Inicio.setLayout(null);
        
        Inicio.setVisible(true);
        Inicio.add(sair);
        Inicio.add(textos.textos(" Bem vindo __________", 0, 0, 410, 50, 25, Color.white, corVerde));
        Inicio.add(textos.textos(" Saldo:", 0, 50, 400, 50, 25, Color.black, corAmarelo));
        Inicio.add(showSaldo);
        JProgressBar barra = new JProgressBar(0, 100);

        barra.setBounds(200, 200, 300, 30);
        
        Carregamento.setBackground(corAzul);
        Carregamento.setLayout(null);
        
        Carregamento.setVisible(false);
        Carregamento.add(barra);

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

        registrar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(getNome.getText().isEmpty()){
                    erroNome.setVisible(true);
                }else erroNome.setVisible(false);
                if(getEndereco.getText().isEmpty()){
                    erroEndereco.setVisible(true);
                }else erroEndereco.setVisible(false);
                if(getRG.getText().isEmpty()){
                    erroRG.setVisible(true);
                }else erroRG.setVisible(false);
                if(getCPF.getText().isEmpty()){
                    erroCPF.setVisible(true);
                }else erroCPF.setVisible(false);
                if(getEmail.getText().isEmpty()){
                    erroEmail.setVisible(true);
                }else erroEmail.setVisible(false);
                if(getSenha.getText().isEmpty()){
                    erroSenha.setVisible(true);
                }else erroSenha.setVisible(false);
                if(getNome.getText().isEmpty() || getEndereco.getText().isEmpty() || getRG.getText().isEmpty() || getCPF.getText().isEmpty() || getEmail.getText().isEmpty() || getSenha.getText().isEmpty()){
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

        
        finalizar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tituloCorrente.isSelected() || tituloPoupanca.isSelected()){
                    Cliente cl = new Cliente(getNome.getText(), getRG.getText(), getCPF.getText(), getEndereco.getText(), getEmail.getText(), getSenha.getText());
                    System.out.println(cl.toString());
                    if(tituloCorrente.isSelected()){
                        Corrente cc = cl.criaCc();
                    }else{
                        Poupanca cp = cl.criaCp();
                    } 

                    tpConta.setVisible(false);
                    Inicio.setVisible(true);
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

        Container Pane = janela.getContentPane();
        Pane.add(Carregamento);
        Pane.add(Entrar);
        Pane.add(Cadastrar);
        Pane.add(tpConta);
        Pane.add(Inicio);
    }

    
}
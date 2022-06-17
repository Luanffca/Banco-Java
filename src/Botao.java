import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;

public class Botao {
    Color corAzul = new Color(34, 92, 150);
    Color corAmarelo = new Color(254, 170, 58);

    public JButton botao(Color corFundo, int X, int Y, int W, int H) {
        JButton bt = new JButton();
        bt.setBackground(corFundo);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        return bt;
    }

    public JButton botao(String texto, Color corFundo, Color corTexto, int X, int Y, int W, int H) {
        JButton bt = new JButton(texto);
        bt.setBackground(corFundo);
        bt.setForeground(corTexto);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        return bt;
    }

    public JButton botao(String texto, Color corFundo, Color corTexto, int X, int Y, int W, int H, int F) {
        JButton bt = new JButton(texto);
        bt.setBackground(corFundo);
        bt.setForeground(corTexto);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        bt.setFont(new Font("Arial", Font.PLAIN, F));
        return bt;
    }

    public JButton botao(String texto, Color corFundo, Color corTexto, int X, int Y, int W, int H, int F, int T, int L,
            int B, int R, Color corBorda) {
        JButton bt = new JButton(texto);
        bt.setBackground(corFundo);
        bt.setForeground(corTexto);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(T, L, B, R, corBorda));
        bt.setFont(new Font("Arial", Font.PLAIN, F));
        return bt;
    }

    public JButton botao(Color corFundo, int X, int Y, int W, int H, int T, int L, int B, int R, Color corBorda) {
        JButton bt = new JButton();
        bt.setBackground(corFundo);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(T, L, B, R, corBorda));
        return bt;
    }

    public JButton botaoVoltar(JPanel Anterior, JPanel Proxima) {
        JButton bt = new JButton(" <");
        bt.setBackground(corAmarelo);
        bt.setForeground(corAzul);
        bt.setBounds(0, 0, 50, 50);
        bt.setFont(new Font("Arial", Font.PLAIN, 30));
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Anterior.setVisible(false);
                Proxima.setVisible(true);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                bt.setText("<");
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                bt.setText(" <");
            }
        });
        return bt;
    }

    public JButton botaoInicial(String texto, int X, int Y, JPanel Anterior, JPanel Proxima, JLabel operacao) {
        JButton bt = new JButton(texto);
        bt.setBackground(new Color(34, 92, 150));
        bt.setForeground(Color.white);
        bt.setBounds(X, Y, 350, 50);
        bt.setFont(new Font("Arial", Font.PLAIN, 40));
        bt.setHorizontalAlignment(SwingConstants.LEFT);
        bt.setBorder(BorderFactory.createMatteBorder(1, 20, 5, 1, Color.white));
        bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                operacao.setText(bt.getText());
                Anterior.setVisible(false);
                Proxima.setVisible(true);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                bt.setText(" " + texto);
                bt.setForeground(corAmarelo);
                bt.setBorder(BorderFactory.createMatteBorder(1, 3, 5, 1, corAmarelo));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                bt.setText(texto);
                bt.setBorder(BorderFactory.createMatteBorder(1, 20, 5, 1, Color.white));
                bt.setForeground(Color.white);
            }
        });
        return bt;
    }
}

import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Botao{
    
    public JButton botao(Color corFundo, int X, int Y, int W, int H){
        JButton bt = new JButton();
        bt.setBackground(corFundo);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        return bt;
    }
    public JButton botao(String texto, Color corFundo, Color corTexto, int X, int Y, int W, int H){
        JButton bt = new JButton(texto);
        bt.setBackground(corFundo);
        bt.setForeground(corTexto);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        return bt;
    }
    public JButton botao(String texto, Color corFundo, Color corTexto, int X, int Y, int W, int H, int F){
        JButton bt = new JButton(texto);
        bt.setBackground(corFundo);
        bt.setForeground(corTexto);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        bt.setFont(new Font("Arial", Font.PLAIN, F));
        return bt;
    }

    public JButton botao(String texto, Color corFundo, Color corTexto, int X, int Y, int W, int H, int F, int T, int L, int B, int R, Color corBorda){
        JButton bt = new JButton(texto);
        bt.setBackground(corFundo);
        bt.setForeground(corTexto);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(T, L, B, R, corBorda));
        bt.setFont(new Font("Arial", Font.PLAIN, F));
        return bt;
    }

    public JButton botao(Color corFundo, int X, int Y, int W, int H, int T, int L, int B, int R, Color corBorda){
        JButton bt = new JButton();
        bt.setBackground(corFundo);
        bt.setBounds(X, Y, W, H);
        bt.setBorder(BorderFactory.createMatteBorder(T, L, B, R, corBorda));
        return bt;
    }

    public JButton botaoVoltar(JPanel Anterior, JPanel proxima){
        JButton bt = new JButton(" <");
        bt.setBackground(new Color(254,170,58));
        bt.setForeground(Color.black);
        bt.setBounds(0, 0, 50, 50);
        bt.setFont(new Font("Arial", Font.PLAIN, 30));
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.white));
        bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Anterior.setVisible(false);
                proxima.setVisible(true);
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
    
    public JButton botaoInicial(String texto, int X, int Y){
        JButton bt = new JButton(texto);
        bt.setBackground(new Color(34,92,150));
        bt.setForeground(Color.white);
        bt.setBounds(X, Y, 350, 50);
        bt.setFont(new Font("Arial", Font.PLAIN, 40));
        bt.setHorizontalAlignment(SwingConstants.LEFT);
        bt.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.white));
        return bt;
    }
}

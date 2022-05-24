import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;

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
}

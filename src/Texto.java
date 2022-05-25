import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
public class Texto {
    
    public JLabel textosInvisiveis(String texto, int X, int Y, int W, int H, int F, Color corTexto){
        JLabel txt = new JLabel(texto);
        txt.setBounds(X, Y, W, H);
        txt.setFont(new Font("Arial", Font.PLAIN, F));
        txt.setForeground(corTexto);
        txt.setVisible(false);
        return txt;
    }
    public JLabel textosAlinhados(String texto, int X, int Y, int W, int H, int F, Color corTexto){
        JLabel txt = new JLabel(texto);
        txt.setBounds(X, Y, W, H);
        txt.setFont(new Font("Arial", Font.PLAIN, F));
        txt.setHorizontalAlignment(SwingConstants.CENTER);  
        txt.setForeground(corTexto);
        return txt;
    }
    public JLabel textos(int X, int Y, int W, int H, Color corFundo){
        JLabel txt = new JLabel();
        txt.setBounds(X, Y, W, H);
        txt.setOpaque(true);
        txt.setBackground(corFundo);
        return txt;
    }
    public JLabel textos(int X, int Y, int W, int H, Color corFundo, int T, int L, int B, int R, Color corBorda){
        JLabel txt = new JLabel();
        txt.setBounds(X, Y, W, H);
        txt.setOpaque(true);
        txt.setBorder(BorderFactory.createMatteBorder(T, L, B, R, corBorda));
        txt.setBackground(corFundo);
        return txt;
    }
    public JLabel textos(String texto, int X, int Y, int W, int H, int F, Color corTexto, Color corFundo, int T, int L, int B, int R, Color corBorda){
        JLabel txt = new JLabel(texto);
        txt.setBounds(X, Y, W, H);
        txt.setOpaque(true);
        txt.setFont(new Font("Arial", Font.PLAIN, F));
        txt.setBorder(BorderFactory.createMatteBorder(T, L, B, R, corBorda));
        txt.setForeground(corTexto);
        txt.setBackground(corFundo);
        return txt;
    }
    public JLabel textos(String texto, int X, int Y, int W, int H, int F, Color corTexto){
        JLabel txt = new JLabel(texto);
        txt.setBounds(X, Y, W, H);
        
        txt.setFont(new Font("Arial", Font.PLAIN, F));
        txt.setForeground(corTexto);
        return txt;
    }
    public JLabel textos(String texto, int X, int Y, int W, int H, int F, Color corTexto, Color corFundo){
        JLabel txt = new JLabel(texto);
        txt.setBounds(X, Y, W, H);
        txt.setFont(new Font("Arial", Font.PLAIN, F));
        txt.setForeground(corTexto);
        txt.setOpaque(true);
        txt.setBackground(corFundo);
        return txt;
    }
}

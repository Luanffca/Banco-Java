import javax.swing.BorderFactory;

import java.awt.*;

import javax.swing.*;

public class campoTxt {
    public JTextField campo(int X, int Y, int W, int H, int F, Color corFundo, Color corTexto, Color corBorda){
        JTextField cmp = new JTextField();
        cmp.setBounds(X, Y, W, H);
        cmp.setFont(new Font("Arial", Font.PLAIN, F));
        cmp.setBackground(corFundo);
        cmp.setForeground(corTexto);
        return cmp;
    }
    public JTextField campo(int X, int Y, int W, int H, int F, Color corFundo, Color corTexto, int T, int L, int B, int R, Color corBorda){
        JTextField cmp = new JTextField();
        cmp.setBounds(X, Y, W, H);
        cmp.setFont(new Font("Arial", Font.PLAIN, F));
        cmp.setBackground(corFundo);
        cmp.setForeground(corTexto);
        cmp.setBorder(BorderFactory.createMatteBorder(T, L, B, R, corBorda));
        return cmp;
    }
}

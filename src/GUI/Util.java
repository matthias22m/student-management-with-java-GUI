package GUI;

import javax.swing.*;
import java.awt.*;

public class Util {
    static JButton button(int x,int y,int width,int height, String title, JPanel topPanel){
        JButton btn = new JButton(title);
        btn.setBounds(x,y,width,height);
        topPanel.add(btn);
        return btn;
    }

    static JLabel label(int x, int y, int width, int height, String title, JPanel topPanel){
        JLabel lbl = new JLabel(title);
        lbl.setBounds(x,y,width,height);
        topPanel.add(lbl);
        return lbl;
    }
    static JTextField Tfield(int x,int y,int width,int height, JPanel topPanel){
        JTextField tfld = new JTextField();
        tfld.setBounds(x,y,width,height);
        topPanel.add(tfld);
        tfld.setBackground(Color.decode("#BBBABA"));
        tfld.setForeground(Color.white);
        tfld.setFont(new Font("",Font.BOLD,14));
        return tfld;
    }

    static JPasswordField Pfield(int x,int y,int width,int height, JPanel topPanel){
        JPasswordField pfld = new JPasswordField();
        pfld.setBounds(x,y,width,height);
        topPanel.add(pfld);
        pfld.setBackground(Color.decode("#BBBABA"));
        pfld.setForeground(Color.white);
        pfld.setFont(new Font("",Font.BOLD,14));
        return pfld;
    }

    static JRadioButton radioButton(int x,int y,int width,int height,ButtonGroup btngroup,String title, JPanel topPanel){
        JRadioButton radioBtn = new JRadioButton(title);
        radioBtn.setBounds(x,y,width,height);
        btngroup.add(radioBtn);
        topPanel.add(radioBtn);
        return radioBtn;
    }
}

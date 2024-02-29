import javax.swing.*;
import GUI.*;
import Objects.Course;

import java.awt.*;
import java.io.IOException;


public class Main extends JFrame{
    static JFrame frame = new JFrame("Student Grade Management System");
    JPanel cardPanel;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        frame.setBounds(100,100,1000,700);

//        Login pan = new Login();
        frame.setVisible(true);
//        JPanel n = pan.LoginPage(frame);

        Register pan2 = new Register();
        JPanel n2 = pan2.RegisterPage(frame);



        frame.getContentPane().add(n2);

//        frame.getContentPane().add(n);
        frame.setLayout(null);

        frame.setResizable(false);




    }
}
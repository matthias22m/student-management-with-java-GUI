package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Login{


    public Login() {
    }

    public JPanel LoginPage(JFrame mainFrame){
        JPanel panel = new JPanel();
        panel.setSize(mainFrame.getSize());
        mainFrame.setTitle("Login page");
        panel.setLayout(null);
        panel.setBackground(Color.decode("#D9D9D9"));

        JLabel login,username,password,role,d_hv_acc;

        login = Util.label(425,20,150,80,"Login",panel);
        login.setFont(new Font("",Font.BOLD,40));

        username = Util.label(250,100,170,80,"Username :",panel);
        username.setFont(new Font("",Font.BOLD,25));

        password = Util.label(250,200,170,80,"Password :",panel);
        password.setFont(new Font("",Font.BOLD,25));

        role = Util.label(250,300,150,80,"Role :",panel);
        role.setFont(new Font("",Font.BOLD,25));

        d_hv_acc = Util.label(270,400,250,80,"Doesn't have an account?",panel);
        d_hv_acc.setFont(new Font("",Font.BOLD,20));

        JTextField idField;


        idField = Util.Tfield(420,130,170,25,panel);
        idField.setFont(new Font("",Font.BOLD,12));
        idField.setBackground(Color.decode("#BBBABA"));
        idField.setForeground(Color.white);

        JPasswordField passField;

        passField = Util.Pfield(420,230,170,25,panel);
        passField.setFont(new Font("",Font.BOLD,12));
        passField.setBackground(Color.decode("#BBBABA"));
        passField.setForeground(Color.white);



        JRadioButton studRadio, instructorRadio;


        ButtonGroup bg = new ButtonGroup();
        studRadio = Util.radioButton(350,330,100,20,bg ,"Student",panel);
        studRadio.setBackground(panel.getBackground());
        studRadio.setFont(new Font("",Font.BOLD,15));

        instructorRadio= Util.radioButton(500,330,100,20,bg ,"Instructor",panel);
        instructorRadio.setBackground(panel.getBackground());
        instructorRadio.setFont(new Font("",Font.BOLD,15));

        JButton loginBtn,registerBtn;
        loginBtn = Util.button(450,530,100,30,"Login",panel);
        loginBtn.setBackground(Color.decode("#316988"));
        loginBtn.setFont(new Font("",Font.BOLD,15));
        loginBtn.setForeground(Color.white);
        loginBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String Id = idField.getText();
                String pass = passField.getText();
                if (    idField.getText().isEmpty()||
                        passField.getText().isEmpty()||
                        !(studRadio.isSelected()|| instructorRadio.isSelected())
                ){
                    JOptionPane.showMessageDialog(null,"Input all information");
                }else{
                    int x=0;
                    if(studRadio.isSelected()) x=1;
                    if(instructorRadio.isSelected()) x=2;
                    if (x == 1) {
                        boolean y = checkAndLogin(Id, pass);
                        if (y) {
                            JOptionPane.showMessageDialog(null, "Login successfully");
                            try {
                                mainFrame.setVisible(false);
                                new StudMain(Id);
                            } catch (IOException | ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "invalid information");
                        }
                    } else if (x==2) {
                        boolean y = checkAndLogin(Id, pass,1);
                        if (y) {
                            JOptionPane.showMessageDialog(null, "Login successfully");
                            try {
                                mainFrame.setVisible(false);
                                new InstructMain(Id);
                            } catch (IOException | ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "invalid information");
                        }
                    }
                }
            }
        });


        registerBtn = Util.button(520,427,100,30,"Register",panel);
        registerBtn.setBackground(panel.getBackground());
        registerBtn.setFont(new Font("",Font.BOLD,15));
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                Register obj =new Register();
                JPanel n = obj.RegisterPage(mainFrame);
                mainFrame.getContentPane().add(n);
            }
        });

        panel.setVisible(true);
        return panel;
    }


    public boolean checkAndLogin(String username,String password,int t){
        boolean bool = false;
        try {
            FileReader fin = new FileReader("InstructPass.txt");
            BufferedReader bin = new BufferedReader(fin);
            String line;
            String arr[]={};
            while ((line= bin.readLine())!= null){
                String data[] = line.split(",");
                arr = Arrays.copyOf(data, data.length);
                if (Objects.equals(arr[0], username)) {
                    if (Objects.equals(arr[1], password)) {
                        bool=true;
                        break;
                    }
                }
            }


        }catch (IOException e){
            e.printStackTrace();
        }
        return bool;
    }

    public boolean checkAndLogin(String username,String password){
        boolean bool = false;
        try {
            FileReader fin = new FileReader("StudPass.txt");
            BufferedReader bin = new BufferedReader(fin);
            String line;
            String arr[]={};
            while ((line= bin.readLine())!= null){
                String data[] = line.split(",");
                arr = Arrays.copyOf(data, data.length);
                if (Objects.equals(arr[0], username)) {
                    if (Objects.equals(arr[1], password)) {
                        bool=true;
                        break;
                    }
                }
            }


        }catch (IOException e){
            e.printStackTrace();
        }
        return bool;
    }

}
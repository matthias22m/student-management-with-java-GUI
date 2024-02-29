package GUI;

import Objects.Instructor;
import Objects.SaveToFile;
import Objects.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Register extends Login {
    String id;

    public JPanel RegisterPage(JFrame mainFrame){
        JPanel panel = new JPanel();
        panel.setSize(mainFrame.getSize());
        mainFrame.setTitle("Register page");
        panel.setLayout(null);
        panel.setBackground(Color.decode("#D9D9D9"));
        panel.setVisible(true);

        JLabel register,fullName,idNumber,phoneNumber, email,newPass,conirmPass,role,hv_acc;

        register = Util.label(425,20,150,80,"Register",panel);
        register.setFont(new Font("",Font.BOLD,30));

        fullName = Util.label(250,80,150,60,"Full Name :",panel);
        fullName.setFont(new Font("",Font.BOLD,20));

        idNumber = Util.label(250,130,150,60,"ID number :",panel);
        idNumber.setFont(new Font("",Font.BOLD,20));

        phoneNumber = Util.label(203,180,160,60,"Phone Number :",panel);
        phoneNumber.setFont(new Font("",Font.BOLD,20));

        email = Util.label(203,230,150,60,"Email Address :",panel);
        email.setFont(new Font("",Font.BOLD,20));

        newPass = Util.label(203,280,160,60,"New Password :",panel);
        newPass.setFont(new Font("",Font.BOLD,20));

        conirmPass = Util.label(170,330,190,60,"Confirm Password :",panel);
        conirmPass.setFont(new Font("",Font.BOLD,20));

        role = Util.label(300,380,150,80,"Role :",panel);
        role.setFont(new Font("",Font.BOLD,20));

        hv_acc = Util.label(270,430,250,80,"I already have an account?",panel);
        hv_acc.setFont(new Font("",Font.BOLD,20));

        JTextField nameField, idField,phoneField,emailField;

        nameField = Util.Tfield(400,100,200,25,panel);

        idField = Util.Tfield(400,150,200,25,panel);

        phoneField = Util.Tfield(400,200,200,25,panel);

        emailField = Util.Tfield(400,250,200,25,panel);

        JPasswordField newPassField,confirmField;

        newPassField = Util.Pfield(400,300,200,25,panel);

        confirmField = Util.Pfield(400,350,200,25,panel);



        JRadioButton studRadio, instructorRadio;

        ButtonGroup bg = new ButtonGroup();
        studRadio = Util.radioButton(370,410,100,20,bg ,"Student",panel);
        studRadio.setBackground(panel.getBackground());
        studRadio.setFont(new Font("",Font.BOLD,15));

        instructorRadio = Util.radioButton(520,410,100,20,bg ,"Instructor",panel);
        instructorRadio.setBackground(panel.getBackground());
        instructorRadio.setFont(new Font("",Font.BOLD,15));



        JButton loginBtn,registerBtn;
        registerBtn = Util.button(450,530,100,30,"Register",panel);
        registerBtn.setBackground(Color.decode("#316988"));
        registerBtn.setFont(new Font("",Font.BOLD,15));
        registerBtn.setForeground(Color.white);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int x=0;
                if (emailField.getText().isEmpty()||
                        idField.getText().isEmpty()||
                        nameField.getText().isEmpty()||
                        phoneField.getText().isEmpty()||
                        fullName.getText().isEmpty()||
                        newPassField.getText().isEmpty()||
                        !(studRadio.isSelected()||
                        instructorRadio.isSelected())
                ){
                    JOptionPane.showMessageDialog(null,"Input all information");
                }else {

                    if(studRadio.isSelected()) x=1;
                    if(instructorRadio.isSelected()) x=2;

                    if (!(emailField.getText().contains("@"))) {
                        JOptionPane.showMessageDialog(null, "email must contain '@' character ");
                    }else if(SaveToFile.isRepeated(x,idField.getText())) {
                        JOptionPane.showMessageDialog(null, "Id already exists");
                    }else {
                        if(!(newPassField.getText().equals(confirmField.getText()))){
                            JOptionPane.showMessageDialog(null,"Password mismatch");
                        }else {


                            Instructor instructor = new Instructor();
                            Instructor[] instructors;
                            Student student=new Student();
                            Student[] students;

                            if (x == 1) {
                                student.id = idField.getText();
                                student.address = emailField.getText();
                                student.contact = phoneField.getText();
                                student.fullName = nameField.getText();

                                File f = new File("Student.txt");
                                if(f.exists()){
                                    try {
                                        students = SaveToFile.ReadFromFile();
                                        students = Arrays.copyOf(students, students.length + 1);
                                        students[students.length - 1] = student;
                                        new SaveToFile(students,null);

                                    } catch (IOException | ClassNotFoundException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }else {
                                    students = new Student[0];
                                    students = Arrays.copyOf(students, students.length + 1);
                                    students[students.length - 1] = student;

                                    new SaveToFile(students,null);
                                }
                            }

                            if (x == 2) {
                                instructor.id = idField.getText();
                                instructor.address = emailField.getText();
                                instructor.contact = phoneField.getText();
                                instructor.fullName = nameField.getText();

                                File f2 = new File("Instructor.txt");
                                if(f2.exists()){
                                    try {
                                        instructors = SaveToFile.ReadFromFile(x);
                                        instructors = Arrays.copyOf(instructors, instructors.length + 1);
                                        instructors[instructors.length - 1] = instructor;

                                        new SaveToFile(instructors);

                                    } catch (IOException | ClassNotFoundException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }else {
                                    instructors = new Instructor[0];
                                    instructors = Arrays.copyOf(instructors, instructors.length + 1);
                                    instructors[instructors.length - 1] = instructor;

                                    new SaveToFile(instructors);
                                }
                            }


                            try {
                                SaveToFile.auth(idField.getText(),newPassField.getText(),x);  //Save password and id
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            id=idField.getText();

                            JOptionPane.showMessageDialog(null,"Account created Successfully!");
                            panel.setVisible(false);
                            Login obj =new Login();
                            JPanel n = obj.LoginPage(mainFrame);
                            mainFrame.getContentPane().add(n);

                        }
                    }
                }

            }
        });

        loginBtn = Util.button(520,460,100,30,"Login",panel);
        loginBtn.setBackground(panel.getBackground());
        loginBtn.setFont(new Font("",Font.BOLD,15));

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                Login obj =new Login();
                JPanel n = obj.LoginPage(mainFrame);
                mainFrame.getContentPane().add(n);
            }
        });

        return panel;
    }
    public String getId(){
        return this.id;
    }

}

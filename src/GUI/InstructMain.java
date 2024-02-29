package GUI;

import Objects.Course;
import Objects.Instructor;
import Objects.SaveToFile;
import Objects.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

import static Objects.SaveToFile.ReadFromFile;
import static Objects.SaveToFile.searchObject;

public class InstructMain {
    public InstructMain(String id) throws IOException, ClassNotFoundException {
        JFrame frame = new JFrame("Instructor profile");
        frame.setSize(1000,700);


        JPanel Profile = new JPanel();
        Profile.setSize(frame.getSize());



        Profile.setBackground(Color.decode("#D9D9D9"));

        DefaultTableModel tableModel = new DefaultTableModel( new Object[] { "Student ID", "Student Name", "Email" }, 0);
        JTable courseTable = new JTable(tableModel);

        String[] arr ={"","",""};


        Instructor instructor = searchObject(id,1);

        JLabel nameLable,name , emailLable,email;

        nameLable = Util.label(100,100,100,80,"Name : ",Profile);
        name = Util.label(250,100,100,80, instructor.fullName, Profile);

        emailLable = Util.label(100,200,100,80,"Email : ",Profile);
        email = Util.label(250,200,100,80,instructor.address,Profile);

        Student[] students = SaveToFile.ReadFromFile();
        for (Student student: students) {

            arr[0] = student.id;
            arr[1] = student.fullName;
            arr[2] = student.address;
            tableModel.addRow(arr);
        }
        JScrollPane scrollPane = new JScrollPane(courseTable);
//        scrollPane.add(courseTable);
        scrollPane.setBounds(500,50,500,500);
        Profile.add(scrollPane);



        Profile.setVisible(true);
        frame.add(Profile);
        frame.setVisible(true);
    }
}

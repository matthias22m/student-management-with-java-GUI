package GUI;

import Objects.Course;
import Objects.SaveToFile;
import Objects.Student;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class StudMain extends JFrame {
    public StudMain(String id) throws IOException, ClassNotFoundException {
        JFrame frame = new JFrame("Student profile");
        frame.setSize(1000,700);


        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel courses = new JPanel();
        courses.setBackground(Color.decode("#D9D9D9"));
        tabbedPane.addTab("Cources", courses);
        Student studentCurrent = SaveToFile.searchObject(id);

        JLabel nameLable,name , emailLable,email;

        nameLable = Util.label(100,100,100,80,"Name : ",courses);
        name = Util.label(250,100,100,80,studentCurrent.fullName,courses);

        emailLable = Util.label(100,200,100,80,"Email : ",courses);
        email = Util.label(250,200,100,80,studentCurrent.address,courses);

        DefaultTableModel tableModel = new DefaultTableModel( new Object[] { "Course name", "CreditHour", "Instructor" }, 0);
        JTable courseTable = new JTable(tableModel);

        String[] arr ={"","",""};

        Student student = SaveToFile.searchObject(id);
        for (Course course: student.courses) {
            arr[0] = course.courseName;
            arr[1] = Integer.toString(course.crHr);
            arr[2] = course.instructor;
            tableModel.addRow(arr);
        }
        JScrollPane scrollPane = new JScrollPane(courseTable);
//        scrollPane.add(courseTable);
        scrollPane.setBounds(500,50,500,500);
        courses.add(scrollPane);



        courses.setVisible(true);


        JPanel grade = new JPanel();
        grade.setBackground(Color.decode("#D9D9D9"));

        JLabel gradeLAble = Util.label(500,300 ,100,80,"Grade ",grade);
        gradeLAble.setFont(new Font("",Font.BOLD,20));

        JLabel grade1 = Util.label(600,300 ,100,80,"3.47",grade);
        grade1.setFont(new Font("",Font.BOLD,20));

        tabbedPane.addTab("Grade", grade);


        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }


}

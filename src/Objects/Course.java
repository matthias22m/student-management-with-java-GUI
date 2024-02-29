package Objects;
import java.util.Arrays;
import java.io.*;

public class Course implements Serializable{
    public String grade,instructor,courseName;
    public int crHr;


//    Read course from file
    public static Course[] readFromFile(){

        Course[] courses = new Course[0];
        try {
            FileReader fin= new FileReader("courses.txt");
            BufferedReader bin = new BufferedReader(fin);
            String line, arr[];
            String[][] co={} ;


            while ((line = bin.readLine()) != null) {
                String data[] = line.split(",");
                co = Arrays.copyOf(co, co.length + 1);
                co[co.length - 1] = data;
            }
            bin.close();
            fin.close();

            for (int i = 0; i < co.length-3; i++) {
                Course course = new Course();
                course.courseName = co[i][0];
                course.crHr = Integer.valueOf(co[i][1]);
                course.instructor = co[i][2];
                courses = Arrays.copyOf(courses, courses.length + 1);
                courses[courses.length - 1] = course;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return courses;
    }

}

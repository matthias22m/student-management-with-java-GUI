package Objects;
import java.io.*;
import java.net.Authenticator;

public class SaveToFile{
    public SaveToFile(Student[] student, Course[] course) {
        try {
            FileOutputStream fout = new FileOutputStream("Student.txt");
            BufferedOutputStream bout =new BufferedOutputStream(fout);
            ObjectOutputStream out = new ObjectOutputStream(bout);

            out.writeObject(student);

            out.close();
            bout.flush();
            bout.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SaveToFile(Instructor[] instructors) {
        try {
            FileOutputStream fout = new FileOutputStream("Instructor.txt");
            BufferedOutputStream bout =new BufferedOutputStream(fout);
            ObjectOutputStream out = new ObjectOutputStream(bout);

            out.writeObject(instructors);

            out.close();
            bout.flush();
            bout.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Student[] allStudents() throws IOException, ClassNotFoundException {
        Student[] objects = ReadFromFile();

        return objects;
    }
    public static Student searchObject(String id) throws IOException, ClassNotFoundException {
        Student[] objects = ReadFromFile();
        Student object=null;
        int x;
        for (Student obj: objects) {
            if (id.equals(obj.id)) object = obj;
        }
        return object;
    }
    public static Instructor searchObject(String id,int t) throws IOException, ClassNotFoundException {
        Instructor[] objects = ReadFromFile(t);
        Instructor object=null;
        int x;
        for (Instructor obj: objects) {
            if (id.equals(obj.id)) object = obj;
        }
        return object;
    }
   public static Student[] ReadFromFile() throws IOException, ClassNotFoundException {

        FileInputStream fin = new FileInputStream("Student.txt");
        BufferedInputStream bin = new BufferedInputStream(fin);
        ObjectInputStream objectIn = new ObjectInputStream(bin);
        Student[] obj = (Student[]) objectIn.readObject();
        objectIn.close();
        bin.close();
        fin.close();

        return obj;
   }

    public static Instructor[] ReadFromFile(int t) throws IOException, ClassNotFoundException {

        FileInputStream fin = new FileInputStream("Instructor.txt");
        BufferedInputStream bin = new BufferedInputStream(fin);
        ObjectInputStream objectIn = new ObjectInputStream(bin);
        Instructor[] obj = (Instructor[]) objectIn.readObject();
        objectIn.close();
        bin.close();
        fin.close();
        return obj;
    }
   public static void auth(String id,String pass,int x) throws IOException {
       if (x == 1) {
           FileWriter fout = new FileWriter("StudPass.txt",true);
           BufferedWriter bout = new BufferedWriter(fout);
           bout.write(id+","+pass);
           bout.newLine();
           bout.flush();
           bout.close();
           fout.close();
       }else if (x == 2) {
           FileWriter fout = new FileWriter("InstructPass.txt",true);
           BufferedWriter bout = new BufferedWriter(fout);
           bout.write(id+","+pass);
           bout.newLine();
           bout.flush();
           bout.close();
           fout.close();
       }
   }


   public static boolean isRepeated(int y, String id){
        boolean x=false;
        try {
            File f=new File("StudPass.txt");
            File f2=new File("InstructPass.txt");
            if(f.exists())
                if(y==1){
                    FileReader fileReader = new FileReader("StudPass.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while ((line=bufferedReader.readLine())!= null){
                        String data[]=line.split(",");
                        if(data[0].equals(id)) x=true;
                    }
                    return x;
                }
            if(f2.exists())
                if (y==2) {
                        FileReader fileReader = new FileReader("InstructPass.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        while ((line=bufferedReader.readLine())!= null){
                            String data[]=line.split(",");
                            if(data[0].equals(id)) x=true;
                        }
                        return x;
                    }
        }catch (IOException e){
            e.printStackTrace();
        }

       return x;
   }
}


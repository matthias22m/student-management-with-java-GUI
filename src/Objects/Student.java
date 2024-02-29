package Objects;

import java.io.Serializable;

public class Student implements Serializable {
    public String id, fullName, address, contact ;
    public Course[] courses = Course.readFromFile();

}


import java.util.Vector;

public class Advisory {

    private Vector<Student> students;
    private float score;

    public Advisory(){

    }

    public void addStudent(Student s) {
        students.add(s);
    }
    public void removeStudent(Student s) {
        students.remove(s);
    }

}

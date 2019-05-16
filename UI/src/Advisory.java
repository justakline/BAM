import java.util.List;

public class Advisory {

    private List<Student> students;
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

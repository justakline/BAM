import java.util.Vector;
import java.util.List;
import java.util.Vector;

public class Advisory {

    private Vector<Student> students;
    private Vector<Student> students;
    private String advisor;
    private float score;

    public Advisory(Vector<Student> students, String advisor){
        this.students = students;
        this.advisor = advisor;

    }

    public void addStudent(Student s) {
        students.add(s);
    }
    public void removeStudent(Student s) {
        students.remove(s);
    }

    public String getAdvisor() {
        return advisor;
    }

    public Vector<Student> getStudents() {
        return students;
    }

    public Vector<String>getStudentsToString() {
        Vector<String> s = new Vector<>();
        for(Student stud:students) {
            s.add(stud.getName());
        }
        return s;

    }

}

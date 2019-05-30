import java.util.Vector;

public class Advisory {

    private Vector<Student> students;
    private String advisor;
    private float score;

    public Advisory(Vector<Student> students, String advisor){
        this.students = students;
        this.advisor = advisor;
    }

    public void addStudent(Student s) {
        students.add(s);
        s.setAdvisory(this);
    }

    public void removeStudent(Student s) {
        students.remove(s);
        s.setAdvisory(null);
    }

    public String getAdvisor() {
        return advisor;
    }

    public Vector<Student> getStudents() {
        return students;
    }

    public float getScore() {
        return score;
    }

    public float setScore(float score){
        this.score = score;
    }

    public Vector<String>getStudentsToString() {
        Vector<String> s = new Vector<>();
        students.forEach(Student -> s.add(Student.getName()));
        return s;
    }


}

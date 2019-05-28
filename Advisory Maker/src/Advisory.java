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
        updateScoreAdd(s);
    }

    public void removeStudent(Student s) {
        updateScoreRemove(s);
        students.remove(s);
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

    public Vector<String>getStudentsToString() {
        Vector<String> s = new Vector<>();
        students.forEach(Student -> s.add(Student.getName()));
        return s;
    }

    public void updateScoreAdd(Student s) {
        for (Student friend:students) {
            score+=Algorithm.getInstance().scoreStudents(s, friend) + Algorithm.getInstance().scoreStudents(friend, s);
        }

    }

    public void updateScoreRemove(Student s){
        for (Student friend:students) {
            score-=Algorithm.getInstance().scoreStudents(s, friend);
            score-=Algorithm.getInstance().scoreStudents(friend,s);
        }
    }

}

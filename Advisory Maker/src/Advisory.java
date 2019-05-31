import java.util.Vector;

public class Advisory {

    private Vector<Student> students;
    private String advisor;
    private float score;

    public Advisory(Vector<Student> students, String advisor){
        this.students = students;
        this.advisor = advisor;
        for (Student student : students)
            student.setAdvisory(this);
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
        return this.score = score;
    }

    public String toString()
    {
        String output = advisor + " Advisory | Score : " + score + " | Students : " + students.get(0).getName();
        for (int i = 1; i < students.size(); i++)
            output += ", " + students.get(i).getName();
        return output;
    }

    public void analyze(){
        System.out.println("FRIENDS");
        for (Student s0 : students)
            for (Student s1 : students)
                if(s0.isFriend(s1))
                    System.out.println("\t"+ s0.getName() + " --> " +s1.getName());

        System.out.println("INTERESTS");
        for (Student s0 : students)
            for (Student s1 : students)
                if(s0 != s1 && s0.interestCount(s1) > 0)
                    System.out.println("\t"+ s0.getName() + " --> " +s1.getName() + " = " + s0.interestCount(s1));

    }

    public Vector<String>getStudentsToString() {
        Vector<String> s = new Vector<>();
        students.forEach(Student -> s.add(Student.getName()));
        return s;
    }


}

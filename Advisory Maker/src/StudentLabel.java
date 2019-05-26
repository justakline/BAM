import javax.swing.*;

public class StudentLabel extends JLabel
{
    private Student student;

    public StudentLabel(Student _student)
    {
        super(_student.getName());
        setStudent(_student);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

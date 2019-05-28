import javax.swing.*;

public class StudentLabel extends JLabel {
	private Student student;

	public StudentLabel(Student student) {
		super(student.getName());
		setStudent(student);
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}

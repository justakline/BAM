import javax.swing.*;
import java.util.Vector;

public class AdvisorButton extends JToggleButton {

    private String name;
    private Vector<Student> students; //Vector for simple list of
    private JList<Student> studentJList; //JLIst for detail view


    public AdvisorButton(String name, Vector<Student> students) {
        super(name);
        this.name = name;
        this.students = students;
        studentJList = new JList<>(students);
        //studentJList //setTitle of JList

    }

    public JList getDetView() {
        return studentJList;
    }

    @Override
    public String getName() {
        return name;
    }
}

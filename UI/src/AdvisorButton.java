import javax.swing.*;
import java.util.Vector;

public class AdvisorButton extends JToggleButton {

    private Advisory advisory;
    private JList<Student> studentJList; //JLIst for detail view


    public AdvisorButton(String name, Vector<Student> students) {
        super(name);

        this.advisory = new Advisory(students, name);

        studentJList = new JList<>(students);
        //studentJList //setTitle of JList

    }

    public JList getDetView() {
        return studentJList;
    }

    @Override
    public String getName() {
        return advisory.getAdvisor();
    }

    public Advisory getAdvisory(){return advisory;}
}

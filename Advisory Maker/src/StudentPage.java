import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class StudentPage extends JInternalFrame implements ComponentListener {
    private Student student;
    private JScrollPane scrollPane;
    private JTable table;

    public StudentPage(Student student) {
        super(student.getName());
        this.student = student;
        this.setSize(200, 300);
        this.setVisible(true);
        this.setLayout(new GridLayout());

        //Creation of the data
        String[][] data = new String[student.getFriends().size() + student.getInterests().size()+2 ][3];
        for (int i = 0; i < student.getFriends().size(); i++) {
            data[i][0] = student.getFriends().get(i).getName();
            data[i][1] = student.getInterests().get(i);
        }

        table = new JTable((new DefaultTableModel(data, new String[]{"Friends", "Interests"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }));

        //Creation of the UI
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(30 * student.getFriends().size(), 120));
        scrollPane.addComponentListener(this);
        scrollPane.setViewportView(table);

        this.addComponentListener(this);
        this.add(scrollPane);
        this.setSize(new Dimension(200, 70 * student.getFriends().size()));
        this.setIconifiable(true);
        this.setClosable(true);
        this.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        scrollPane.setSize(e.getComponent().getSize());
        table.setSize(e.getComponent().getSize());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
    public Student getStudent(){
        return student;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTable getTable() {
        return table;
    }

}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Set;

public class StudentPage extends JInternalFrame implements ComponentListener {
    private Student student;
    JDesktopPane desktopPane;
    JScrollPane scrollPane;
    JInternalFrame internalFrame;
    JTable table;

    public StudentPage(Student student){
        super(student.getName());
        this.student = student;
        this.setSize(200, 300);
        this.setVisible(true);
        setLayout( new GridLayout());

//Creation of the data
        String[] col = new String[]{"Friends", "Interests"};

        String[][] data = new String[student.getFriends().size() + student.getInterests().size()+2 ][3];


        int i =0;

        Set<Student> friens = student.getFriends().keySet();


        for(Student friend : friens){
            data[i][0] = friend.getName();
            i++;
        }

        for(int j = 0; j< student.getFriends().size(); j++){
            data[j][1] = student.getInterests().get(j);
        }




        table = new JTable(data, col);
        DefaultTableModel tableModel = new DefaultTableModel(data, col) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table.setModel(tableModel);

//        table.

        //Creation of the UI

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(30 * student.getFriends().size(), 120));
        scrollPane.addComponentListener(this);
        scrollPane.setViewportView(table);



        addComponentListener(this);

        add(scrollPane);
        setSize(new Dimension(200, 70*student.getFriends().size()));
        setIconifiable(true);
        setClosable(true);
        setVisible(true);

    }

    @Override
    public void componentResized(ComponentEvent e) {

        System.out.println("Hi");
        Component component = e.getComponent();
        scrollPane.setSize( component.getSize());
        table.setSize(component.getSize());

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
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Set;
import java.util.Vector;

public class StudentPage extends JFrame{
    private Student student;
    JDesktopPane desktopPane;
    JScrollPane scrollPane;
    JInternalFrame internalFrame;

    public StudentPage(Student student){
        super(student.getName());
        this.student = student;
        this.setSize(200, 300);
        this.setVisible(true);
        setLayout( new FlowLayout());

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




        JTable table = new JTable(data, col);
        DefaultTableModel tableModel = new DefaultTableModel(data, col) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table.setModel(tableModel);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(100,100));

        add(scrollPane);
        setVisible(true);

    }
}

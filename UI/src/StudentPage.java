import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Set;
import java.util.Vector;

public class StudentPage extends JFrame  {
    private Student student;
    JDesktopPane desktopPane;
    JInternalFrame internalFrame;

    public StudentPage(Student student){
        super(student.getName());
        this.student = student;
        this.setSize(200, 300);
        this.setVisible(true);
        setLayout( new FlowLayout());

        String[] col = new String[]{"Friends", "Nothing", "Interests"};

        String[][] data = new String[student.getFriends().size() + student.getInterests().size()+2 ][3];

        data[0][0] = new String ("Friends");
        data[0][2] = new String("Interests");

        int i =2;

        Set<Student> friens = student.getFriends().keySet();


        for(Student friend : friens){
            data[i][0] = friend.getName();
            i++;
        }

        for(int j = 0; j< student.getFriends().size(); j++){
            data[j+2][2] = student.getInterests().get(j);
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
        JPanel panel = new JPanel();
//        internalFrame = new JInternalFrame();
//        desktopPane = new JDesktopPane();

        panel.add(table);
//        internalFrame.add(panel);
//        desktopPane.add(internalFrame);
//        add(desktopPane);
        add(panel);
        setVisible(true);






//        desktopPane = new JDesktopPane();
//        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
//        desktopPane.setVisible(true);
//        internalFrame = new JInternalFrame(student.getName(), true, true);
//
//
//        int tableHeight = 0;
//
//        if(student.getFriends().size() > student.getInterests().size())
//            tableHeight = student.getFriends().size();
//        else
//            tableHeight = student.getInterests().size();
//
//
//        String[] columnNames = {"Friends",  "Interests"};
//
//        String[][] dat = new String[student.getFriends().size() + student.getInterests().size() ][2];
//        int i =0;
//
//        Set<Student> friens = student.getFriends().keySet();
//
//
//        for(Student friend : friens){
//            dat[i][0] = friend.getName();
//        }
//
//        for(int j = 0; i < student.getFriends().size(); i++){
//            dat[j][1] = student.getInterests().get(j);
//
//        }
//
//
//        JTable table = new JTable( dat, columnNames);
//        DefaultTableModel tableModel = new DefaultTableModel(dat, columnNames) {
//
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                //all cells false
//                return false;
//            }
//        };
//
//        table.setModel(tableModel);
//        JPanel panel = new JPanel(new FlowLayout());
//        panel.add(table);
////        internalFrame.add(panel);
////        desktopPane.add(internalFrame);
//        this.add(panel);
//        setVisible(true);
//

    }
}

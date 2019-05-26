import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class AdvisoryFrame extends JInternalFrame{

    private Advisory advisory;
    private Vector<StudentLabel> labels;
    private int default_cell_width = 200;
    private int default_cell_height = 50;


    public AdvisoryFrame(Advisory _advisory){
        super(_advisory.getAdvisor() + " Advisory", true, true);
        advisory = _advisory;
        int numStudents = advisory.getStudents().size();

        setLayout(new GridLayout(numStudents, 1));
        setSize(default_cell_width, default_cell_height * numStudents);

        //createTable();
        labels = new Vector<>();
        for(Student student : advisory.getStudents())
        {
            StudentLabel label = new StudentLabel(student);
            labels.add(label);

            label.setBorder(new LineBorder(Color.BLACK));
            label.setBackground(Color.WHITE);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            label.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e)
                {
                    StudentPage newStudentPage = new StudentPage(student);//label_map.get(label));
                    GUI.getRightPane().getStudentPages().add(newStudentPage);
                    GUI.getRightPane().add(newStudentPage);
                }
            });

            // Add the JLabel to the JInternalFrame
            this.add(label);
        }

        setVisible(true);
        setIconifiable(true);
    }

    public Advisory getAdvisory() {
        return advisory;
    }

    private void update() {
        for (StudentLabel s : (StudentLabel[])this.getComponents())
            System.out.println(s.getName());
    }
}

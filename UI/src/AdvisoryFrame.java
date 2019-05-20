import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AdvisoryFrame extends JInternalFrame implements ListSelectionListener {

    private Advisory advisory;
    private String advisor;
    private JTable data;

    public AdvisoryFrame(AdvisorButton button){
       super(button.getName(), true, true);
        this.advisory= button.getAdvisory();
       createTable();
        JPanel panel = new JPanel();
        JScrollPane p = new JScrollPane();
        p.add(data);
        add(p);



        int height = 35 * advisory.getStudents().size();
        setSize(100,height);

        setVisible(true);
        data.getSelectionModel().addListSelectionListener(this);

    }

    public AdvisoryFrame(Advisory advisory){
        super("" + advisory.getAdvisor(), true, true);
        this.advisory= advisory;

        setLayout( new FlowLayout());
        createTable();
        JPanel panel = new JPanel();

        panel.add(data);
        add(panel);



        int height = 35 * advisory.getStudents().size();
        setSize(100,height);

        setVisible(true);
        data.getSelectionModel().addListSelectionListener(this);

    }

    private void createTable() {
        String[] columnNames = {advisory.getAdvisor()};
        String[][] dat = new String[advisory.getStudents().size()][1];

        for(int i = 0; i < advisory.getStudents().size(); i ++){
            dat[i][0] = advisory.getStudents().get(i).getName();
        }
        data= new JTable( dat, columnNames);
        JScrollPane p = new JScrollPane();
        data.setDragEnabled(true);
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(dat, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        data.setModel(tableModel);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Go");
       StudentPage studentPage = new StudentPage((Student) e.getSource());
    }
}

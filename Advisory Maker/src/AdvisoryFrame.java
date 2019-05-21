import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdvisoryFrame extends JInternalFrame implements ListSelectionListener {

    private Advisory advisory;
    private JTable data;

    public AdvisoryFrame(AdvisorButton button) {
        super(button.getName(), true, true);
        this.advisory = button.getAdvisory();
        this.setLayout(new GridLayout());
        createTable();
        this.add(new JScrollPane(data));

        this.setSize(100, 35 * advisory.getStudents().size());
        this.setVisible(true);
        data.getSelectionModel().addListSelectionListener(this);
    }

    public AdvisoryFrame(Advisory advisory){
        super("" + advisory.getAdvisor(), true, true);
        this.advisory= advisory;

        this.setLayout(new GridLayout());
        createTable();
//
        this.setSize(100, 35 * advisory.getStudents().size());
        this.setVisible(true);
        data.getSelectionModel().addListSelectionListener(this);
        setIconifiable(true);
    }

    private void createTable() {
        Object[] columnNames = {advisory.getAdvisor()};
        Object[][] dat = new String[advisory.getStudents().size()][1];

        for(int i = 0; i < advisory.getStudents().size(); i ++){
            dat[i][0] = advisory.getStudents().get(i).getName();
        }
        data= new JTable( dat, columnNames);
        JScrollPane p = new JScrollPane(data);
        p.setPreferredSize(new Dimension(50,100));
        add(p);

        data.setDragEnabled(true);
        //instance table model

        data.setModel(new DefaultTableModel(dat, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Go");
        int rowIndex = data.getSelectedRow();
        int colIndex = data.getSelectedColumn();
//        if(dat[rowIndex][colIndex].equals(advisory.getStudents().get(rowIndex-2)))
//       StudentPage studentPage = new StudentPage((Student) e.getSource());
    }
}

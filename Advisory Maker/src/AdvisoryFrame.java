import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class AdvisoryFrame extends JInternalFrame implements ListSelectionListener {

    private Advisory advisory;
    private JTable table;
    private Student[][] data;

    public AdvisoryFrame(AdvisorButton button) {
        super(button.getName(), true, true);

        this.advisory = button.getAdvisory();
        this.setLayout(new GridLayout());
        createTable();
        this.add(new JScrollPane(table));

        this.setSize(100, 35 * advisory.getStudents().size());
        this.setVisible(true);
        table.getSelectionModel().addListSelectionListener(this);
    }

    public AdvisoryFrame(Advisory advisory){
        super("" + advisory.getAdvisor(), true, true);
        this.advisory= advisory;

        this.setLayout(new GridLayout());
        createTable();
//
        this.setSize(100, 35 * advisory.getStudents().size());
        this.setVisible(true);
        table.getSelectionModel().addListSelectionListener(this);
        setIconifiable(true);


    }

    private void createTable() {
        Object[] columnNames = {advisory.getAdvisor()};
        data = new Student[advisory.getStudents().size()][1];
         Object[][] tableData = new String[advisory.getStudents().size()][1];
        for(int i = 0; i < advisory.getStudents().size(); i ++){
            data[i][0] = advisory.getStudents().get(i);

            tableData[i][0] =advisory.getStudents().get(i).getName();

        }


        table= new JTable( tableData, columnNames);
        JScrollPane p = new JScrollPane(table);
        p.setPreferredSize(new Dimension(50,100));
        add(p);

        table.setDragEnabled(true);
        //instance table model

        table.setModel(new DefaultTableModel(tableData, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Go");
        int rowIndex = table.getSelectedRow();
        int colIndex = table.getSelectedColumn();
        System.out.println(rowIndex + "  +  " + colIndex);
        System.out.println("Data[" + rowIndex +"][" + colIndex+"] =" + data[rowIndex][colIndex].getName());
        StudentPage newStudentPage = new StudentPage(data[rowIndex][colIndex]);
        GUI.getRightPane().getStudentPages().add(newStudentPage);
        GUI.getRightPane().add(newStudentPage );
//        if(data[rowIndex][colIndex].equals(advisory.getStudents().get(rowIndex-2)))
//       StudentPage studentPage = new StudentPage((Student) e.getSource());
    }
}

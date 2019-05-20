import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class AdvisoryFrame extends JInternalFrame {

    private Advisory advisory;
    private String advisor;
    private JTable data;


    public AdvisoryFrame(Advisory advisory){
        super("" + advisory.getAdvisor(), true, true);

        setLayout( new FlowLayout());
        String[] columnNames = {advisory.getAdvisor()};
        String[][] dat = new String[advisory.getStudents().size()][1];

        for(int i = 0; i < advisory.getStudents().size(); i ++){
            dat[i][0] = advisory.getStudents().get(i).getName();
        }
        data= new JTable( dat, columnNames);
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

        JPanel panel = new JPanel();

        panel.add(data);
        add(panel);


        this.advisory= advisory;
        int height = 35 * advisory.getStudents().size();
        setSize(100,height);

        setVisible(true);

    }
}

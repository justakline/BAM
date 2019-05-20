import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class AdvisoryFrame extends JInternalFrame {

    private Advisory advisory;
    private String advisor;
    private JTable data;


    public AdvisoryFrame(Advisory advisory){
        super("" + advisory.getAdvisor());



        setLayout( new GridLayout());
        this.
        setClosable(true);
        setResizable(true);
        setMaximizable(true);

              String[] columnNames = {advisory.getAdvisor()};
        String[][] dat = new String[advisory.getStudents().size()][1];

        for(int i = 0; i < advisory.getStudents().size(); i ++){
            dat[i][0] = advisory.getStudents().get(i).getName();
        }
        data= new JTable( dat, columnNames);

        JScrollPane scrollPane = new JScrollPane(data);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel);


        this.advisory= advisory;
        setSize(300,300);

        setVisible(true);

    }
}

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class AdvisoryButton extends JInternalFrame {

    private Advisory advisory;
    private String advisor;
    private JTable data;


    public AdvisoryButton(Advisory advisory){
        super("" + advisory.getAdvisor(), true, true);
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.add(this);
        setLayout( new FlowLayout());

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

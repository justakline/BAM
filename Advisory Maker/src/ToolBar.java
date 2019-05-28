import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ToolBar extends JMenuBar implements ActionListener, MenuListener {

    private JFileChooser fc;
    private JMenu file;
    private JMenu load;
    private JMenu run;
    private JMenu help;
    private JMenu options;
    private File studentCSV;
    private File activitiesCSV;
    private GUI host;

    public ToolBar() {

    }
    public ToolBar(GUI host){
        super();
        fc = new JFileChooser() {
            @Override
            public void setFileFilter(FileFilter filter) {
                super.setFileFilter(new FileNameExtensionFilter(".CSV files", "CSV", "csv"));
            }
        };
        JMenuItem temp1;
        JMenu temp2;
        this.host = host;
        fc = new JFileChooser();
        JMenuItem temp;

        //Creation of the file Tab and associated submenu items
        this.add(file = new JMenu("File"));
        file.add(temp1 = new JMenuItem("New"));
        file.add(new JMenuItem("Open"));
        file.add(new JMenuItem("Print"));

        this.add(load = new JMenu("Load"));
        load.add(temp1 = new JMenuItem("Load Student List"));
        temp1.addActionListener(this);
        load.add(temp1 = new JMenuItem("Load Activities List"));
        temp1.addActionListener(this);

        this.add(temp2 = run = new JMenu("Run"));
        temp2.addMenuListener(this);
        this.add(temp2 = help = new JMenu("Help"));
        temp2.addMenuListener(this);

        this.add(options = new JMenu("Options"));
        options.add(new JMenuItem("Set to One Window"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource().equals(load.getMenuComponent(0)))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                studentCSV = fc.getSelectedFile();
                System.out.println("student: " + studentCSV);
            }
        } else if ((e.getSource().equals(load.getMenuComponent(1)))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                activitiesCSV = fc.getSelectedFile();
                System.out.println("activities: " + activitiesCSV);
            }
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if ((e.getSource().equals(this.run))) {
            try {
                System.out.println(CSVParser.buildStudentList(studentCSV, activitiesCSV));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        fc.showOpenDialog(host.getRightPanel());
//    }
}

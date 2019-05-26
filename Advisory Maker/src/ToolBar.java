import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JMenuBar implements ActionListener, MenuListener {

    private final JFileChooser fc;
    private JMenu file;
    private JMenu load;
    private JMenu run;
    private JMenu help;
    private JMenu options;
    private GUI host;

    public ToolBar(GUI host){
        super();
        this.host = host;
        fc = new JFileChooser();
        JMenuItem temp;

        //Creation of the file Tab and associated submenu items
        this.add(file = new JMenu("File"));
        file.add(temp = new JMenuItem("New"));
        file.add(new JMenuItem("Open"));
        file.add(new JMenuItem("Print"));

        this.add(load = new JMenu("Load"));
        load.add(temp = new JMenuItem("Load Student List"));
        temp.addActionListener(this);
        load.add(temp = new JMenuItem("Load Activities List"));
        temp.addActionListener(this);

        this.add(run = new JMenu("Run"));
        this.add(help = new JMenu("Help"));

        this.add(options = new JMenu("Options"));
        options.add(new JMenuItem("Set to One Window"));
    }

    @Override
    public void menuSelected(MenuEvent e) {

    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fc.showOpenDialog(host.getRightPanel());
    }
}

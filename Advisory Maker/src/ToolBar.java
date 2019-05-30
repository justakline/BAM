import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class ToolBar extends JMenuBar implements ActionListener, MenuListener{

    private JFileChooser fc;
    private JMenu file;
    private JMenu load;
    private JMenu run;
    private JMenu help;
    private JMenu options;
	private GUI host;
    private File studentCSV;
    private File activitiesCSV;
    private File friendsCSV;
    private JComboBox<String>studentJComboBox;

	public ToolBar(GUI host) {
        super();
		this.host = host;
        fc = new JFileChooser() {
            @Override
            public void setFileFilter(FileFilter filter) {
                super.setFileFilter(new FileNameExtensionFilter(".CSV files", "CSV", "csv"));
            }
        };
        JMenuItem temp1; //temp for attaching listeners
        JMenu temp2; //temp for attaching listeners
        fc = new JFileChooser();

        //Creation of the file Tab and associated submenu items
        this.add(file = new JMenu("File"));
        file.add(temp1 = new JMenuItem("New")); //0
        temp1.addActionListener(this);
        file.add(temp1 = new JMenuItem("Open")); //1
        temp1.addActionListener(this);
        file.add(temp1 = new JMenuItem("Print")); //2
        temp1.addActionListener(this);

        this.add(load = new JMenu("Load"));
        load.add(temp1 = new JMenuItem("Load Student List"));
        temp1.addActionListener(this);
        load.add(temp1 = new JMenuItem("Load Activities List"));
        temp1.addActionListener(this);
        load.add(temp1 = new JMenuItem("Load Friends List"));
        temp1.addActionListener(this);
        load.add(temp1 = new JMenuItem("Load All"));
        temp1.addActionListener(this);

        this.add(temp2 = run = new JMenu("Run"));
        temp2.addMenuListener(this);
        this.add(temp2 = help = new JMenu("Help"));
        temp2.addMenuListener(this);

        this.add(options = new JMenu("Options"));
        options.add( temp1 =new JMenuItem("Find Student"));
        temp1.addActionListener(this);
        Vector<String>studs = new Vector<>();
        for (AdvisoryFrame advisoryFrame : host.getRightPanel().getAdvisoryFrames()){
            for (Student student: advisoryFrame.getAdvisory().getStudents()){
                studs.add(student.getName());
            }

        }
        studentJComboBox = new JComboBox<>(studs);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource().equals(load.getMenuComponent(0)))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                studentCSV = fc.getSelectedFile();
            }
        } else if ((e.getSource().equals(load.getMenuComponent(1)))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                activitiesCSV = fc.getSelectedFile();
            }
        } else if ((e.getSource().equals(load.getMenuComponent(2)))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                friendsCSV = fc.getSelectedFile();
            }
        } else if ((e.getSource().equals(load.getMenuComponent(3)))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                friendsCSV = fc.getSelectedFile();
            }
        } else if (e.getSource().equals(file.getMenuComponent(2))) {
            try {
                host.getLeftPanel().getCSV();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource().equals(file.getItem(1))) {//Open
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                try {
                    FileInputStream fileIn = new FileInputStream("initial_save.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                     host = (GUI)in.readObject();
//                    System.out.println( hash.toString() );
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
//                host = fc.getSelectedFile();
            }
        }  else if (e.getSource().equals(file.getItem(0))){//New
//            host.dispose();
            host = new GUI();
            host.setVisible(true);
            host.setSize(new Dimension(500,500));
            host.setPreferredSize(new Dimension(500,500));

        }else if (e.getSource().equals(options.getItem(0))){//Find Student
            System.out.println("GO");
            JInternalFrame frame = new JInternalFrame();
            frame.setVisible(true);
            frame.setSize(200, 80);
            frame.setPreferredSize(new Dimension(200,60));
            frame.add(studentJComboBox);
            host.getRightPanel().add(frame);
            studentJComboBox.addActionListener(host.getRightPanel());
            frame.setClosable(true);
            frame.setTitle("Find Student");
//            host.getRightPanel().findStudent();


        }


    }

    @Override
    public void menuSelected(MenuEvent e) {
        if ((e.getSource().equals(this.run))) {
            try {
                System.out.println(CSVParser.buildStudentList(studentCSV, activitiesCSV, friendsCSV));
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
}

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class SettingsWindow extends JInternalFrame implements ActionListener {

    private JFileChooser fc;

    private JButton students;
    private JButton interests;
    private JButton settings;
    private JButton run;
    private File studentCSV;
    private File activitiesCSV;
    private GUI host;
    private Vector<JButton> buttons;

    public SettingsWindow(GUI host) {
        super("Settings");

        setSize(new Dimension(400, 400));
        setLayout(new GridLayout(4,1));

        Panel zero = new Panel();
        zero.setLayout(new GridLayout(1,2));
        Panel one = new Panel();
        one.setLayout(new GridLayout(1,2));
        Panel two = new Panel();
        two.setLayout(new GridLayout(1,2));
        Panel three = new Panel();
        three.setLayout(new GridLayout(1,3));
        students = new JButton("Import Students");
        interests = new JButton("Import Interests");
        settings = new JButton("Open Settingds");
        run = new JButton("Run");

        buttons.add(students);
        buttons.add(interests);
        buttons.add(settings);
        buttons.add(run);
        for (JButton b : buttons){
            b.setPreferredSize(new Dimension(400, 200));
            b.setHorizontalAlignment(JTextField.CENTER);
        }


        buttons.get(0).setFont(new Font("Import Students", 1, 40));
        buttons.get(1).setFont(new Font("Import Interests", 1, 40));
        buttons.get(2).setFont(new Font("Open Settings", 1, 40));
        buttons.get(3).setFont(new Font("Run", 1, 40));


//        students = new JButton(buttons.get(0));
        zero.add(buttons.get(0));
        one.add(buttons.get(1));
        two.add(buttons.get(2));
        three.add(buttons.get(3));
        add(zero);
        add(one);
        add(two);
        add(three);

//        setSize(new Dimension(400, 400));
//        setLayout(new GridLayout(3,1));
//
//        Panel up = new Panel();
//        Panel middle = new Panel();
//        Panel down = new Panel();
//
//        middle.setLayout(new GridLayout(2,1));
//        Panel middleUp = new Panel();
//        Panel middleDown = new Panel();
//
//        down.setLayout(new GridLayout(2,1));
//        Panel downUp = new Panel();
//        Panel downDown = new Panel();

        System.out.println("Working");

        fc = new JFileChooser() {
            @Override
            public void setFileFilter(FileFilter filter) {
                super.setFileFilter(new FileNameExtensionFilter(".CSV files", "CSV", "csv"));
            }
        };
        this.host = host;
        setVisible(true);





    }
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource().equals(students))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                studentCSV = fc.getSelectedFile();
                System.out.println("student: " + studentCSV);
            }
        } else if ((e.getSource().equals(interests))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                activitiesCSV = fc.getSelectedFile();
                System.out.println("activities: " + activitiesCSV);
            }
        }
    }
}

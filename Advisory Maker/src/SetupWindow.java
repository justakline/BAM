import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class SetupWindow extends JInternalFrame implements ActionListener {

    private JFileChooser fc;

    private JButton students;
    private JButton friends;
    private JButton interests;
    private JButton settings;
    private JButton run;

    private JCheckBox studentBox, friendsBox, interestsBox, settingsBox;

    private File studentCSV;
    private File activitiesCSV;
    private File friendsCSV;
    private GUI host;
    private Vector<JButton> butt = new Vector<>();
    private SettingsWindow settingsWindow;

    private JFrame realSettings;

    private float friendValue;
    private float interestValue;
    private  boolean isWorking;

    public SetupWindow(GUI host) {
        super("Setup");
        isWorking = true;
        setSize(new Dimension(500, 500));
        setLayout(new GridLayout(5,1));

        Panel zero = new Panel();
        zero.setLayout(new GridLayout(1,2));
        Panel one = new Panel();
        one.setLayout(new GridLayout(1,2));
        Panel two = new Panel();
        two.setLayout(new GridLayout(1,2));
        Panel three = new Panel();
        zero.setLayout(new GridLayout(1,2));
        Panel four = new Panel();
        three.setLayout(new GridLayout(1,3));
        students = new JButton("Import Students");
        friends = new JButton ("Import Friends");
        interests = new JButton("Import Interests");
        settings = new JButton("Open Settings");
        run = new JButton("Run");
        studentBox = new JCheckBox();
        studentBox.addActionListener(this);
        studentBox.setEnabled(false);
        interestsBox = new JCheckBox();
        interestsBox.addActionListener(this);
        interestsBox.setEnabled(false);
        friendsBox = new JCheckBox();
        friendsBox.addActionListener(this);
        friendsBox.setEnabled(false);
        settingsBox = new JCheckBox();
        settingsBox.addActionListener(this);
        settingsBox.setEnabled(false);


        students.addActionListener(this);
        friends.addActionListener(this);
        interests.addActionListener(this);
        this.butt = new Vector<>();
        butt.add(students);
        butt.add(friends);
        butt.add(interests);
        butt.add(settings);
        butt.add(run);
        for (JButton b : butt){
            b.setPreferredSize(new Dimension(400, 100));
            b.setHorizontalAlignment(JTextField.CENTER);
        }


        butt.get(0).setFont(new Font("Import Students", 1, 20));
        butt.get(1).setFont(new Font("Import Friends", 1, 20));
        butt.get(2).setFont(new Font("Import Interests", 1, 20));
        butt.get(3).setFont(new Font("Open Settings", 1, 20));
        butt.get(4).setFont(new Font("Run", 1, 20));


//        students = new JButton(butt.get(0));
        zero.add(butt.get(0));
        zero.add(studentBox);
        one.add(butt.get(1));
        one.add(friendsBox);
        two.add(butt.get(2));
        two.add(interestsBox);
        three.add(butt.get(3));
        three.add(settingsBox);
        four.add(butt.get(4));
        add(zero);
        add(one);
        add(two);
        add(three);
        add(four);

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

        settingsWindow = new SettingsWindow(this);




    }
    public void actionPerformed(ActionEvent e) {

        System.out.println("Action performed");
        if ((e.getSource().equals(students))) {


            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                studentCSV = fc.getSelectedFile();
                studentBox.setSelected(true);
                System.out.println("student: " + studentCSV);
            }
        } else if ((e.getSource().equals(interests))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                activitiesCSV = fc.getSelectedFile();
                interestsBox.setSelected(true);
                System.out.println("activities: " + activitiesCSV);
            }
        } else if ((e.getSource().equals(friends))) {
            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
                friendsCSV = fc.getSelectedFile();
                friendsBox.setSelected(true);
                System.out.println("Friends: " + friendsCSV);
            }
        }else if ((e.getSource().equals(settings))) {
            System.out.println("Settings");
            settingsWindow.setVisible(true);
        }
//Wait for all toher checkMarks to be hit, then leave
        else if(e.getSource().equals(run)){
            if(interestsBox.isSelected() && friendsBox.isSelected() && settingsBox.isSelected()){
                isWorking= false;
            }
        }


    }

    public void initializeValues(Vector<Float> vals) {
        friendValue = vals.get(0);
        interestValue = vals.get(1);
        settingsWindow.setVisible(false);
    }

    public float getFriendValue() {
        return friendValue;
    }

    public float getInterestValue() {
        return interestValue;
    }

    public boolean isWorking() {
        return isWorking;
    }


}

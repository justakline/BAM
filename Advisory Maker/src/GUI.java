import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GUI extends JFrame {

    static Vector<String> data = new Vector<>(Stream.of("Smith", "Valente", "Fouchet", "Wilson", "Shang", "Conn", "Stamper", "Rheingold", "Bakewell", "Newton", "Smith", "Valente", "Fouchet", "Wilson", "Shang", "Conn", "Stamper", "Rheingold", "Bakewell", "Newton").collect(Collectors.toList()));
    //Temp Dummies



    private JSplitPane split1;

    public GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Init Components in this block:
        split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LeftPane.getLeftPane(), RightPane.getRightPane());

        //AddComponents in this block:
        setJMenuBar(new ToolBar()); //accessible with getJMenuBar();

        this.add(split1);

        //Attributes of JFrame
        this.setSize(800, 800);
        this.setVisible(true);
        setTitle("Insert Sick UI/UX Here");

        Vector<String> advisors = new Vector<String>();
        advisors.add("Valente");
        advisors.add("Smith");
        advisors.add("Conn");
        Vector<String> interests = new Vector<String>();
        interests.add("Computer Science");
        interests.add("Bike Watching");
        interests.add("Singing in the Shower");




        Student justin = new Student("Justin", advisors, interests, Student.Gender.MALE);
        Student ryan = new Student("Ryan", advisors, interests, Student.Gender.MALE);
        Student nick = new Student("Nick", advisors, interests, Student.Gender.MALE);

        Vector<Student> friends = new Vector<Student>();
        friends.add(ryan);
        friends.add(nick);

        justin.addFriendGroup(friends);

//        StudentPage page = new StudentPage(justin);

        Vector<Student> students = new Vector<>();
        students.add(justin);
        students.add(ryan);
        students.add(nick);

        Advisory advisory = new Advisory(students, advisors.firstElement());
        AdvisoryFrame advisoryFrame = new AdvisoryFrame(advisory);
        RightPane.getRightPane().add(advisoryFrame);
        RightPane.getRightPane().add(new StudentPage(justin));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GUI test = new GUI();
        test.setVisible(true);
    }

    private static class LeftPane extends JPanel {

        private static LeftPane leftPane; //singleton
        private Vector<AdvisorButton> advisors;

        private LeftPane() {
            //Init Fields
            this.setLayout(new GridLayout(0, 1, 0, 0));
            advisors = new Vector<>();
            this.add(new JLabel("Advisories:", SwingConstants.CENTER));

            //Create AdvisorButtons
            for (int i = 0; i < data.size(); i++) {
                AdvisorButton adv = new AdvisorButton(new Advisory(new Vector<Student>(), data.get(i)));
                adv.addActionListener(RightPane.getRightPane());
                this.add(adv);
            }
        }

        public static LeftPane getLeftPane() {
            return (leftPane == null) ? (leftPane = new LeftPane()) : (leftPane);
        }
    }

    private static class RightPane extends JDesktopPane implements ActionListener {

        private static RightPane rightPane; //singleton

        public RightPane() {
            super();
            this.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
            this.setVisible(true);
        }

        public static RightPane getRightPane() {
            return (rightPane == null) ? (rightPane = new RightPane()) : (rightPane);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AdvisorButton source = (AdvisorButton) e.getSource();
            if (source.isSelected()) {
                System.out.println("I'm pressed for time");
                this.add(new AdvisoryFrame(source.getAdvisory()));
            }
            else {
                this.remove(new AdvisoryFrame(source));
            }
        }
    }
}

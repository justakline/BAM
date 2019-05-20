import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GUI extends JFrame {

    static Vector<String> data = new Vector<>(Stream.of("Smith", "Valente", "Fouchet", "Wilson", "Shang", "Conn", "Stamper", "Rheingold", "Bakewell", "Newton", "Smith", "Valente", "Fouchet", "Wilson", "Shang", "Conn", "Stamper", "Rheingold", "Bakewell", "Newton").collect(Collectors.toList()));

    private final String NORTH = BorderLayout.PAGE_START;
    private final String SOUTH = BorderLayout.PAGE_END;
    private final String EAST = BorderLayout.LINE_START;
    private final String WEST = BorderLayout.LINE_END;
    //TEMP DUMMIES
    private final String CENTER = BorderLayout.CENTER;
    private final JPanel main = new JPanel(new BorderLayout());
    private JPanel left, right;
    private JSplitPane split1;
    private JList<String> advisories;

    public GUI() {

        //Init Components in this block:
        left = LeftPane.getLeftPane();
        right = RightPane.getRightPane();
        split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);

        //AddComponents in this block:
        setJMenuBar(new ToolBar()); //accessible with getJMenuBar();

        main.add(split1);
        this.add(main);

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

        StudentPage page = new StudentPage(justin);

        Vector<Student> students = new Vector<>();

        students.add(justin);
        students.add(ryan);
        students.add(nick);

        Advisory advisory = new Advisory(students, advisors.firstElement());
        AdvisoryFrame advisoryFrame = new AdvisoryFrame(advisory);
        right.add(advisoryFrame);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GUI test = new GUI();
        test.setVisible(true);
    }

    private static class LeftPane extends JPanel {

        private static LeftPane leftPane; //singleton


        private JLabel title;
        private JSeparator separator;
        private JScrollPane scrollPane;
        private Vector<AdvisorButton> advisors;
        private ButtonGroup advisorButtons;


        private LeftPane() {
            //Init Fields
            this.setLayout(new GridLayout(0, 1, 0, 0));
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            advisors = new Vector<>();
            title = new JLabel("Advisories");
            advisorButtons = new ButtonGroup();
            scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


            //Create AdvisorButtons
            for (int i = 0; i < data.size(); i++) {
                advisors.add((new AdvisorButton(data.get(i), new Vector<>())));
            }
            //Register Buttons with Mr.RightPane
            for (AdvisorButton advisor : advisors) {
                advisor.addActionListener(RightPane.getRightPane());
                System.out.println(advisor.getName() + ": Listening");
            }
            for (AdvisorButton advisor : advisors) {
                advisorButtons.add(advisor);
            }
            // Add Buttons to Mr.JFrame to display
            for (JToggleButton advisor : advisors) {
                scrollPane.add(this);
                this.add(advisor);
            }
        }

        public static LeftPane getLeftPane() {
            if (leftPane == null) {
                return (leftPane = new LeftPane());
            }
            return leftPane;
        }
    }

    private static class RightPane extends JPanel implements ActionListener {

        private static RightPane rightPane; //Singleton

        private static Vector<JPanel> panels;
        private static JPanel panel1;
        private static JPanel panel2;
        private static JPanel panel3;


        private Vector<AdvisoryFrame> advisoryFrames;

        private RightPane() {
            this.setLayout(new GridLayout(2, 2, 0, 0));
            Vector<JPanel> panels;
            panel1 = new JPanel();
            panel2 = new JPanel();
            panel3 = new JPanel();
            this.add(panel1);
            this.add(panel2);
            this.add(panel3);


        }

        public static RightPane getRightPane() {
            if (rightPane == null) {
                rightPane = new RightPane();
            }
            return rightPane;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("I've been pressed");
            AdvisorButton source = (AdvisorButton) e.getSource();
            this.add(source.getDetView());
        }

        public void addAdvisoryButton(AdvisoryFrame frame) {
//            advisoryButton.reshape(30,30,30,100);
            frame.setBounds(0, 0, 100, 100);
            advisoryFrames.add(frame);
            add(frame);

        }
    }

}

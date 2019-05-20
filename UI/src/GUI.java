import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GUI extends JFrame {

    static Vector<String> data = new Vector<>(Stream.of("Smith", "Valente", "Fouchet", "Wilson", "Shang", "Conn").collect(Collectors.toList()));
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

        Student justin = new Student("Justin",advisors, interests, Student.Gender.MALE);
        Student ryan = new Student("Ryan",advisors, interests, Student.Gender.MALE);
        Student nick = new Student("Nick",advisors, interests, Student.Gender.MALE);

        StudentPage page = new StudentPage(justin);

        Vector<Student> students = new Vector<>();

        students.add(justin);
        students.add(ryan);
        students.add(nick);

        Advisory advisory = new Advisory(students, advisors.firstElement());
        AdvisoryFrame advisoryFrame  = new AdvisoryFrame(advisory );
        right.add(advisoryFrame);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static class LeftPane extends JPanel {

        private static LeftPane leftPane;
        private JLabel title;
        private JSeparator separator;
        private JList advisors;


        private LeftPane() {
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            advisors = new JList<>(data);
            this.add(title = new JLabel("Advisories")); //Note to Rufe: Ask S/V, is this a bad thing to do?
            this.add(Box.createRigidArea(new Dimension(0, 10)));
            this.add(advisors);
            //Need to modify a little and add data but pretty much there



        }

        public static LeftPane getLeftPane() {
            if (leftPane == null) {
                return (leftPane = new LeftPane());
            }
            return leftPane;
        }
    }

    private static class RightPane extends JPanel {

        private static RightPane rightPane;

        private Vector<AdvisoryFrame> advisoryFrames;
        private RightPane() {

        }

        public static RightPane getRightPane() {
            if (rightPane == null) {
                rightPane = new RightPane();
            }
            return rightPane;
        }
        public void addAdvisoryButton(AdvisoryFrame frame){
//            advisoryButton.reshape(30,30,30,100);
            frame.setBounds(0,0, 100, 100);
            advisoryFrames.add(frame);
            add(frame);

        }
    }



    public static void main(String[] args) {
        GUI test = new GUI();
        test.setVisible(true);
    }
}

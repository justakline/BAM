import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GUI extends JFrame {

    //Temp Dummies
    public static Vector<StudentPage> displayedStudentPages;


    public GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        displayedStudentPages = new Vector<>();

        //Init Components in this block:
        this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LeftPane.getLeftPane(), RightPane.getRightPane()));

        //AddComponents in this block:
        setJMenuBar(new ToolBar()); //accessible with getJMenuBar();

        //Attributes of JFrame
        this.setSize(800, 800);
        this.setVisible(true);
        setTitle("Insert Sick UI/UX Here");

        RightPane.getRightPane().add(TestCases.getAdvisoryFrame());
        RightPane.getRightPane().add(new StudentPage(TestCases.getFriendlyMaleStudentTest()));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GUI test = new GUI();
        test.setVisible(true);
    }

    public static RightPane getRightPane() {
        return RightPane.getRightPane();
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
            for (int i = 0; i < TestCases.getAdvisorStringList().size(); i++) {
                AdvisorButton adv = new AdvisorButton(new Advisory(TestCases.getStudentGroupTest(), TestCases.getAdvisorStringList().get(i)));
                adv.addActionListener(RightPane.getRightPane());
                this.add(adv);
            }
        }

        public static LeftPane getLeftPane() {
            return (leftPane == null) ? (leftPane = new LeftPane()) : (leftPane);
        }
    }

    public static class RightPane extends JDesktopPane implements ActionListener {

        private static RightPane rightPane; //singleton
        private Vector<AdvisoryFrame> advisoryFrames;
        private Vector<StudentPage> studentPages;

        public RightPane() {
            super();
            studentPages = new Vector<>();
            this.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
            this.setVisible(true);

        }

        public static RightPane getRightPane() {
            return (rightPane == null) ? (rightPane = new RightPane()) : (rightPane);
        }

        public void addAdvisoryFrame(AdvisoryFrame advisory) {
            this.add(advisory);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AdvisorButton source = (AdvisorButton) e.getSource();
            System.out.println("Here");
            RightPane.getRightPane().add(new AdvisoryFrame(source.getAdvisory()));
//            if(e.getClass().getName().equals("AdvisoryButton")){
//                AdvisorButton source = (AdvisorButton) e.getSource();
//                if (source.isSelected()) {
//                    System.out.println("I'm pressed for time");
//                    this.add(new AdvisoryFrame(source.getAdvisory()));
//                }
//                else {
//                    this.remove(new AdvisoryFrame(source));
//                }
//            }
//            if(e.getClass().getName().equals("Student")){
//
//            }
        }

        public Vector<StudentPage> getStudentPages() {
            return studentPages;
        }


    }
}

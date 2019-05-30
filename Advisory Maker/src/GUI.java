import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

public class GUI extends JFrame implements Serializable {

	public Vector<StudentPage> displayedStudentPages;

	private Vector<Student> students;
	private AdvisorySelectionPanel leftPanel;
	private AdvisoryDisplayPanel rightPanel;
	private WelcomeWindow welcomeWindow;
	private SetupWindow setupWindow;
	private SettingsWindow settingsWindow;
	private SearchBox studentFinder;
	private Algorithm al;

	private File studentsCSV;
	private File activitiesCSV;
	private File friendsCSV;
	private File masterCSV;

	public GUI() {
		super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
		studentFinder = new SearchBox();
		welcomeWindow = new WelcomeWindow(this);


		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
//        this.setPreferredSize(new Dimension(700, 700));
		setSize(new Dimension(700,700));
		welcomeWindow = new WelcomeWindow(this);
        add(welcomeWindow);


        while(welcomeWindow.isWorking()) {
			System.out.println(welcomeWindow.isWorking());
		}
		System.out.println("Moved");
        remove(welcomeWindow);

		System.out.println("Bye Welcome Window");
        setupWindow = new SetupWindow(this);
        add(setupWindow);
//        settingsWindow = new SettingsWindow(setupWindow);
//        add(settingsWindow);

        while(setupWindow.isWorking()){
			System.out.println("isWorking");
		}

		float friendGroupValue = setupWindow.getFriendValue();
		float interestValue = setupWindow.getInterestValue();
		masterCSV = setupWindow.getMasterCSV();
		activitiesCSV = setupWindow.getActivitiesCSV();
		studentsCSV = setupWindow.getStudentCSV();
		activitiesCSV = setupWindow.getActivitiesCSV();

		try {
			students = CSVParser.masterCSVParser(masterCSV);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setupWindow.getProgressFrame().setVisible(false);
		remove(setupWindow);
//        settingsWindow = new SettingsWindow(this);

        displayedStudentPages = new Vector<>();

        //Init Components in this block:
		rightPanel = new AdvisoryDisplayPanel(this);
		leftPanel = new AdvisorySelectionPanel(this);
		this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel));

        //AddComponents in this block:
		setJMenuBar(new ToolBar(this)); //accessible with getJMenuBar();

        //Attributes of JFrame
        this.setSize(800, 800);
		this.setVisible(true);
		setTitle("BAM!!!");

		this.al = new Algorithm(getStudents(), getLeftPanel().getAdvisories());
		al.setFriendMult(friendGroupValue);
		al.setInterestMult(interestValue);
    }

	public AdvisoryDisplayPanel getRightPanel() {
		return rightPanel;
	}

	public AdvisorySelectionPanel getLeftPanel() {
		return leftPanel;
	}

	public Algorithm getAl() {
		return al;
	}

	public SearchBox getStudentFinder() {
		return studentFinder;
	}

	public Vector<Student> getStudents() {
		return students;
	}
}

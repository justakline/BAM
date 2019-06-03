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
		setSize(new Dimension(800,800));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
		studentFinder = new SearchBox();
		welcomeWindow = new WelcomeWindow(this);
		setupWindow = new SetupWindow(this);
		setupWindow.setVisible(false);
		add(welcomeWindow);
		add(setupWindow);
		showWelcome();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
//        this.setPreferredSize(new Dimension(700, 700));
		setSize(new Dimension(700,700));



//        settingsWindow = new SettingsWindow(this);

        displayedStudentPages = new Vector<>();
		rightPanel = new AdvisoryDisplayPanel(this);
		rightPanel.setVisible(false);

        //Init Components in this block:



        //AddComponents in this block:


        //Attributes of JFrame
        this.setSize(800, 800);
		this.setVisible(true);
		setTitle("BAM!!!");


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

	public void showSettings() {
		settingsWindow.setVisible(true);
	}

	public void showSetup(){
		welcomeWindow.setVisible(false);
		setupWindow.setVisible(true);

	}

	public void showWelcome (){
		welcomeWindow.setVisible(true);
	}

	public void showPanels() {
		//Init Components in this block:
		setupWindow.setVisible(false);
		rightPanel.setVisible(true);
		leftPanel.setVisible(true);
		this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel));

		setMenu();
	}

	public void makeCSV() {
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
		leftPanel = new AdvisorySelectionPanel(this);
		leftPanel.setVisible(false);
		this.al = new Algorithm(getStudents(), getLeftPanel().getAdvisories());
		al.setFriendMult(friendGroupValue);
		al.setInterestMult(interestValue);

		try {
			students = CSVParser.masterCSVParser(masterCSV);
		} catch (IOException e) {
			e.printStackTrace();
		}
		showPanels();

	}

	public void setMenu(){
		setJMenuBar(new ToolBar(this)); //accessible with getJMenuBar();
	}
}

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

public class GUI extends JFrame implements Serializable {

	public Vector<StudentPage> displayedStudentPages;

	private Vector<Student> students;
	private Vector<Advisory> advisories;

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
		setSize(new Dimension(800, 800));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		studentFinder = new SearchBox();
		welcomeWindow = new WelcomeWindow(this);
		welcomeWindow.setResizable(true);
		setupWindow = new SetupWindow(this);
		setupWindow.setVisible(false);

		add(welcomeWindow);
		add(setupWindow);
		showWelcome();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);


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

	public Vector<Advisory> getAdvisories() {
		return advisories;
	}

	public void showSettings() {
		settingsWindow.setVisible(true);
	}

	public void showSetup() {
		welcomeWindow.setVisible(false);
		setupWindow.setVisible(true);

	}

	public void showWelcome() {
		welcomeWindow.setVisible(true);
	}

	public void showPanels(Vector<Advisory> advisories) {
		//Init Components in this block:
		rightPanel = new AdvisoryDisplayPanel(this);
		leftPanel = new AdvisorySelectionPanel(this, advisories);


		setupWindow.setVisible(false);
		rightPanel.setVisible(true);
		leftPanel.setVisible(true);
		this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel));

		setMenu();
	}

	public void runAlgorithm() {
		try {
			students = CSVParser.masterCSVParser(setupWindow.getMasterCSV());
		} catch(IOException e) {
			e.printStackTrace();
		}
		this.advisories = initAdv(students);
		al = new Algorithm(this, setupWindow.getFriendValue(), setupWindow.getInterestValue());
		al.run();
		showPanels(al.getAdvisories());
	}

	private Vector<Advisory> initAdv(Vector<Student> students) {
		int n = (int) setupWindow.getAdvNumb();
		Vector<Vector<Student>> groups = new Vector<>();
		for(int i = 0; i < n; i++) {
			groups.add(new Vector<>());
		}
		for(int i = 0; i < students.size(); i++) {
			groups.get(i % n).add(students.get(i));
		}
		Vector<Advisory> advisories = new Vector<>();
		for(int i = 0; i < groups.size(); i++) {
			advisories.add(new Advisory(groups.get(i), "Advisor " + i));
		}
		return advisories;
	}

	public void setMenu() {
		setJMenuBar(new ToolBar(this)); //accessible with getJMenuBar();
	}
}

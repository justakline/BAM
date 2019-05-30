import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

public class GUI extends JFrame implements Serializable {

	public Vector<StudentPage> displayedStudentPages;
	private AdvisorySelectionPanel leftPanel;
	private AdvisoryDisplayPanel rightPanel;
	private WelcomeWindow welcomeWindow;
	private SetupWindow setupWindow;
	private SettingsWindow settingsWindow;

	private float friendGroupValue;
	private float interestValue;

	public GUI() {
		super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
        this.setPreferredSize(new Dimension(700, 700));
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

        friendGroupValue = setupWindow.getFriendValue();
        interestValue = setupWindow.getInterestValue();
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

		setTitle("BAM!!!");


    }

	public AdvisoryDisplayPanel getRightPanel() {
		return rightPanel;
	}

	public AdvisorySelectionPanel getLeftPanel() {
		return leftPanel;
	}

}

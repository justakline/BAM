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
	private JButton importAll;

	private JCheckBox studentBox, friendsBox, interestsBox, settingsBox, importAllBox;

	private File studentCSV;
	private File activitiesCSV;
	private File friendsCSV;
	private File masterCSV;
	private GUI host;
	private Vector<JButton> butt = new Vector<>();
	private SettingsWindow settingsWindow;
	private JProgressBar progressBar;

	private JFrame realSettings, progressFrame;

	private float friendValue;
	private float interestValue;
	private float advNumb;
	private boolean isWorking;

	public SetupWindow(GUI host) {
		super("Setup");
		isWorking = true;
		setSize(new Dimension(host.getWidth(), host.getHeight()));
		setLayout(new GridLayout(6, 1));

		Panel zero = new Panel();
		zero.setLayout(new GridLayout(1, 2));
		Panel one = new Panel();
		one.setLayout(new GridLayout(1, 2));
		Panel two = new Panel();
		two.setLayout(new GridLayout(1, 2));
		Panel three = new Panel();
		three.setLayout(new GridLayout(1, 2));
		Panel four = new Panel();
		four.setLayout(new GridLayout(1, 2));
		Panel five = new Panel();
		five.setLayout(new GridLayout(1, 2));

		three.setLayout(new GridLayout(1, 3));
		students = new JButton("Import Students");
		friends = new JButton("Import Friends");
		interests = new JButton("Import Interests");
		settings = new JButton("Open Settings");
		importAll = new JButton("Import All");
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
		importAllBox = new JCheckBox();
		importAllBox.addActionListener(this);
		importAllBox.setEnabled(false);
		settingsBox = new JCheckBox();
		settingsBox.addActionListener(this);
		settingsBox.setEnabled(false);


		students.addActionListener(this);
		friends.addActionListener(this);
		settings.addActionListener(this);
		importAll.addActionListener(this);
		run.addActionListener(this);
		interests.addActionListener(this);
		this.butt = new Vector<>();
		butt.add(students);
		butt.add(friends);
		butt.add(interests);
		butt.add(importAll);
		butt.add(settings);
		butt.add(run);
		for(JButton b : butt) {
			b.setPreferredSize(new Dimension(400, 100));
			b.setHorizontalAlignment(JTextField.CENTER);
		}


		butt.get(0).setFont(new Font("Import Students", 1, 20));
		butt.get(1).setFont(new Font("Import Friends", 1, 20));
		butt.get(2).setFont(new Font("Import Interests", 1, 20));
		butt.get(3).setFont(new Font("Import All", 1, 20));
		butt.get(4).setFont(new Font("Open Settings", 1, 20));
		butt.get(5).setFont(new Font("Run", 1, 20));


//        students = new JButton(butt.get(0));
		zero.add(butt.get(0));
		zero.add(studentBox);
		one.add(butt.get(1));
		one.add(friendsBox);
		two.add(butt.get(2));
		two.add(interestsBox);
		three.add(butt.get(3));
		three.add(importAllBox);
		four.add(butt.get(4));
		four.add(settingsBox);
		five.add(butt.get(5));
		add(zero);
		add(one);
		add(two);
		add(three);
		add(four);
		add(five);

		fc = new JFileChooser() {
			@Override
			public void setFileFilter(FileFilter filter) {
				super.setFileFilter(new FileNameExtensionFilter(".CSV files", "CSV", "csv"));
			}
		};
		fc.setDialogTitle("Specify a .CSV file containing the appropriate values");
		this.host = host;
		setVisible(true);

		settingsWindow = new SettingsWindow(this);

		progressFrame = new JFrame("Progress");

		progressFrame.setSize(new Dimension(200, 50));
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);

		progressFrame.add(progressBar);
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();

		if((source.equals(students))) {
			if(fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
				studentCSV = fc.getSelectedFile();
				studentBox.setSelected(true);

			}
		} else if((source.equals(interests))) {
			if(fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
				activitiesCSV = fc.getSelectedFile();
				interestsBox.setSelected(true);
				System.out.println("activities: " + getActivitiesCSV());
			}
		} else if((source.equals(friends))) {
			if(fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
				friendsCSV = fc.getSelectedFile();
				friendsBox.setSelected(true);

			}
		} else if((source.equals(importAll))) {
			if(fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
				masterCSV = fc.getSelectedFile();
				friendsBox.setSelected(true);
				interestsBox.setSelected(true);
				studentBox.setSelected(true);
				importAllBox.setSelected(true);

			}
		} else if((source.equals(settings))) {
			settingsWindow.setVisible(true);
		}
//Wait for all toher checkMarks to be hit, then leave
		else if(source.equals(run)) {
			if(interestsBox.isSelected() && friendsBox.isSelected() && settingsBox.isSelected() && studentBox.isSelected()) {
				host.runAlgorithm();
				host.showPanels(host.getAl().getAdvisories());
			}
		}

		if(interestsBox.isSelected() && friendsBox.isSelected() && studentBox.isSelected()) {
			importAllBox.setSelected(true);
		}

	}

	public void initializeValues(Vector<Float> vals) {
		friendValue = vals.get(0);
		interestValue = vals.get(1);
		advNumb = vals.get(2);
		System.out.println(vals.get(2));
		System.out.println("Values initialized");
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

	public SettingsWindow getSettingsWindow() {
		return settingsWindow;
	}

	public JFrame getProgressFrame() {
		return progressFrame;
	}

	public JCheckBox getSettingsBox() {
		return settingsBox;
	}

	public File getStudentCSV() {
		return studentCSV;
	}

	public File getActivitiesCSV() {
		return activitiesCSV;
	}

	public File getFriendsCSV() {
		return friendsCSV;
	}

	public File getMasterCSV() {
		return masterCSV;
	}

	public float getAdvNumb() {
		return advNumb;
	}
}

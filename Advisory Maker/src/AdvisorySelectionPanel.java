import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

public class AdvisorySelectionPanel extends JPanel implements Serializable {
	private Vector<AdvisorButton> advisors;
	private GUI host;

	public AdvisorySelectionPanel(GUI host) {
		super(new GridLayout(0, 1, 0, 0));
		advisors = new Vector<>();
		this.add(new JLabel("Advisories:", SwingConstants.CENTER));
		this.host = host;

		Vector<Vector<Student>> studentGroups = randDivAdv(host.getStudents());

		//Create AdvisorButtons
		for (int i = 0; i < TestCases.getAdvisorStringList().size(); i++) {
			AdvisorButton adv = new AdvisorButton(new Advisory(studentGroups.get(i), TestCases.getAdvisorStringList().get(i)));
			adv.addActionListener(host.getRightPanel());
			advisors.add(adv);
			this.add(adv);

			host.getRightPanel().addFrame(adv);
		}
	}

	public Vector<AdvisorButton> getAdvisors() {
		return advisors;
	}

	public void setAdvisories(Vector<Advisory> advisories) {
		for(int i = 0; i < advisors.size(); i++) {
			advisors.get(i).setAdvisory(advisories.get(i));
		}
	}

	private int largestAdvSize() {
		int size = 0;
		for (AdvisorButton advisor : advisors) {
			size = (advisor.getAdvisory().getStudents().size() > size) ? advisor.getAdvisory().getStudents().size() : size;
		}
		return size;
	} //71490

	public File getCSV() throws IOException {
		JFileChooser fc = new JFileChooser() {
			@Override
			public void setFileFilter(FileFilter filter) {
				super.setFileFilter(new FileNameExtensionFilter(".CSV files", "CSV", "csv"));
			}
		};
		fc.setDialogTitle("Specify a file to save");
		File csv = new File(System.getProperty("user.home"), "Advisories.csv");
		if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			csv = new File(fc.getSelectedFile().getAbsoluteFile() + ".csv");
		}

		FileOutputStream stream = new FileOutputStream(csv);
		StringBuilder data = new StringBuilder();
		//file row one with advisor names, newline
		//for loop going from 1 to longest length,iterating over advisors
		//for loop going from 0 to advisors.size() (iterating across)
		//get name of Student in row,newline
		for (AdvisorButton advisor : advisors) {
			data.append(String.format("%s,", advisor.getName()));
		}
		data.append("\n");
		for (int i = 1; i < largestAdvSize(); i++) {
			for(int j = 0; j < advisors.get(i).getAdvisory().getStudents().size(); j++) {
				try {
					data.append(String.format("%s,", advisors.get(j).getAdvisory().getStudents().get(i).getName()));
				} catch(Exception e) {

				}
			}
			data.append("\n");
		}
		stream.write(data.toString().getBytes());
		return csv;
	}

	public Vector<Advisory> getAdvisories() {
		Vector<Advisory> advisories = new Vector<>();
		for (AdvisorButton advisor : advisors) {
			advisories.add(advisor.getAdvisory());
		}
		return advisories;
	}

	private Vector<Vector<Student>> randDivAdv(Vector<Student> students) {
		int n = TestCases.getAdvisorStringList().size();
		Vector<Vector<Student>> groups = new Vector<>();
		for (int i = 0; i < n; i++) {
			groups.add(new Vector<>());
		}
		for (int i = 0; i < students.size(); i++) {
			groups.get(i % n).add(students.get(i));
		}
		return groups;
	}

	public void addButton(AdvisorButton b) {
		advisors.add(b);
		host.getRightPanel().addFrame(b);
	}
}
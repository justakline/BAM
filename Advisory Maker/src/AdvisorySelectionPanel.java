import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

public class AdvisorySelectionPanel extends JPanel implements Serializable {
	private Vector<AdvisorButton> advisors;
	private GUI host;

	public AdvisorySelectionPanel(GUI host, Vector<Advisory> advisories) {
		super(new GridLayout(0, 1, 0, 0));
		advisors = new Vector<>();
		this.add(new JLabel("Advisories:", SwingConstants.CENTER));
		this.host = host;


		//Create AdvisorButtons
		for(int i = 0; i < advisories.size(); i++) {
			AdvisorButton adv = new AdvisorButton(advisories.get(i));
			//AdvisorButton adv = new AdvisorButton(new Advisory(studentGroups.get(i), TestCases.getAdvisorStringList().get(i)));
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
		File csv = new File("C:/Users/ryanl/Documents/export.csv");
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
			for (int j = 0; j < advisors.size(); j++) {
				data.append(String.format("%s,", advisors.get(j).getAdvisory().getStudents().get(i).getName()));
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


	public void addButton(AdvisorButton b) {
		advisors.add(b);
		host.getRightPanel().addFrame(b);
	}
}
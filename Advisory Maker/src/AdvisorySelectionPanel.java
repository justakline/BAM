import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

//This class is where the buttons on the left side live

public class AdvisorySelectionPanel extends JPanel implements Serializable {
	private Vector<AdvisorButton> advisors;
	private GUI host;

	public AdvisorySelectionPanel(GUI host, Vector<Advisory> advisories) {
		super(new GridLayout(0, 1, 0, 0));
		advisors = new Vector<>();
		this.add(new JLabel("Advisories:", SwingConstants.CENTER));
		this.host = host;

		//Create AdvisorButtons and make the GUI listen for changes
		for(int i = 0; i < advisories.size(); i++) {
			AdvisorButton adv = new AdvisorButton(advisories.get(i));
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
		//StringBuilder data = new StringBuilder();

		//file row one with advisor names, newline
		//for loop going from 1 to longest length,iterating over advisors
		//for loop going from 0 to advisors.size() (iterating across)
		//get name of Student in row,newline
		String[][] data = new String[advisors.size()][];

		//for eqch advisor button create the corresponding String array of name

		for(int i = 0; i < advisors.size(); i++) {
			Advisory adv = advisors.get(i).getAdvisory();
			String[] advisory = new String[adv.getStudents().size() + 1];

			advisory[0] = adv.getAdvisor() + ",";
			for(int j = 0; j < adv.getStudents().size(); j++) {
				advisory[j + 1] = adv.getStudents().get(j).getName() + ",";
			}
			data[i] = advisory;
		}


//		for (AdvisorButton advisor : advisors) {
//			data.append(String.format("%s,", advisor.getName()));
//		}
//		data.append("\n");
//		for(int i = 0; i < advisors.size(); i++) {
//			Advisory currentAdv = advisors.get(i).getAdvisory();
//			Vector<Student> students = currentAdv.getStudents();
//			for(int j = 0; j < students.size(); j++) {
//				try {
//					data.append(String.format("%s,", students.get(j).getName()));
//				} catch(Exception e) {
//
//				}
//			}
//			data.append("\n");
//		}
		//transpose data
//		String temp;
//		for(int i=0 ; i<(data.length/2 + 1); i++)
//		{
//			for(int j=i ; j<(data[0].length) ; j++)
//			{
//				temp = data[i][j];
//				data[i][j] = data[j][i];
//				data[j][i] = temp;
//			}
//		}
//ATTEMPT TO TRANSPOSE:
//		String[][] transpose = new String[data.length][];
//		for(int i = 0; i < transpose.length; i++) {
//			transpose[i] = new String[data[0].length];
//			for(int j = 0; j < data[i].length; j++) {
//				try {
//					transpose[i][j] = data[j][i];
//				} catch(Exception e) {
//					System.out.println("i = " + i);
//					System.out.println("j = " + j);
//					System.out.println("data[i][j] = " + data[i][j]);
//				}
//			}
//		}
//
//		data = transpose;
		String output = "";

		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				output += data[i][j];
			}
			output += "\n";
		}
		System.out.println(output);

		stream.write(output.getBytes());
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
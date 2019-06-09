import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AdvisoryFrame extends JInternalFrame {

    private Advisory advisory;
	private Vector<StudentLabel> labels;
	private int default_cell_width = 200;
	private int default_cell_height = 50;
	private AdvisoryDisplayPanel host;


	public AdvisoryFrame(Advisory advisory, AdvisoryDisplayPanel host) {
		super(advisory.getAdvisor() + " Advisory", true, true);
		this.setAdvisory(advisory);
		this.host = host;
		int numStudents = advisory.getStudents().size();

		setLayout(new GridLayout(15, 1));
		setSize(default_cell_width, default_cell_height * numStudents);

		//createTable();
		labels = new Vector<>();

		//Student Labels are used for the drag and Drop, do not touch
		for (Student student : advisory.getStudents()) {
			StudentLabel label = new StudentLabel(student);
			labels.add(label);

			label.setBorder(new LineBorder(Color.BLACK));
			label.setBackground(Color.WHITE);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);

			label.addMouseListener(new MouseAdapter() {//Has a mouse been pressed?
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() ==2) { //Double clicking

						StudentPage newStudentPage = new StudentPage(label.getStudent());
						host.getStudentPages().add(newStudentPage);
						host.add(newStudentPage);
					}
				}
			});
			// Add the JLabel to the JInternalFrame
			this.add(labels.get(labels.indexOf(label)));
		}
        this.setVisible(true);
		this.setIconifiable(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.addInternalFrameListener(host);
    }

	public Advisory getAdvisory() {
		return advisory;
    }

	public Vector<StudentLabel> getLabels() {
		return labels;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}


	//Update the Labels
	public void updLabels() {
		for(StudentLabel label : labels) {
			this.remove(label);
		}
		labels.clear();
		for(Student student : advisory.getStudents()) {
			StudentLabel label;
			labels.add(label = new StudentLabel(student));

			label.setBorder(new LineBorder(Color.BLACK));
			label.setBackground(Color.WHITE);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);

			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2) {
						// might have to change argument to ((StudentLabel)e.getSource()).getStudent();
						StudentPage newStudentPage = new StudentPage(label.getStudent());
						host.getStudentPages().add(newStudentPage);
						host.add(newStudentPage);
					}
				}
			});

			// Add the JLabel to the JInternalFrame
			this.add(label);
		}
	}
    public void removeLabel(int index){
		labels.remove(index);

	}

}

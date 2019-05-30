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


	public AdvisoryFrame(Advisory advisory, AdvisoryDisplayPanel host) {
		super(advisory.getAdvisor() + " Advisory", true, true);
		this.advisory = advisory;
		int numStudents = advisory.getStudents().size();

		setLayout(new GridLayout(numStudents, 1));
		setSize(default_cell_width, default_cell_height * numStudents);

		//createTable();
		labels = new Vector<>();
		for (Student student : advisory.getStudents()) {
			StudentLabel label = new StudentLabel(student);
			labels.add(label);

			label.setBorder(new LineBorder(Color.BLACK));
			label.setBackground(Color.WHITE);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);

			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() ==2) {
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

}

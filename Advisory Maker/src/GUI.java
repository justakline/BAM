import javax.swing.*;
import java.util.Vector;

public class GUI extends JFrame {

	public Vector<StudentPage> displayedStudentPages;
	private AdvisorySelectionPanel leftPanel;
	private AdvisoryDisplayPanel rightPanel;

	public GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        displayedStudentPages = new Vector<>();

        //Init Components in this block:
		rightPanel = new AdvisoryDisplayPanel();
		leftPanel = new AdvisorySelectionPanel(this);
		this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel));

        //AddComponents in this block:
		setJMenuBar(new ToolBar(this)); //accessible with getJMenuBar();

        //Attributes of JFrame
        this.setSize(800, 800);
        this.setVisible(true);
		setTitle("BAM!!!");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

	public AdvisoryDisplayPanel getRightPanel() {
		return rightPanel;
	}

}

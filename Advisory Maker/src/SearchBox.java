import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Vector;

public class SearchBox extends JInternalFrame {
	private final JTextField tf;
	private final JComboBox combo = new JComboBox();
	private final Vector<String> students = new Vector<>();
	private boolean hide_flag = false;

	public SearchBox() {
		super("Find a student:", false, true, false, false);
		combo.setEditable(true);
		tf = (JTextField) combo.getEditor().getEditorComponent();
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				EventQueue.invokeLater(() -> {
					String text = tf.getText();
					if (text.length() == 0) {
						combo.hidePopup();
						setModel(new DefaultComboBoxModel(students), "");
					} else {
						DefaultComboBoxModel m = getSuggestedModel(students, text);
						if (m.getSize() == 0 || hide_flag) {
//							combo.hidePopup();
							hide_flag = false;
						} else {
							setModel(m, text);
							combo.showPopup();
						}
					}
				});
			}

			public void keyPressed(KeyEvent e) {
				String text = tf.getText();
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ENTER) {
					if (!students.contains(text)) {
						students.addElement(text);
						Collections.sort(students);
						setModel(getSuggestedModel(students, text), text);
					}
					hide_flag = true;
				} else if (code == KeyEvent.VK_ESCAPE) {
					hide_flag = true;
				} else if (code == KeyEvent.VK_RIGHT) {
					for (int i = 0; i < students.size(); i++) {
						String str = students.elementAt(i);
						if (str.startsWith(text)) {
							combo.setSelectedIndex(-1);
							tf.setText(str);
//							return;
						}
					}
				}
			}
		});


		setModel(new DefaultComboBoxModel(students), "");
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createTitledBorder("AutoSuggestion Box"));
		p.add(combo, BorderLayout.NORTH);
		add(p);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(300, 150));
	}

	private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
		DefaultComboBoxModel m = new DefaultComboBoxModel();
		for (String s : list) {
			if (s.startsWith(text)) m.addElement(s);
		}
		return m;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new SearchBox());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void setModel(DefaultComboBoxModel mdl, String str) {
		combo.setModel(mdl);
		combo.setSelectedIndex(-1);
		tf.setText(str);
	}
}
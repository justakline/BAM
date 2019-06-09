import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SettingsWindow extends JFrame implements ActionListener {

	private JPanel up;
	private JPanel middle;
	private JPanel down;
	private JButton defaultButton;
	private JButton confirm;
	private SetupWindow host;
	private JSpinner friendGroupNumb;
	private JSpinner interestsNumb;
	private JSpinner advNumb;

	public SettingsWindow(SetupWindow host) {
		super();
		setSize(new Dimension(500, 500));
		setLayout(new GridLayout(3, 1));
		this.host = host;


		JTextArea title = new JTextArea();
		title.setPreferredSize(new Dimension(475, 125));
		title.setLineWrap(true);
		title.append("From 1-100, Please Rank the Importance of the   following in determining quality advisories. The higher the number, the more important the         attribute is.");
		title.setFont(new Font("From 1-100, Please Rank the Importance of the   following in determining quality advisories. The higher the number, the more important the         attribute is.", 1, 20));


		JTextField friendGroup = new JTextField("Friend Group");
		friendGroup.setFont(new Font("Friends Group", 1, 20));
		friendGroup.setEditable(false);
		friendGroup.setPreferredSize(new Dimension(400, 200));
		friendGroup.setHorizontalAlignment(JTextField.CENTER);

		JTextField interests = new JTextField("Common Interests");
		interests.setFont(new Font("Common Interests", 1, 20));
		interests.setEditable(false);
		interests.setPreferredSize(new Dimension(400, 200));
		interests.setHorizontalAlignment(JTextField.CENTER);

		JTextField advisories = new JTextField("Number of Advisories");
		advisories.setFont(new Font("Advisory Number", 1, 20));
		advisories.setEditable(false);
		advisories.setPreferredSize(new Dimension(400, 200));
		advisories.setHorizontalAlignment(JTextField.CENTER);

		friendGroupNumb = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		friendGroupNumb.setFont(new Font("Font", 1, 40));
		interestsNumb = new JSpinner(new SpinnerNumberModel(2, 0, 100, 1));
		interestsNumb.setFont(new Font("Int", 1, 40));
		advNumb = new JSpinner(new SpinnerNumberModel(20, 0, 30, 1));
		advNumb.setFont(new Font("Int", 1, 40));

		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		confirm.setFont(new Font("confirm", 1, 25));

		defaultButton = new JButton("Make Default");
		defaultButton.addActionListener(this);
		defaultButton.setFont(new Font("confirm", 1, 25));


		up = new JPanel();
		up.add(title);
		middle = new JPanel();
		middle.setLayout(new GridLayout(3, 3));
		middle.add(friendGroup);
		middle.add(friendGroupNumb);
		middle.add(interests);
		middle.add(interestsNumb);
		middle.add(advisories);
		middle.add(advNumb);
		down = new JPanel();
		down.setLayout(new GridLayout());
		down.setSize(this.getWidth() / 5, this.getHeight() / 2);
		down.add(defaultButton);
		down.add(confirm);


		add(up);
		add(middle);
		add(down);
		setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Set it the values to the default
		if(e.getSource().equals(defaultButton)) {
			friendGroupNumb.setValue(10);
			interestsNumb.setValue(2);
			advNumb.setValue(15);
		}//Use the values for the program
		else if(e.getSource().equals(confirm)) {
			Vector<Float> vals = new Vector<>();
			Object v = friendGroupNumb.getValue();
			Integer i = (Integer) v;
			Float friend = i.floatValue();

			Object o = interestsNumb.getValue();
			Integer j = (Integer) o;
			Float interest = j.floatValue();

			Object w = advNumb.getValue();
			Integer k = (Integer) w;
			Float advNumb = k.floatValue();


			System.out.println("friend = " + friend);
			vals.add(friend);
			vals.add(interest);
			vals.add(advNumb);
			host.initializeValues(vals);
			host.getSettingsWindow().setVisible(false);
			host.getSettingsBox().setSelected(true);
			System.out.println("Confirm");

		}
	}
}

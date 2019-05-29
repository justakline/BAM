import javax.swing.*;
import java.awt.*;

public class WelcomeWindow extends JInternalFrame {

    private JTextField text;
    private JButton howTo;
    private JButton start;

    public WelcomeWindow() {
        super();
        setSize(new Dimension(400, 400));
        setLayout(new GridLayout(2,1));

        Panel up = new Panel();
        Panel down = new Panel();

        Panel downUp = new Panel();
        Panel downDown = new Panel();


        text = new JTextField("Welcome to BAM!");
        text.setFont(new Font("Weclome to BAM", 1, 40));
        text.setEditable(false);;
        text.setPreferredSize(new Dimension(400, 200));
        text.setHorizontalAlignment(JTextField.CENTER);
        howTo = new JButton("How To Use");
        howTo.setFont(new Font("How To Use" , 1, 20));
        howTo.setPreferredSize(new Dimension(200,100));
        start = new JButton("Start");
        start.setFont(new Font("Start" , 1, 20));
        start.setPreferredSize(new Dimension(200,100));

//        start.action;

        up.add(text);
        downUp.add(howTo);
        downDown.add(start);

        down.add(downUp);
        down.add(downDown);
        add(up);
        add(down);

        setVisible(true);

    }


}

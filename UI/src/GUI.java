import javax.swing.*;

public class GUI extends JFrame {

    static final JFrame root = new JFrame();
    private JInternalFrame frame1 = new JInternalFrame();

    public GUI() {
        setTitle("Hello There!");
        setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GUI test = new GUI();
        test.setVisible(true);
        JInternalFrame frame2 = new JInternalFrame();

        frame2.setTitle("here I stand");

        JButton button1 = new JButton("a button");


        ToolBar toolBar = new ToolBar();
        JPanel panel = new JPanel();
        panel.add(button1);

        frame2.setVisible(true);
        frame2.add(panel);
        root.add(frame2);
        root.setVisible(true);






    }
}

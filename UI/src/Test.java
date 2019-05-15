import javax.swing.*;

public class Test extends JFrame {

    private JPanel root = new JPanel();
    private JButton button = new JButton();
    private JButton button1 = new JButton();

    public Test() {

        add(root);
        setTitle("Hello There!");
        setSize(400, 400);
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.isVisible();
        int pi = 3;
        System.out.println("pi = " + pi + " lol");
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.FlowLayout.LEFT;

public class GUI extends JFrame {

    static final JFrame root = new JFrame();
    private JInternalFrame frame1 = new JInternalFrame();

    public GUI() {
        setTitle("Hello There!");
        setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel panel=new JPanel();


        ToolBar toolBar= new ToolBar();




        this.setSize(400,400);
        this.setLayout(null);
        this.setVisible(true);
    setJMenuBar(toolBar);




//
//        ToolBar toolBar = new ToolBar();
//        JPanel panel = new JPanel(new FlowLayout());
//        panel.setBounds(40,80,10,10);
////        panel.setBackground(Color.BLUE);
//
//        panel.add(toolBar);
//        this.add(panel);





    }

    public static void main(String[] args) {
        GUI test = new GUI();
        test.setVisible(true);





    }
}

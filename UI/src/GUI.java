import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.FlowLayout.LEFT;

public class GUI extends JFrame {

    private JInternalFrame frame1 = new JInternalFrame();

    public GUI() {
        setTitle("Hello There!");
        setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel panel=new JPanel();



        ToolBar toolBar= new ToolBar();
        JList b;

        //create a new label
        JLabel l= new JLabel("select the day of the week");

        //String array to store weekdays
        String week[]= { "Monday","Tuesday","Wednesday",
                "Thursday","Friday","Saturday","Sunday"};

        //create list
        b= new JList(week);

        //set a selected index
        b.setSelectedIndex(2);

        //add list to panel
        panel.add(b);

        add(panel);

        //set the size of frame
        AdvisoryList advisoryList = new AdvisoryList();
        panel.add(advisoryList);


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

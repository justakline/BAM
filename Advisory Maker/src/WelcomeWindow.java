import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class WelcomeWindow extends JInternalFrame implements ActionListener {

    private JTextField text;
    private JButton howTo;
    private JButton start;
    private JButton previous;
    private boolean isWorking;
    private SetupWindow setupWindow;
    private GUI host;

    public WelcomeWindow(GUI host) {
        super();
        setSize(new Dimension(host.getWidth(), host.getHeight()));
        setLayout(new GridLayout(2,1));
        this.host = host;
        isWorking = true;

        Panel up = new Panel();
        Panel down = new Panel();

        Panel downRight = new Panel();
        Panel downLeft = new Panel();


        text = new JTextField("BAM!");
        text.setFont(new Font("BAM", 1, 80));
        text.setEditable(false);;
        text.setPreferredSize(new Dimension(400, 200));
        text.setHorizontalAlignment(JTextField.CENTER);
        howTo = new JButton("How To Use");
        howTo.setFont(new Font("How To Use" , 1, 20));
        howTo.setPreferredSize(new Dimension(200,100));
        howTo.addActionListener(this);
        start = new JButton("Start");
        start.setFont(new Font("Start" , 1, 20));
        start.setPreferredSize(new Dimension(200,100));
        start.addActionListener(this);



//        start.action;

        up.add(text);

        downRight.add(howTo);
        downLeft.add(start);




        down.add(downRight);
        down.add(downLeft);
        add(up);
        add(down);

        setVisible(true);
    }

//    public boolean isWorking() {
//
//        return isWorking;
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if(source.equals(start)){
            System.out.println("False");
//            isWorking = false;
            host.showSetup();

//
        }else if(source.equals(previous)){
            System.out.println("Try........................");

            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("intial_save.ser"));
                host = (GUI)in.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if(source.equals(howTo)){
            if (Desktop.isDesktopSupported()) {
                try {
                    String currentDir = System.getProperty("user.dir");
                    currentDir = currentDir+"/resources/The Best Advisory Maker How To Guide.pdf";
                    System.out.println("Current dir using System:" +currentDir);
                    if ((new File(currentDir)).exists())
                    {
                        Process p = Runtime
                                .getRuntime()
                                .exec("rundll32 url.dll,FileProtocolHandler " +currentDir);
                        p.waitFor();
                    }
                    else
                    {
                        System.out.println("File is not exists");
                    }
                    System.out.println("Done");
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }


}

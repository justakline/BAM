import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ToolBar extends JMenuBar {

    public ToolBar(){
        super();


        //Creation fo the file Tab
        JMenu file = new JMenu("File");

        file.setMnemonic(KeyEvent.VK_A);
        file.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

            //Creation of teh "New" Sub menu
            JMenuItem newThing = new JMenuItem("New");
            JMenuItem open = new JMenuItem("Open");
            JMenuItem print = new JMenuItem("Print");

        file.add(newThing);
        file.add(open);
        file.add(print);
        this.add(file);




        JMenu load = new JMenu("Load");

        file.setMnemonic(KeyEvent.VK_A);
        file.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        this.add(load);


        JMenu run = new JMenu("Run");

        file.setMnemonic(KeyEvent.VK_A);
        file.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        this.add(run);

        JMenu help = new JMenu("Help");

        file.setMnemonic(KeyEvent.VK_A);
        file.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        this.add(help);

    }


}

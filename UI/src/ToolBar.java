import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ToolBar extends JMenuBar {





    public ToolBar(){
        super();

        JMenu file = new JMenu("File");

        file.setMnemonic(KeyEvent.VK_A);
        file.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        JMenuItem newThing = new JMenuItem("New");
        file.add(newThing);
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

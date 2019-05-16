import javax.swing.*;
import java.awt.event.KeyEvent;

public class ToolBar extends JMenuBar {
    public JMenu menu;

    public ToolBar(){
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        this.add(menu);
    }
}

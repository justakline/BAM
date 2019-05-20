import javax.swing.*;

public class ToolBar extends JMenuBar {

    public ToolBar(){
        super();

        //Creation of the file Tab and associated submenu items
        JMenu file = new JMenu("File");
        file.add(new JMenuItem("New"));
        file.add(new JMenuItem("Open"));
        file.add(new JMenuItem("Print"));
        this.add(file);

        this.add(new JMenu("Load"));
        this.add(new JMenu("Run"));
        this.add(new JMenu("Help"));
    }


}

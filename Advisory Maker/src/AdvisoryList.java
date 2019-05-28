import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Vector;

public class AdvisoryList extends JList {
    Vector<Advisory> advisories;

    public AdvisoryList(Vector<Advisory> advisories, JPanel panel) {
        super(advisories);
        this.advisories = advisories;
        this.setListData(this.advisories);
        this.addListSelectionListener(new ListSelectionHandler(panel));
    }

    public class ListSelectionHandler implements ListSelectionListener {
        JPanel panel;

        public ListSelectionHandler(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {

            ListSelectionModel lsm = (ListSelectionModel) e.getSource();


        }
    }
}

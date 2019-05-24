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
             /*   ListSelectionModel lsm = (ListSelectionModel)e.getSource();

                int firstIndex = e.getFirstIndex();
                int lastIndex = e.getLastIndex();
                boolean isAdjusting = e.getValueIsAdjusting();
                System.output.append("Event for indexes "
                        + firstIndex + " - " + lastIndex
                        + "; isAdjusting is " + isAdjusting
                        + "; selected indexes:");

                if (lsm.isSelectionEmpty()) {
                    output.append(" <none>");
                } else {
                    // Find out which indexes are selected.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) {
                            output.append(" " + i);
                        }
                    }
                }
                output.append(newline);
        }
        */
    }
}

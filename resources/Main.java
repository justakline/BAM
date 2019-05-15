/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dnd;

import java.awt.BorderLayout;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

/**
 *
 * @author smithk
 */
public class Main extends JFrame {
    public Main() {
        setSize (100, 400);
        setLocation (100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout (new BorderLayout());

        JLabel src = new JLabel (new ImageIcon ("test/CD.gif"));
        add (src, BorderLayout.NORTH);

        TransferHandler xfrh = new TransferHandler("icon") {
            public void exportDone(JComponent src, Transferable data, int action) {
                super.exportDone (src, data, action);
                System.out.println("Action " + action);
                if (action == TransferHandler.COPY)
                    ((JLabel) src).setIcon(null);
            }
            public boolean canImport (TransferHandler.TransferSupport support) {
                if (support.getComponent() instanceof JLabel)
                    return true;
                return false;
            }
        };
        src.setTransferHandler (xfrh);
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent mev) {
                JLabel src = (JLabel) mev.getSource();
                src.getTransferHandler().exportAsDrag(src, mev, TransferHandler.COPY);
            }
        };
        src.addMouseListener(ml);


        JLabel dst = new JLabel (new ImageIcon ("test/debug.jpg"));
        dst.setText ("Drop Here");
        add (dst, BorderLayout.SOUTH);

        dst.setTransferHandler (xfrh);
        dst.addMouseListener(ml);

        setVisible (true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

}

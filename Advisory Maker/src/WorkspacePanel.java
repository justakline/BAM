import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.datatransfer.*;
import java.awt.dnd.*;

public class WorkspacePanel extends JDesktopPane implements ActionListener,
        DragGestureListener, DragSourceListener, DropTargetListener, Transferable
{
    private Vector<AdvisoryFrame> advisoryFrames;
    private Vector<StudentPage> studentPages;
    private Vector<DropTarget> dropTargets;
    private Vector<DragGestureRecognizer> dragRecogs;
    private DragSource dragSource;

    public WorkspacePanel() {
        super();
        studentPages = new Vector<>();
        this.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        this.setVisible(true);
        dropTargets = new Vector<>();
        dragRecogs = new Vector<>();
        dragSource = new DragSource();
    }


    // Call by AdvisorySelectionPanel when an advisory button is pressed.
    // Creates a new AdvisoryFrame, adds to WorkspacePanel, and attaches DND Listeners
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Click!");
        AdvisorButton source = (AdvisorButton)e.getSource();
        AdvisoryFrame newFrame = new AdvisoryFrame(source.getAdvisory(), this);
        attachListeners(newFrame);
        this.add(newFrame);
    }

    private void attachListeners(AdvisoryFrame frame)
    {
        dropTargets.add(new DropTarget(frame, DnDConstants.ACTION_MOVE, this));
        for(StudentLabel label : frame.getLabels())
            dragRecogs.add(dragSource.createDefaultDragGestureRecognizer(label, DnDConstants.ACTION_MOVE, this));
    }

    public Vector<StudentPage> getStudentPages()
    {
        return studentPages;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// DND BOILERPLATE - DO NOT TOUCH ////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static final DataFlavor[] supportedFlavors = {null};
    static {
        try {
            supportedFlavors[0] = new
                    DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    Object object;
    // Transferable methods.
    public Object getTransferData(DataFlavor flavor) {
        if (flavor.isMimeTypeEqual
                (DataFlavor.javaJVMLocalObjectMimeType)) {
            return object;
        } else {
            return null;
        }
    }
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.isMimeTypeEqual
                (DataFlavor.javaJVMLocalObjectMimeType);
    }
    // DragGestureListener method.
    public void dragGestureRecognized(DragGestureEvent ev) {
        ev.startDrag(null, this, this);
    }
    // DragSourceListener methods.
    public void dragDropEnd(DragSourceDropEvent ev) {
    }
    public void dragEnter(DragSourceDragEvent ev) {
    }
    public void dragExit(DragSourceEvent ev) {
    }
    public void dragOver(DragSourceDragEvent ev) {
        object = ev.getSource();
    }
    public void dropActionChanged(DragSourceDragEvent ev) {
    }
    // DropTargetListener methods.
    public void dragEnter(DropTargetDragEvent ev) {
    }
    public void dragExit(DropTargetEvent ev) {
    }
    public void dragOver(DropTargetDragEvent ev) {
        dropTargetDrag(ev);
    }
    public void dropActionChanged(DropTargetDragEvent ev) {
        dropTargetDrag(ev);
    }
    void dropTargetDrag(DropTargetDragEvent ev) {
        ev.acceptDrag(ev.getDropAction());
    }
    public void drop(DropTargetDropEvent ev) {
        ev.acceptDrop(ev.getDropAction());
        try {
            Object target = ev.getSource();
            Object source = ev.getTransferable().getTransferData
                    (supportedFlavors[0]);
            Component component = ((DragSourceContext)
                    source).getComponent();
            Container oldContainer = component.getParent();
            Container container = (Container) ((DropTarget)
                    target).getComponent();
            container.add(component);
            oldContainer.validate();
            oldContainer.repaint();
            container.validate();
            container.repaint();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        ev.dropComplete(true);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

@SuppressWarnings("unused")
public class DragDrop implements DragGestureListener,
                                         DragSourceListener,
                                         DropTargetListener, Transferable {
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ev.dropComplete(true);
    }

    public static void main(String[] arg) {

        // Create Components
        Button button = new Button("Drag this button");
        Label label = new Label("Drag this label");
        Checkbox checkbox = new Checkbox("Drag this check box");
        CheckboxGroup radiobutton = new CheckboxGroup();
        Checkbox checkbox1 = new Checkbox("Drag this check box",
                radiobutton, false);
        Choice country = new Choice();
        country.add("India");
        country.add("US");
        country.add("Australia");

        // Create frames
        JFrame source_frame = new JFrame("Source Frame");
        source_frame.setLayout(new FlowLayout());
        source_frame.add(button);
        source_frame.add(label);
        source_frame.add(checkbox);
        source_frame.add(checkbox1);
        source_frame.add(country);

        JFrame target = new JFrame("Target Frame");
        target.setLayout(new FlowLayout());

        JFrame target2 = new JFrame("Target Frame 2");
        target2.setLayout(new FlowLayout());

        // Create main DragDrop Listener
        DragDrop dndListener = new DragDrop();

        // Every frame gets 1 DropTarget linked to a DragDrop Listener (this)
        DropTarget dropTarget1 = new DropTarget(source_frame,
                DnDConstants.ACTION_MOVE, dndListener);
        DropTarget dropTarget2 = new DropTarget(target,
                DnDConstants.ACTION_MOVE, dndListener);
        DropTarget dropTarget3 = new DropTarget(target2,
                DnDConstants.ACTION_MOVE, dndListener);

        // Every draggable object (Component) gets 1 DragGestureRecognizer, linked to a DragSource
        // and a DragDrop Listener (this)
        DragSource dragSource = new DragSource();
        DragGestureRecognizer dragRecognizer1 = dragSource.
                                                                  createDefaultDragGestureRecognizer(button,
                                                                          DnDConstants.ACTION_MOVE, dndListener);
        DragGestureRecognizer dragRecognizer2 = dragSource.
                                                                  createDefaultDragGestureRecognizer(label,
                                                                          DnDConstants.ACTION_MOVE, dndListener);
        DragGestureRecognizer dragRecognizer3 = dragSource.
                                                                  createDefaultDragGestureRecognizer(checkbox,
                                                                          DnDConstants.ACTION_MOVE, dndListener);
        DragGestureRecognizer dragRecognizer4 = dragSource.
                                                                  createDefaultDragGestureRecognizer(checkbox1,
                                                                          DnDConstants.ACTION_MOVE, dndListener);
        DragGestureRecognizer dragRecognizer5 = dragSource.
                                                                  createDefaultDragGestureRecognizer(country,
                                                                          DnDConstants.ACTION_MOVE, dndListener);

        // Size and show frames
        source_frame.setBounds(0, 200, 200, 200);
        target.setBounds(220, 200, 200, 200);
        target2.setBounds(440, 200, 200, 200);


        source_frame.setVisible(true);
        target.setVisible(true);
        target2.setVisible(true);
    }
} 
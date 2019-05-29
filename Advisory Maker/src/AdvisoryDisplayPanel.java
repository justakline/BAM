import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.Vector;

public class AdvisoryDisplayPanel extends JDesktopPane implements ActionListener, DragGestureListener, DragSourceListener,
		                                                                  DropTargetListener, Transferable, InternalFrameListener {
	private GUI host;
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// DND BOILERPLATE - DO NOT TOUCH ////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	static final DataFlavor[] supportedFlavors = {null};

	static {
		try {
			supportedFlavors[0] = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	Object object;
	private Vector<AdvisoryFrame> advisoryFrames;
	private Vector<StudentPage> studentPages;
	private Vector<DropTarget> dropTargets;
	private Vector<DragGestureRecognizer> dragRecogs;
	private DragSource dragSource;

	public AdvisoryDisplayPanel(GUI host) {
		super();
		studentPages = new Vector<>();
		this.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		this.setVisible(true);
		dropTargets = new Vector<>();
		dragRecogs = new Vector<>();
		dragSource = new DragSource();
		advisoryFrames = new Vector<>();
		this.host = host;
	}

	// Call by AdvisorySelectionPanel when an advisory button is pressed.
	// Creates a new AdvisoryFrame, adds to WorkspacePanel, and attaches DND Listeners
	@Override
	public void actionPerformed(ActionEvent e) {
		AdvisorButton source = (AdvisorButton) e.getSource();
		if (source.isSelected()) {
			findFrame(source.getAdvisory()).setVisible(true);
		} else {
			findFrame(source.getAdvisory()).setVisible(false);
		}
	}

	public void addFrame(AdvisorButton source) {
		AdvisoryFrame newFrame = new AdvisoryFrame(source.getAdvisory(), this);
		newFrame.setVisible(false);
		attachListeners(newFrame);
		advisoryFrames.add(newFrame);
		this.add(newFrame);
	}

	public void removeFrame(AdvisoryFrame frame) {
		dropTargets.removeElement(frame);
		for (StudentLabel label : frame.getLabels()) {
			dragRecogs.remove(label);
		}
		this.remove(frame);
	}

	private AdvisoryFrame findFrame(Advisory advisory) throws NoSuchElementException {
		for (AdvisoryFrame advisoryFrame : advisoryFrames) {
			if (advisoryFrame.getAdvisory().equals(advisory))
				return advisoryFrame;
		}
		throw new NoSuchElementException();
	}


	private void attachListeners(AdvisoryFrame frame) {
		dropTargets.add(new DropTarget(frame, DnDConstants.ACTION_MOVE, this));
		for (StudentLabel label : frame.getLabels()) {
			dragRecogs.add(dragSource.createDefaultDragGestureRecognizer(label, DnDConstants.ACTION_MOVE, this));
		}
	}

	public Vector<StudentPage> getStudentPages() {
		return studentPages;
	}

	// Transferable methods.
	public Object getTransferData(DataFlavor flavor) {
		if (flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType)) {
			return object;
		} else {
			return null;
		}
	}

	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType);
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
			Object source = ev.getTransferable().getTransferData(supportedFlavors[0]);
			Component component = ((DragSourceContext) source).getComponent();
			Container oldContainer = component.getParent();
			Container container = (Container) ((DropTarget) target).getComponent();
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

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		//Will be used to disable button upon closed AdvisoryFrame, TBIL
		AdvisoryFrame source = (AdvisoryFrame) e.getSource();
		for (AdvisorButton advisor : getHost().getLeftPanel().getAdvisors()) {
			if (advisor.getAdvisory().equals(source.getAdvisory())) {
				advisor.setSelected(false);
			}
		}
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {

	}

	public GUI getHost() {
		return host;
	}
}

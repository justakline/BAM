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
/////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////     IMPORTANT... DO NOT TOUCH DRAG AND DROP    ////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////
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
		Vector <Student> s = new Vector<>();
		AdvisorButton b = new AdvisorButton(new Advisory(s, "Test"));
		JComboBox<String> t = new JComboBox<>();
		JMenuItem m = new JMenuItem();
		if(e.getSource().getClass().getName().equals(b.getClass().getName())) {
			AdvisorButton source = (AdvisorButton) e.getSource();
			if (source.isSelected()) {
				findFrame(source.getAdvisory()).setVisible(true);
			} else {
				findFrame(source.getAdvisory()).setVisible(false);
			}
		}else if (e.getSource().getClass().getName().equals(t.getClass().getName())) {
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				boolean setLeave = false;
				for (AdvisoryFrame frame : advisoryFrames) {
					for (Student student : frame.getAdvisory().getStudents()) {
						if (source.getSelectedItem() == student.getName()) {
							frame.setVisible(true);
							System.out.println(frame.getAdvisory().getAdvisor());
						}
					}
				}



		}

	}

	//Creates a new Frame... duh
	public void addFrame(AdvisorButton source) {
		if(source.getAdvisory().getStudents().contains(TestCases.getFriendlyMaleStudentTest())) {
			source.setAdvisory(new Advisory(new Vector<>(), source.getAdvisory().getAdvisor()));
			System.out.println("CONTAINS");
		}
		AdvisoryFrame newFrame = new AdvisoryFrame(source.getAdvisory(), this);
		newFrame.setVisible(false);
		attachListeners(newFrame);
		advisoryFrames.add(newFrame);
		this.add(newFrame);

	}
//Remove Frame... Duh
	public void removeFrame(Advisory advisory) {

	    for(AdvisoryFrame frame : advisoryFrames){
	        if(frame.getAdvisory().equals(advisory)){
	            this.remove(frame.getLabels().get(0));
                frame.removeLabel(0);

            }
        }
    }

	public void removeFrame(AdvisoryFrame frame) {
		dropTargets.removeElement(frame);
		for (StudentLabel label : frame.getLabels()) {
			dragRecogs.remove(label);
		}
		this.remove(frame);
	}

	//Finds the frame for a specified advisory, used for the find student
	private AdvisoryFrame findFrame(Advisory advisory) throws NoSuchElementException {
		for (AdvisoryFrame advisoryFrame : advisoryFrames) {
			if (advisoryFrame.getAdvisory().equals(advisory))
//				advisoryFrame.moveToFront();
				return advisoryFrame;
		}
		throw new NoSuchElementException();
	}

//Do not touch
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
	//Do not touch
	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}
	//Do not touch
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
	//Do not touch
	public void dragEnter(DragSourceDragEvent ev) {
	}
	//Do not touch
	public void dragExit(DragSourceEvent ev) {
	}
	//Do not touch
	public void dragOver(DragSourceDragEvent ev) {
		object = ev.getSource();
	}
	//Do not touch
	public void dropActionChanged(DragSourceDragEvent ev) {
	}

	// DropTargetListener methods.
	public void dragEnter(DropTargetDragEvent ev) {
	}
	//Do not touch
	public void dragExit(DropTargetEvent ev) {
	}

	public void dragOver(DropTargetDragEvent ev) {
		dropTargetDrag(ev);
	}
	//Do not touch
	public void dropActionChanged(DropTargetDragEvent ev) {
		dropTargetDrag(ev);
	}
	//Do not touch
	void dropTargetDrag(DropTargetDragEvent ev) {
		ev.acceptDrag(ev.getDropAction());
	}
	//Do not touch, Drag and Drop works, but it's so complicated that we basically hvae no idea how
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
//finds student
	public void findStudent(Student find) {

            for(AdvisoryFrame advisorFrame: advisoryFrames){
                if(advisorFrame.getAdvisory().getStudents().contains(find)){
                    advisorFrame.show();
                }
            }



	}
	public Vector<AdvisoryFrame> getAdvisoryFrames() {
		return advisoryFrames;
	}

	public void addNewAdvisory(String advisor){
		Vector<Student> studs = new Vector<>();
		Advisory newAdvisory = new Advisory(studs, advisor);
		AdvisorButton but  = new AdvisorButton(newAdvisory);
		getHost().getLeftPanel().addButton(but);
		but.addActionListener(host.getRightPanel());
        getHost().getLeftPanel().addButton(but);
        getHost().getLeftPanel().add(but);
		addFrame(but);
		but.show();
	}
}

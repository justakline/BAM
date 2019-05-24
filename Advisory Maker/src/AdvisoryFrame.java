import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;
import java.util.Vector;

public class AdvisoryFrame extends JInternalFrame implements ListSelectionListener {

    private Advisory advisory;
    private JTable table;
    private Student[][] data;
    private JButton addStudent;
    public int row;
    public int col;

    public AdvisoryFrame(AdvisorButton button) {
        super(button.getName(), true, true);
        row = 0;
        col = 0;
        this.advisory = button.getAdvisory();
        this.setLayout(new FlowLayout());
        createTable();
        createButton();
        setConstraints();

    }

    public AdvisoryFrame(Advisory advisory){
        super("" + advisory.getAdvisor(), true, true);
        row= 0;
         col=0;
        this.advisory= advisory;

        this.setLayout(new FlowLayout());

        createTable();
        createButton();
        setConstraints();



    }

    private void setConstraints(){
        this.setSize(120, 40 * advisory.getStudents().size());
        this.setVisible(true);
        table.getSelectionModel().addListSelectionListener(this);
        setIconifiable(true);
    }

    private void createTable() {
        Object[] columnNames = {advisory.getAdvisor()};
        data = new Student[advisory.getStudents().size()][1];
         Object[][] tableData = new String[advisory.getStudents().size()][1];
        for(int i = 0; i < advisory.getStudents().size(); i ++){
            data[i][0] = advisory.getStudents().get(i);

            tableData[i][0] =advisory.getStudents().get(i).getName();

        }


        table= new JTable( tableData, columnNames);


        
        JScrollPane p = new JScrollPane(table);
        p.setPreferredSize(new Dimension(100,130));
        add(p);

        table.setDragEnabled(true);

        //instance table model

        table.setModel(new DefaultTableModel(tableData, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

    }

    public void createButton() {
        addStudent  = new JButton("+");

//        addStudent.setTransferHandler(new TransferHandler(){
//
//            public int getSourceActions(JComponent c) {
//                System.out.println("Get source");
//                System.out.println(c.getComponent(0));
//                return DnDConstants.ACTION_COPY_OR_MOVE;
//            }
//
//            public Transferable createTransferable(JComponent comp) {
//                System.out.println("Starting");
//                System.out.println(comp.getClass()+"");
//                JTable table=(JTable)comp;
//                row=table.getSelectedRow();
//                col=table.getSelectedColumn();
//                System.out.println("Row = " + row + "  Col = " + col);
//                String value = (String)table.getModel().getValueAt(row,col);
//                StringSelection transferable = new StringSelection(value);
//                table.getModel().setValueAt(null,row,col);
//                System.out.println("Transferab;e");
//                return transferable;
//            }
//            public boolean canImport(TransferHandler.TransferSupport info){
//
//                if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)){
//                    return false;
//                }
//
//                return true;
//            }
//
//            public boolean importData(TransferSupport support) {
//
//                System.out.println(getComponent(0).getName());
//                if (!support.isDrop()) {
//                    return false;
//                }
//
//                if (!canImport(support)) {
//                    return false;
//                }
//
//
//                JButton but=(JButton)support.getComponent();
//                System.out.println("Imported Data");
//                DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
//
//
////
//                System.out.println("BUT");
//
//
//
//                String data;
//                try {
//                    data = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
//                } catch (UnsupportedFlavorException e) {
//                    return false;
//                } catch (IOException e) {
//                    return false;
//                }
//                Vector<String> v = new Vector<>();
//
////                System.out.println(support.getComponent());
//                System.out.println( table.getRowCount());
//
//                v.add((String)(table.getValueAt(row, col)));
//
//                tableModel.addRow(v);
//
//
//                return true;
//            }
//
//        });
////        addStudent.setPreferredSize(new Dimension(getWidth(), getHeight()));
        Panel p = new Panel(new FlowLayout());
        p.add(addStudent);

        add(p);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Go");
        int rowIndex = table.getSelectedRow();
        int colIndex = table.getSelectedColumn();
        System.out.println(rowIndex + "  +  " + colIndex);
        System.out.println("Data[" + rowIndex +"][" + colIndex+"] =" + data[rowIndex][colIndex].getName());
        StudentPage newStudentPage = new StudentPage(data[rowIndex][colIndex]);



        if(!e.getValueIsAdjusting()){
            GUI.getRightPane().getStudentPages().add(newStudentPage);
            GUI.getRightPane().add(newStudentPage);
        }
//        if(data[rowIndex][colIndex].equals(advisory.getStudents().get(rowIndex-2)))
//       StudentPage studentPage = new StudentPage((Student) e.getSource());
    }
}

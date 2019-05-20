import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;

public class StudentPage extends JFrame  {
    private Student student;

    public StudentPage(Student student){
        super("Student Page");
        this.student = student;
        this.setSize(200, 300);
        this.setVisible(true);
        JPanel panel = new JPanel(new FlowLayout());
        JButton name = new JButton(student.getName());
        DefaultListModel listModel = new DefaultListModel();
        student.getFriends().forEach((Student, Float) -> {
            listModel.addElement(Student.getName());
        });
        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
//        list.addListSelectionListener(super.getClass());
        JScrollPane listScrollPane = new JScrollPane(list);

        int colLength = student.getFriends().size() >= student.getInterests().size()? student.getFriends().size() : student.getInterests().size();
       System.out.println(colLength);
       colLength++;
        String[][] rowData = new String[2][colLength];

        int i =0;


        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};


        //Adds student names to the table
        for(Student s: student.getFriends().keySet()){
            rowData[0][i] = s.getName();
        }
        i = 0;
        //adds student interest to the table
        for(String s: student.getInterests()){
            rowData[1][i] = s;
        }

        System.out.println(rowData[0][0]);
        System.out.println(rowData[0][1]);


        String[] columnNames = new String[2];
        columnNames[0] = new String("Friends");
        columnNames[1] = new String("Intersts");

        JTable table = new JTable(rowData, columnNames);
        table.setBounds(30,40,200,300);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

//        Container contentPane = getContentPane();
//        contentPane.add(listScrollPane, BorderLayout.CENTER);


        panel.add(name);
        panel.add(table);
        this.add(panel);
        setVisible(true);


    }
}

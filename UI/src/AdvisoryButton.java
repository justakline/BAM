import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class AdvisoryButton extends JInternalFrame {

    private Advisory advisory;
    private String advisor;
    private JTable data;


    public AdvisoryButton(Advisory advisory){
        super("" + advisory.getAdvisor(), true, true);
        setLayout( new FlowLayout());

        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};




        Object[][] dat = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        Vector<String> col = new Vector<>();
        col.add("Nick");
//        data = new JTable(advisory.getStudentsToString(),  col);
        data= new JTable(dat, columnNames);
        add(data);


        this.advisory= advisory;
        setSize(300,300);


        setVisible(true);

    }
}

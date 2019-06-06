import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class ToolBar extends JMenuBar implements ActionListener, MenuListener{

    private JFileChooser fc;
    private JMenu file;
    private JMenu load;
    private JMenu run;
    private JMenu find;
    private JMenu help;
    private JMenu options;
	private GUI host;
    private File studentCSV;
    private File activitiesCSV;
    private File friendsCSV;
	private JComboBox<String> studentJComboBox;
	 private JTextField newAdvisory;
    private JInternalFrame hosting;
    private JButton adding;
    private Advisory newAdd;

	public ToolBar(GUI host) {
        super();
		this.host = host;
        fc = new JFileChooser() {
            @Override
            public void setFileFilter(FileFilter filter) {
                super.setFileFilter(new FileNameExtensionFilter(".CSV files", "CSV", "csv"));
            }
        };
        JMenuItem temp1; //temp for attaching listeners
        JMenu temp2; //temp for attaching listeners
        fc = new JFileChooser();

        //Creation of the file Tab and associated submenu items
        this.add(file = new JMenu("File"));
        file.add(temp1 = new JMenuItem("New")); //0
        temp1.addActionListener(this);
        file.add(temp1 = new JMenuItem("Export")); //1
        temp1.addActionListener(this);
        file.add(temp1 = new JMenuItem("Print")); //2
        temp1.addActionListener(this);


        this.add(options = new JMenu("Options"));
        this.add(temp2 = find = new JMenu("Find"));
        temp2.addMenuListener(this);
        find.add(temp1 = new JMenuItem("Find Student"));
        temp1.addActionListener(this);
        this.add(temp2 = help = new JMenu("Help"));
        temp2.addMenuListener(this);
        help.add(temp1 = new JMenuItem("Open Tutorial"));
        temp1.addActionListener(this);

        //Instantiation the options


//        options.add( temp1 =new JMenuItem("Find Student"));

		Vector<String> students = new Vector<>();

		//iterate through advisories
		//compare one name with all others to find smaller in lexigraphical order (CompaeTo)

		Vector<Advisory> advisories = host.getAl().getAdvisories();

		for(Advisory advisory : advisories) {
			for(int i = 0; i < advisory.getStudents().size(); i++) {
				String smallest = advisory.getStudents().get(i).getName();
				for(Advisory advisory1 : advisories) {
					for(int j = 0; j < advisory1.getStudents().size(); j++) {
						if(advisory1.getStudents().get(j).getName().compareTo(smallest) < 0 && !students.contains(smallest)) {
							smallest = advisory1.getStudents().get(j).getName();
						}
					}
					students.add(smallest);
				}
			}
		}

		studentJComboBox = new JComboBox<>(students);
		 options.add( temp1 =new JMenuItem("Open All Advisories"));
        temp1.addActionListener(this);
        options.add( temp1 =new JMenuItem("Close All Advisories"));
        temp1.addActionListener(this);
        options.add( temp1 =new JMenuItem("Add Custom Advisory"));
        temp1.addActionListener(this);

         hosting = new JInternalFrame("New Advisory", true, true);
         hosting.setLayout(new FlowLayout());
        hosting.setSize(new Dimension(200,100));
        hosting.setPreferredSize(new Dimension(200,100));
        newAdvisory = new JTextField("Click and Set Advisor's Name");
        newAdvisory.setEditable(true);
        newAdvisory.setSize(new Dimension(300,100));
        newAdvisory.setVisible(true);
        adding = new JButton("Add");
        adding.addActionListener(this);
        hosting.add(newAdvisory);
        hosting.add(adding);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    //Printing
       if (e.getSource().equals(file.getMenuComponent(2))) {
            try {
                host.getLeftPanel().getCSV();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource().equals(file.getItem(1))) {//Export

            try {
                File csv = host.getLeftPanel().getCSV();
            } catch (IOException ex) {
                ex.printStackTrace();
//            }
//            if (fc.showOpenDialog(host.getRightPanel()) == JFileChooser.APPROVE_OPTION) {
//                try {
//                    FileInputStream fileIn = new FileInputStream("initial_save.ser");
//                    ObjectInputStream in = new ObjectInputStream(fileIn);
//                    host = (GUI) in.readObject();
////                    System.out.println( hash.toString() );
//                } catch (FileNotFoundException ex) {
//                    ex.printStackTrace();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
////                host = fc.getSelectedFile();
            }
        } else if (e.getSource().equals(file.getItem(0))) {//New GUI
//            host.dispose();
//            BAM.main();
            host = new GUI();
            host.setVisible(true);
            host.setSize(new Dimension(500, 500));
            host.setPreferredSize(new Dimension(500, 500));

        } else if (e.getSource().equals(find.getItem(0))) {//Find Student
            System.out.println("GO");
            JInternalFrame frame = new JInternalFrame();
            frame.setVisible(true);
            frame.setSize(200, 80);
            frame.setPreferredSize(new Dimension(200, 60));
            frame.add(studentJComboBox);
            host.getRightPanel().add(frame);
            studentJComboBox.addActionListener(host.getRightPanel());
            frame.setClosable(true);
            frame.setTitle("Find Student");
        } else if (e.getSource().equals(options.getItem(0))) {
            int mult = 2 * host.getRightPanel().getAdvisoryFrames().size();

            int width = host.getWidth() / mult;
            int height = host.getHeight() / mult;
            int count = 1;
            for (AdvisoryFrame advisoryFrame : host.getRightPanel().getAdvisoryFrames()) {
                advisoryFrame.setVisible(true);
                advisoryFrame.setLocation(width * count, height * count);
                count++;
            }
        } else if (e.getSource().equals(options.getItem(1))) {
            for (AdvisoryFrame advisoryFrame : host.getRightPanel().getAdvisoryFrames()) {
                advisoryFrame.setVisible(false);
                advisoryFrame.setLocation(0, 0);
            }
        } else if (e.getSource().equals(options.getItem(2))) {//Create Custom Advisory
           newAdvisory.setVisible(true);
           host.getRightPanel().add(hosting);
           hosting.show();

           System.out.println("Working");

       }else if(e.getSource().equals(help.getItem(0))){

           if (Desktop.isDesktopSupported()) {
               try {
                   String currentDir = System.getProperty("user.dir");
                   currentDir = currentDir+"/resources/The Best Advisory Maker How To Guide.pdf";
                   System.out.println("Current dir using System:" +currentDir);
                   if ((new File(currentDir)).exists())
                   {
                       Process p = Runtime
                               .getRuntime()
                               .exec("rundll32 url.dll,FileProtocolHandler " +currentDir);
                       p.waitFor();
                   }
                   else
                   {
                       System.out.println("File is not exists");
                   }
                   System.out.println("Done");
               } catch (Exception ex)
               {
                   ex.printStackTrace();
               }
           }
		} else {
			JButton source = (JButton) e.getSource();
			if(source.equals(adding)) {
				Vector<Student> stud = new Vector<>();
				stud.add(TestCases.getFriendlyMaleStudentTest());
				Advisory advisory = new Advisory(stud, newAdvisory.getText());
				AdvisorButton button = new AdvisorButton(advisory);
				button.addActionListener(host.getRightPanel());

				host.getLeftPanel().getAdvisors().add(button);
				host.getLeftPanel().add(button);
				host.getRightPanel().addFrame(button);
				button.getAdvisory().getStudents().clear();
				System.out.println();
//
//                    host.getRightPanel().addNewAdvisory(newAdvisory.getText());
//                    System.out.println(newAdvisory.getText());
//                    System.out.println("Adding");
                }
         }
        }


    @Override
    public void menuSelected(MenuEvent e) {
        if ((e.getSource().equals(this.run))) {
			host.getAl().run();
			host.getLeftPanel().setAdvisories(host.getAl().getAdvisories());
		 } else if (e.getSource().equals(this.find)) {
            host.getStudentFinder().show();

        }
    }

    public JMenu getOptions() {
	    return options;
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}

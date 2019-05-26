import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class CSVParser {
    private File file;

    public static ArrayList<Student> buildStudentList(File students, File activities) throws IOException {
        ArrayList<Student> studentList = parseStudentFile(students);
        HashMap<Integer, Vector<String>> map = parseActivities(activities);
        for (Student student : studentList) {
            student.setInterests(map.get(student.getID()));
            System.out.println(student);
        }
        return studentList;
    }

    public static ArrayList<Student> parseStudentFile(File file) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine(); //eliminates column headers
        while (reader.ready()) {
            students.add(new Student(reader.readLine().split(",")));
        }
        return students;
    }

    public static HashMap<Integer, Vector<String>> parseActivities(File file) throws IOException {
        HashMap<Integer, Vector<String>> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine();
        while (reader.ready()) {
            String[] line = reader.readLine().split(",");
            if (map.containsKey(Integer.valueOf(line[0]))) {
                map.get(new Integer(Integer.valueOf(line[0]))).add(line[1]);
            } else {
                map.put(Integer.valueOf(line[0]), new Vector<>());
                map.get(new Integer(Integer.valueOf(line[0]))).add(line[1]);
            }
        }
        return map;
        //and may god have mercy on your soul for the syntax of this method;
    }

    //purely for testing
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser() {
            @Override
            public void setFileFilter(FileFilter filter) {
                super.setFileFilter(new FileNameExtensionFilter(".CSV files", "CSV", "csv"));
            }
        };
        JFrame frame = new JFrame();
        frame.add(new JPanel());
        chooser.showOpenDialog(frame.getComponent(0));
        File file1 = chooser.getSelectedFile();
        chooser.showOpenDialog(frame.getComponent(0));
        File file2 = chooser.getSelectedFile();
        try {
            System.out.println(CSVParser.buildStudentList(file1, file2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

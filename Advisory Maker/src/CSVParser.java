import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class CSVParser {

    public static Vector<Student> buildStudentList(File studentFile, File activitiesFile, File friendsList) throws IOException {
        Vector<Student> studentList = parseStudentFile(studentFile);
        HashMap<Integer, Vector<String>> activitiesMap = parseActivitiesFile(activitiesFile);
        HashMap<Student, Vector<Student>> friendMap = parseFriendsList(friendsList, studentList);
        for (Student student : studentList) {
            student.setInterests(activitiesMap.get(student.getID()));
            student.setFriends(friendMap.get(student));
            System.out.println(student);
        }
        return studentList;
    }

    private static Vector<Student> parseStudentFile(File studentFile) throws IOException {
        Vector<Student> students = new Vector<>();
        BufferedReader reader = new BufferedReader(new FileReader(studentFile));
	    if (reader.readLine().split(",").length != 6)
		    throw new IllegalArgumentException(); //eliminates column headers
        while (reader.ready()) {
            students.add(new Student(reader.readLine().split(",")));
        }
        return students;
    }

    private static HashMap<Integer, Vector<String>> parseActivitiesFile(File activitiesFile) throws IOException, IllegalArgumentException {
        HashMap<Integer, Vector<String>> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(activitiesFile));
		if (reader.readLine().split(",").length != 2)
			throw new IllegalArgumentException();
        while (reader.ready()) {
            String[] line = reader.readLine().split(",");
            if (map.containsKey(Integer.valueOf(line[0]))) {
                map.get(Integer.valueOf(line[0])).add(line[1]);
            } else {
                map.put(Integer.valueOf(line[0]), new Vector<>());
                map.get(Integer.valueOf(line[0])).add(line[1]);
            }
        }
        return map;
        //and may god have mercy on your soul for the syntax of this method;
    }

    private static HashMap<Student, Vector<Student>> parseFriendsList(File friendsList, Vector<Student> students) throws IOException, IllegalArgumentException {
        HashMap<Student, Vector<Student>> friendMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(friendsList));
        if (reader.readLine().split(",").length != 7)
            throw new IllegalArgumentException();
        while (reader.ready()) {
            String[] line = reader.readLine().split(",");
            Vector<Student> friendVector = new Vector<>();
            Student source = null;
            for (Student student : students) {
                if (student.getID() == Integer.valueOf(line[0]))
                    source = student;
            }
            for (Student student : students) {
                for (int i = 1; i < line.length - 1; i++) {
                    if (student.getID() == Integer.valueOf(line[i]))
                        friendVector.add(student);
                }
                friendMap.put(students.get(students.indexOf(source)), friendVector);
            }
        }
        return friendMap;
    }

    //purely for testing
    public static void main(String[] args) throws IOException {
        JFileChooser chooser = new JFileChooser() {
            @Override
            public void setFileFilter(FileFilter filter) {
                super.setFileFilter(new FileNameExtensionFilter(".CSV files", "CSV", "csv"));
            }
        };
        JFrame frame = new JFrame();
        frame.add(new JPanel());
//        chooser.showOpenDialog(frame.getComponent(0));
//        File student = chooser.getSelectedFile();
//        chooser.showOpenDialog(frame.getComponent(0));
//        File activities = chooser.getSelectedFile();
        chooser.showOpenDialog(frame.getComponent(0));
        File friends = chooser.getSelectedFile();
        try {
            System.out.println(CSVParser.parseFriendsList(friends, CSVParser.buildStudentList(new File("C:/Users/ryanl/Documents/Students - export (51).csv"), new File("C:/Users/ryanl/Documents/Activities - Sheet1.csv"), friends)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(CSVParser.buildStudentList(new File("C:/Users/ryanl/Documents/Students - export (51).csv"),new File("C:/Users/ryanl/Documents/Activities - Sheet1.csv")));
    }
}

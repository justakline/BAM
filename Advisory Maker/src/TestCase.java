import javax.swing.*;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TestCase {
    public static final Vector<String> NVinterests = new Vector<>(Stream.of("Computer Science", "Singing", "Competitive Bike Watching").collect(Collectors.toList()));
    //Advisory
    //Student
    //StudentPage
    //AdvisoryComponents
    //
    public static final Vector<String> SSinterests = new Vector<>(Stream.of("Neuroscience", "Dog Petting", "Competitive Bike Watching").collect(Collectors.toList()));
    public static final Vector<String> RLinterests = new Vector<>(Stream.of("Computer Science", "Neuroscience", "Fountain Pen", "Running").collect(Collectors.toList()));
    public static final Vector<String> KBinterests = new Vector<>(Stream.of("Neuroscience", "Singing").collect(Collectors.toList()));

    private TestCase() {
    }

    public static Advisory getAdvisoryTest() {
        return new Advisory(getStudentGroupTest(), "Kim Smith");
    }

    public static Vector<Student> getStudentGroupTest() {
        return new Vector<>(Stream.of(getFriendlyFemaleStudentTest(), getFriendlyMaleStudentTest(), getLonelyFemaleStudentTest(), getLonelyMaleStudentTest()).collect(Collectors.toList()));
    }

    public static Student getLonelyMaleStudentTest() {
        return new Student("Nick Valente", NVinterests, Student.Gender.MALE);
    }

    public static Student getLonelyFemaleStudentTest() {
        return new Student("Sarah Stamper", SSinterests, Student.Gender.FEMALE);
    }

    public static Student getFriendlyMaleStudentTest() {
        return new Student("Rufe Leventhal", getFriendGroupTest(), RLinterests, Student.Gender.MALE);
    }

    public static Student getFriendlyFemaleStudentTest() {
        return new Student("Kate Barranco", getFriendGroupTest(), KBinterests, Student.Gender.FEMALE);
    }

    public static List<Student> getFriendGroupTest() {
        return new Vector<>(Stream.of(getLonelyFemaleStudentTest(), getLonelyMaleStudentTest()).collect(Collectors.toList()));
    }

    public static AdvisoryFrame getAdvisoryFrame() {
        return new AdvisoryFrame(getAdvisoryTest());
    }

    public static AdvisorButton getAdvisorButton() {
        return new AdvisorButton(getAdvisoryTest());
    }

    public static AdvisoryList getAdvisoryList(JPanel panel) {
        return new AdvisoryList(new Vector<>(Stream.of(getAdvisoryTest()).collect(Collectors.toList())), panel);
    }
}

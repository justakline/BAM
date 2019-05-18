import java.util.List;
import java.util.Map;

public class Student {

    private String name; //probs need this lol
    private Map<Student, Float > friends;
    private List<String> advisors;
    private List<String> interests;
    private Gender gender;

    public enum Gender{
        MALE, FEMALE, OTHER
    }


    public Student(String name, Map<Student, Float> friends, List<String> advisors, List<String> interests, Gender gender) {
        this.name = name;
        this.friends = friends;
        this.advisors = advisors;
        this.interests = interests;
        this.gender = gender;

    }

    public Student(String name) {
        this.name = name;
        this.friends = null;
        this.advisors = null;
        this.interests = null;
        this.gender = null;
    }

}

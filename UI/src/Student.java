import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

    private Map<Student, Float > friends;
    private List<String> advisors;
    private List<String> interests;
    private Gender gender;

    public enum Gender{
        MALE, FEMALE, OTHER
    }


    public Student(Map<Student, Float> friends, List<String> advisors, List<String>interests, Gender gender) {
        this.friends = friends;
        this.advisors = advisors;
        this.interests = interests;
        this.gender = gender;

    }

}

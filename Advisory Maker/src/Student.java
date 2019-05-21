import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Student {
    private String name;
    private List<Student> friends;
    private  Vector<String> interests;
    private Gender gender;

    public enum Gender{
        MALE, FEMALE, OTHER
    }

    public Student(String name, List<Student> friends, Vector<String> interests, Gender gender) {
        this.name = name;
        this.friends = friends;
        this.interests = interests;
        this.gender = gender;

    }

    public Student(String name, Vector<String> interests, Gender gender) {
        this.name = name;
        System.out.println(name);
        this.interests = interests;
        this.gender = gender;
        friends = new ArrayList<>();
    }
    //Adds a group of friends with a predetermined value of how connected they are
    public void addFriendGroup(List<Student> friendGroup) {
        friendGroup.forEach(student -> friends.add(student));
    }

    public String getName(){
        return name;
    }

    public List<Student> getFriends() {
        return friends;
    }

    public Vector<String> getInterests() {
        return interests;
    }

}

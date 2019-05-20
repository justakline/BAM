import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Student {
    private String name;
    private HashMap<Student, Float > friends;
    private Vector<String> advisors;
    private  Vector<String> interests;
    private Gender gender;

    public enum Gender{
        MALE, FEMALE, OTHER
    }


    public Student( String name, HashMap<Student, Float> friends, Vector<String> advisors, Vector<String>interests, Gender gender) {
        this.name = new String (name);
        this.friends = friends;
        this.advisors = advisors;
        this.interests = interests;
        this.gender = gender;

    }

    public Student(String name, Vector<String> advisors, Vector<String>interests, Gender gender) {
        this.name = new String(name);
        System.out.println(name);
        this.advisors = advisors;
        this.interests = interests;
        this.gender = gender;
        friends = new HashMap<Student, Float>();
    }
    //Adds a group of friends with a predetermined value of how connected they are
    public void addFriendGroup( HashMap<Student, Float> friendGroup ) {

        friends.forEach((Student, Float) -> {
            friends.put(Student, Float);
        });
    }

    //Adds a group of friends without a predetermined value of how connected they are
    public void addFriendGroup( Vector<Student> friendGroup ) {

        for(Student s: friendGroup){
            friends.put(s, 0f);
        }
    }
        public void addFriend(){

    }

    public String getName(){
        return name;
    }

    public Map<Student, Float> getFriends() {
        return friends;
    }

    public Vector<String> getInterests() {
        return interests;
    }

}

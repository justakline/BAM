import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Student {
    private String name;
    private String nickName;
    private List<Student> friends;
    private  Vector<String> interests;
    private Gender gender;
    private int ID;
    private Advisory advisory;

    public Student(String name, List<Student> friends, Vector<String> interests, Gender gender) {
        this.name = name;
        this.friends = friends;
        this.interests = interests;
        this.gender = gender;

    }

    //ID,First,Middle,Last,nickName, Gender
    public Student(String[] fields) {
        this.ID = Integer.valueOf(fields[0]);
        this.name = String.format("%s  %s  %s", fields[1], fields[2], fields[3]);
        this.nickName = fields[4];
        switch (fields[5]) {
            case "M":
                this.gender = Gender.M;
            case "F":
                this.gender = Gender.F;
            case "O":
                this.gender = Gender.O;
            default:
                this.gender = Gender.O;
        }
    }

    public Student(String name, Vector<String> interests, Gender gender) {
        this.name = name;
        this.interests = interests;
        this.gender = gender;
        friends = new ArrayList<>();
        friends.add(new Student());
    }

    //Supposed to nothing... is just a Ghost because it is needed in the algo
    public Student () {

    }

    public int getID() {
        return ID;
    }

    //Adds a group of friends with a predetermined value of how connected they are
    public void addFriendGroup(List<Student> friendGroup) {
        friendGroup.forEach(student -> friends.add(student));
    }

    public String toString() {
        return String.format("Student ID: %d, Student name: %s, Student Interests: %s", getID(), getName(), getInterests());
    }

    public String getName() {
        return name;
    }

    public List<Student> getFriends() {
        return friends;
    }

    public Vector<String> getInterests() {
        return interests;
    }

    public void setInterests(Vector<String> interests) {
        this.interests = interests;
    }

    public Advisory getAdvisory() {
        return advisory;
    }

    public enum Gender {
        M, F, O
    }
}



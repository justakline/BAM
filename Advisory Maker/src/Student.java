import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Student {
    private String name;
    private String nickName;
    private List<Student> friends;
    private Vector<String> interests;
    private Gender gender;
    private int ID;
    private int INDEX;
    private Advisory advisory;

    public Student(String name, List<Student> friends, Vector<String> interests, Gender gender) {
        this.name = name;
        this.setFriends(friends);
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
        setFriends(new ArrayList<>());
        getFriends().add(new Student());
    }

    //Supposed to nothing... is just a Ghost because it is needed in the algo
    public Student () {

    }

    public int getID() {
        return ID;
    }

    //Adds a group of friends with a predetermined value of how connected they are
    public void addFriendGroup(List<Student> friendGroup) {
        friendGroup.forEach(student -> getFriends().add(student));
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

    public int getINDEX(){
        return INDEX;
    }

    public void setINDEX(int index){
        this.INDEX = index;
    }

    public void setAdvisory(Advisory a){
        this.advisory = a;
    }

    public void setInterests(Vector<String> interests) {
        this.interests = interests;
    }

    public Advisory getAdvisory() {
        return advisory;
    }

    public boolean isFriend(Student friend) {
        return friends.contains(friend);
    }

    public float interestCount(Student friend) {
        float commonInterests = 0;
        for (String friendInterest:friend.getInterests()) {
            for(String thisInterest:interests){
                if(friendInterest.equals(thisInterest)){
                    commonInterests++;
                }
            }
        }
        return commonInterests/interests.size();
    }

    public void setFriends(List<Student> friends) {
        this.friends = friends;
    }

    public enum Gender {
        M, F, O
    }
}



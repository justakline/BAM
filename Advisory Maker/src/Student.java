import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    //ID,First,Middle,Last,nickName, Gender,
    //User ID,First Name,Middle Name,Last Name,Nick Name,Gender,FRIEND1,FRIEND2,FRIEND3,FRIEND4,FRIEND5,FRIEND6,ACTIVITY1,ACTIVITY2,ACTIVITY3
    public Student(String[] fields) {
        this.ID = Integer.valueOf(fields[0]);
		this.name = String.format("%s %s %s", fields[1], fields[2], fields[3]);
		 this.nickName = fields[4];
        switch (fields[5]) {
            case "M":
				this.gender = Gender.M;
				break;
			case "F":
				this.gender = Gender.F;
				break;
			default:
				 this.gender = Gender.O;
        }
        if (fields[12] != null) {
            setInterests(new Vector<>(Stream.of(fields[12], fields[13], fields[14]).collect(Collectors.toList())));
        }
        friends = new Vector<>();
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
    public Student (String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    //Adds a group of friends with a predetermined value of how connected they are
    public void addFriendGroup(List<Student> friendGroup) {
        friendGroup.forEach(student -> getFriends().add(student));
    }

    public String toString() {
        return String.format("Student ID: %d, Student name: %s, Student Interests: %s, Student's friends: %s", getID(), getName(), getInterests(), getFriendsName());
    }

    public String getName() {
        return name;
    }

    public List<Student> getFriends() {
        return friends;
    }

    public String getFriendsName() {
        String s = "";
        for (Student friend : getFriends()) {
            if (friend != null)
                s += String.format("%s ,", friend.getName());
        }
        return s;
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

    public Gender getGender() {
        return gender;
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



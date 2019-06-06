import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class Algorithm {
	private Vector<Student> students;
    private Vector<Advisory> advisories;
    private Vector<Student> originalStudents;
    private int numStudents;
	private double[][] values;

    private float friendMult;
    private float interestMult;

    public Algorithm(Vector<Student> students, Vector<Advisory> advisories, float friendMult, float interestMult) { //make Singleton later
        this.students = students;
        this.originalStudents = new Vector<>();

        for(Student s1: originalStudents) {
            for(Student s2: originalStudents) {
                if(s1.getName().compareTo(s2.getName())>0){
                    System.out.println("Switch");
                    Student temp = s1;
                    s1 = s2;
                    s2= temp;

                }
            }
        }
        this.advisories = advisories;
        numStudents = students.size();
        values = new double[numStudents][numStudents];

        this.friendMult = friendMult;
        this.interestMult = interestMult;

    }

    public Vector<Student> getAlphebeticalOrder() {
        return originalStudents;
    }

    private float scoreStudents(Student s1, Student s2) {
        return (float)(values[s1.getINDEX()][s2.getINDEX()] + values[s2.getINDEX()][s1.getINDEX()]);
    }

    //Probably going through the advisory and adding up the total score between all of the members?

    private void addStudentToAdvisory(Student student, Advisory advisory) {
        float score = advisory.getScore();
        for (Student otherStudent : advisory.getStudents())
            score += scoreStudents(student, otherStudent);

        advisory.addStudent(student);
        advisory.setScore(score);
    }
    private void removeStudentFromAdvisory(Student student){
        Advisory advisory = student.getAdvisory();
        float score = advisory.getScore();
        advisory.removeStudent(student);
        for (Student otherStudent : advisory.getStudents())
            score -= scoreStudents(student, otherStudent);
        advisory.setScore(score);
    }

    private void swap(Student student0, Student student1) {
        Advisory advisory0 = student0.getAdvisory();
        Advisory advisory1 = student1.getAdvisory();
        removeStudentFromAdvisory(student0);
        removeStudentFromAdvisory(student1);
        addStudentToAdvisory(student0, advisory1);
        addStudentToAdvisory(student1, advisory0);
    }

    private float weightEdge(Student s1, Student s2) {
        int areFriends = s1.isFriend(s2) ? 1 : 0;
        float weight = friendMult*areFriends + interestMult*(s1.interestCount(s2));
        return (weight == 0) ? Integer.MAX_VALUE : 1/weight;
    }

    private void initializeValues() {
        for (int i = 0; i < numStudents; i++) {
            for (int j = 0; j < numStudents; j++) {
                values[i][j] = weightEdge(students.get(i),students.get(j));
            }
        }
    }

    private void normalizeValues() {
        for (int i = 0; i < numStudents; i++) {
            for (int j = 0; j < numStudents; j++) {
                values[i][j] = 1/values[i][j];
            }
        }
    }

	private void Floyds() {
        initializeValues();
		for (int k = 0; k < numStudents; k++) {
			for (int i = 0; i < numStudents; i++) {
				for (int j = 0; j < numStudents; j++) {
					values[i][j] = Math.min(values[i][j], values[i][k] + values[k][j]);
				}
			}
		}
        normalizeValues();
    }

    private void initializeScores(){
        for(Advisory advisory : advisories){
            float score = 0;
            for(Student s0 : advisory.getStudents())
                for(Student s1 : advisory.getStudents())
                    if(s1 != s0)
                        score += scoreStudents(s0, s1);

            advisory.setScore(score);
        }

    }

    public void runSwaps() {
        boolean viableSwapsExist = true;

        while(viableSwapsExist){
            viableSwapsExist = false;

            for (Student student0 : students){
                Advisory advisory0 = student0.getAdvisory();
                float currentRank0 = advisory0.getScore();

                for(Student student1 : students){
                    Advisory advisory1 = student1.getAdvisory();
                    float currentRank1 = advisory1.getScore();

                    if(advisory1 != advisory0){
                        swap(student0, student1);
                        float simulatedRank0 = advisory0.getScore();
                        float simulatedRank1 = advisory1.getScore();

                        //if swap is mutually beneficial and good genderQuota, keep swap, else swap back
                        if (simulatedRank0 > currentRank0 && simulatedRank1 > currentRank1 &&
                                advisory0.getTotalMale()   > 2  && advisory1.getTotalMale()   > 2 &&
                                advisory0.getTotalFemale() > 2  && advisory1.getTotalFemale() > 2 ){
                            viableSwapsExist = true;
                            break;
                        }else{
                            swap(student0, student1);
                        }
                    }
                }
            }
        }
        Vector<Student> lonelyStudents = new Vector<>();

        for (Student s0 : students){
            int count = 0;
            for (Student potenitalFriend: s0.getAdvisory().getStudents()) {
                if (s0.isFriend(potenitalFriend)) {
                    count++;
                    break;
                }
            }
            if(count == 0){
                lonelyStudents.add(s0);
            }

        }
        System.out.println(lonelyStudents.size());
//        noFriendsSwap(lonelyStudents);
//        System.out.println(lonelyStudents.size());
    }

//    public void noFriendsSwap(Vector<Student>lonelyStudents) {
//        boolean viableSwapsExist = true;
//
//        while(viableSwapsExist){
//            viableSwapsExist = false;
//
//            for (Student student0 : lonelyStudents){
//                Advisory advisory0 = student0.getAdvisory();
//                float currentRank0 = advisory0.getScore();
//
//                for(Student student1 : students){
//                    Advisory advisory1 = student1.getAdvisory();
////                    float currentRank1 = advisory1.getScore();
//
//                    if(advisory1 != advisory0){
//                        int totalLonelyBefore = calculateTotalLonelyStudents();
//                        swap(student0, student1);
////                        float simulatedRank0 = advisory0.getScore();
////                        float simulatedRank1 = advisory1.getScore();
//
//                        //if swap is mutually beneficial and good genderQuota, keep swap, else swap back
//                        if (totalLonelyBefore> calculateTotalLonelyStudents()&&
//                                advisory0.getTotalMale() > 2&& advisory1.getTotalMale() > 2 && advisory1.getTotalFemale() > 2
//                                && advisory1.getTotalFemale() > 2 ){
//                            viableSwapsExist = true;
//                            break;
//                        }else{
//                            swap(student0, student1);
//                        }
//                    }
//                }
//            }
//        }
//    }

    public int calculateTotalLonelyStudents() {
        int i =0;

        for (Student s0 : students){
            int count = 0;
            for (Student potenitalFriend: s0.getAdvisory().getStudents()) {
                if (s0.isFriend(potenitalFriend)) {
                    count++;
                    break;
                }
            }
            if(count == 0){
               i++;
            }

        }
        return i;
    }

    public boolean everyoneHasFriend(Advisory advisory){
            for(Student s1: advisory.getStudents()){
                int count = 0;
                for(Student s2: advisory.getStudents()){
                    if(s1 != s2&& s1.isFriend(s2)){
                        count++;
                    }
                }
                if(count ==0){//If someone does not have a friend
                    return false;
                }
            }
        return false;
    }

    public boolean run()
    {
        Floyds();
        initializeScores();
        runSwaps();

        /////DEBUG/////
        for (Advisory advisory : advisories) {
            System.out.println(advisory);
            advisory.analyze();
        }

        return true;
    }

    public double[][] getValues() {
        return values;
    }

	public float getFriendMult() {
		return friendMult;
	}

    public float getInterestMult() {
        return interestMult;
    }

	public void setFriendMult(float friendMult) {
		this.friendMult = friendMult;
	}

	public void setInterestMult(float interestMult) {
		this.interestMult = interestMult;
	}
}

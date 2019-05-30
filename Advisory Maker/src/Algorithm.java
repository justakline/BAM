import java.util.Vector;

public class Algorithm {
	private Vector<Student> students;
    private Vector<Advisory> advisories;
    private int numStudents;
	private double[][] values;

    private float friendMult;
    private float interestMult;

    private Algorithm(Vector<Student> students, Vector<Advisory> advisories) { //make Singleton later
        this.students = students;
        this.advisories = advisories;
        numStudents = students.size();
        values = new double[numStudents][numStudents];

        friendMult = 0.5f;
        interestMult = (1-friendMult);

    }

    public float scoreStudents(Student s1, Student s2) {

        return (float)values[s1.getINDEX()][s2.getINDEX()];
    }

    //Probably going through the advisory and adding up the total score between all of the members?

    public void addStudentToAdvisory(Student student, Advisory a) {
        float score = a.getScore();
        for (Student otherStudent : a.getStudents()) {
            score += scoreStudents(student, otherStudent) + scoreStudents(otherStudent, student);
        }
        a.addStudent(student);
    }
    public void removeStudentFromAdvisory(Student s){
        for (Student friend:students) {
            score-=Algorithm.getInstance().scoreStudents(s, friend);
            score-=Algorithm.getInstance().scoreStudents(friend,s);
        }
    }

    private void swap(Student student0, Student student1) {
        //Go to s0's advisory, look at studentList and remove itself, then add to s1's advisory

        student0.getAdvisory().removeStudent(student0);
        student1.getAdvisory().removeStudent(student1);
        student0.getAdvisory().addStudent(student0);
        student1.getAdvisory().addStudent(student1);
//        //Same but for s1
//        student0.getAdvisory().getStudents().add(student1.getAdvisory().getStudents().remove(
//                student1.getAdvisory().getStudents().indexOf(student1))
//        );
    }



    public float weightEdge(Student s1, Student s2) {
        int areFriends = s1.isFriend(s2) ? 1 : 0;
        float weight = friendMult*areFriends + interestMult*(s1.interestCount(s2));
        return weight == 0 ? Integer.MAX_VALUE : 1/weight;
    }

    private void initializeValues() {
        for (int i = 0; i < numStudents; i++) {
            for (int j = 0; j < numStudents; j++) {
                values[i][j] = weightEdge(students.get(i),students.get(j));
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
    }

    public void runSwaps() {
        boolean viableSwapsExist = true;

        while(viableSwapsExist){
            viableSwapsExist = false;
            //Iterate through every student and get its fields
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

                        //if swap is mutually beneficial, keep swap, else swap back
                        if (simulatedRank0 > currentRank0 && simulatedRank1 > currentRank1){
                            viableSwapsExist = true;
                            break;
                        }else{
                            swap(student0, student1);
                        }
                    }
                }
            }
        }
    }

    public boolean run()
    {
        // Run Floyd's
        //
        // Calculate initial scores

        // run Swaps
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

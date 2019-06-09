import java.util.Vector;

public class Algorithm {
	private GUI host;
	private Vector<Student> students;
	private Vector<Advisory> advisories;
	private int numStudents;
	private double[][] values;

	private float friendMult;
	private float interestMult;

	//This is where the magic happens
	//We use floyds and create a graph in order to find the
	//distances between 2 students

	public Algorithm(GUI host, float friendMult, float interestMult) { //make Singleton later
		this.students = host.getStudents();
		this.advisories = host.getAdvisories();
		numStudents = students.size();
		values = new double[numStudents][numStudents];

		this.friendMult = friendMult; //To determine how much of an affect a person's friend group should have on determining closeness
		this.interestMult = interestMult; //To determine how much of an affect a person's Interests should have on determining closeness

	}
//We know the score between students from a generated table matrix called Values
	private float scoreStudents(Student s1, Student s2) {
		return (float) (values[s1.getINDEX()][s2.getINDEX()] + values[s2.getINDEX()][s1.getINDEX()]);
	}



	private void addStudentToAdvisory(Student student, Advisory advisory) {
		float score = advisory.getScore();
		for(Student otherStudent : advisory.getStudents()) { score += scoreStudents(student, otherStudent); }

		advisory.addStudent(student);
		advisory.setScore(score);
	}

	private void removeStudentFromAdvisory(Student student) {
		Advisory advisory = student.getAdvisory();
		float score = advisory.getScore();
		advisory.removeStudent(student);
		for(Student otherStudent : advisory.getStudents()) { score -= scoreStudents(student, otherStudent); }
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
		int areFriends = s1.isFriend(s2) ? 1 : 0; //give them a score if choosen as a friend
		float weight = getFriendMult() * areFriends + getInterestMult() * (s1.interestCount(s2)); //Add the score and interests to determine closness
		return (weight == 0) ? Integer.MAX_VALUE : 1 / weight;
	}
	//find how  close students are
	private void initializeValues() {
		for(int i = 0; i < numStudents; i++) {
			for(int j = 0; j < numStudents; j++) {
				values[i][j] = weightEdge(students.get(i), students.get(j));
			}
		}
	}
//For making the graph weighted
	private void normalizeValues() {
		for(int i = 0; i < numStudents; i++) {
			for(int j = 0; j < numStudents; j++) {
				values[i][j] = 1 / values[i][j];
			}
		}
	}
//Aactually finding the shortest distance
	private void floyds() {
		initializeValues();
		for(int k = 0; k < numStudents; k++) {
			for(int i = 0; i < numStudents; i++) {
				for(int j = 0; j < numStudents; j++) {
					values[i][j] = Math.min(values[i][j], values[i][k] + values[k][j]);
				}
			}
		}
		normalizeValues();
	}

	private void initializeScores() {
		for(Advisory advisory : getAdvisories()) {
			float score = 0;
			for(Student s0 : advisory.getStudents()) {
				for(Student s1 : advisory.getStudents()) { if(s1 != s0) { score += scoreStudents(s0, s1); } }
			}

			advisory.setScore(score);
		}

	}


	//Go through all of the students, and swap them with all others
	//Generate the Simulated Rank of the new advisory
	//if the Both of the advisories have benifited, then keep the swap
	//if not then swap back
	public void runSwaps() {
		boolean viableSwapsExist = true;

		while(viableSwapsExist) {
			viableSwapsExist = false;

            for (Student student0 : students){
                Advisory advisory0 = student0.getAdvisory();
                float currentRank0 = advisory0.getBalancedScore();

                for(Student student1 : students){
                    Advisory advisory1 = student1.getAdvisory();
                    float currentRank1 = advisory1.getBalancedScore();

                    if(advisory1 != advisory0 && !advisory0.isLocked() && !advisory1.isLocked()){
                        swap(student0, student1);
                        float simulatedRank0 = advisory0.getBalancedScore();
                        float simulatedRank1 = advisory1.getBalancedScore();

                        //if swap is mutually beneficial then keep it, else swap back
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
        floyds();
        initializeScores();
        runSwaps();
        //DEBUG();
		return true;
	}

	private void DEBUG(){
        for (Advisory advisory : advisories) {
            System.out.println(advisory);
            System.out.println(advisory.getTotalFemale() + " girls and " + advisory.getTotalMale() + " boys");
            System.out.println((int)(advisory.friendBalanceMultiplier()-1) + " / " + advisory.getStudents().size());

            //Analyze friend network
            for (Student student0 : advisory.getStudents()) {
                String cxn = student0.getINDEX() + ": ";
                int i = student0.getINDEX();
                for (Student student1 : advisory.getStudents()) {
                    int j = student1.getINDEX();
                    if(i != j){
                        cxn += Math.round(100*values[i][j])/100.0 + "  ";
                    }
                }
                System.out.println(cxn);
            }

            //advisory.analyze();
        }

        double size = (double)advisories.size();
        double sum = 0;
        for (Advisory advisory : advisories) sum += advisory.getScore();
        double mean = sum/size;
        System.out.println("mean score = " + mean);

        sum = 0;
        for (Advisory advisory : advisories) sum += Math.pow((advisory.getScore() - mean), 2);
        double stdev = Math.sqrt(sum/size);
        int stdev_p = (int)Math.round(100*stdev/mean);
        System.out.println("stdev_p = " + stdev_p + "%");

        sum = 0;
        for (Advisory advisory : advisories) sum += advisory.friendBalanceMultiplier()-1;
        mean = sum/size;
        System.out.println("mean friends = " + mean);
    }

	public double[][] getValues() {
		return values;
	}

	public float getFriendMult() {
		return friendMult;
	}

	public void setFriendMult(float friendMult) {
		this.friendMult = friendMult;
	}

	public float getInterestMult() {
		return interestMult;
	}

	public void setInterestMult(float interestMult) {
		this.interestMult = interestMult;
	}

	public Vector<Advisory> getAdvisories() {
		return advisories;
	}

	public void setAdvisories(Vector<Advisory> advisories) {
		this.advisories = advisories;
	}
}

import java.lang.reflect.Array;
import java.util.Vector;

public class Algorithm {
    private boolean viableSwapsExist;
	private  Student[] students;
	private  double[][] values;
    private Vector<Advisory> advisories;
    private static Algorithm algorithm; //singleton

    private float friendMult;
    private float interestMult;


    static{


    }
    public Algorithm createValues(int n) {
        students = new Student[n];
        values = new double[students.length][students.length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                values[i][j] = -1;
            }
        }
        return algorithm;
    }
	private Algorithm() { //make Singleton later
        viableSwapsExist = false;
        students = null;
        values = null;
        //Don't understand the CSV Parser Class so...

		friendMult = 0.5f;
		interestMult= (float)(1-friendMult);



		advisories = new Vector<>();
    }

    public float weightEdge(Student s1, Student s2) {
        return friendMult+(interestMult*(s1.interestCount(s2)));
    }

    public float scoreStudents(Student s1, Student s2) {
        return (float)values[s1.getID()][s2.getID()];
    }

    public double[][] getValues() {
	    return values;
    }

    //Probably going through the advisory and adding up the total score between all of the members?

    public void swap(Student student0, Student student1) {
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

	public double[][] Floyds() {
		for (int k = 0; k < students.length; k++) {
			for (int i = 0; i < students.length; i++) {
				for (int j = 0; j < students.length; j++) {
					values[i][j] = Math.min(values[i][j], values[i][k] + values[k][j]);
				}
			}
		}
		return values;
    }

    public void runSwaps() {
        while(viableSwapsExist){
            //Iterate through every student and get its fields
            for (Student student0 : students){
                Advisory advisory0 = student0.getAdvisory();
                float currentRank0 = advisory0.getScore();

                //iterate through every advisory
                for(Advisory advisory1: advisories){
                    if(!advisory1.getStudents().contains(student0)){
                        float currentRank1 = advisory1.getScore();

                        //iterate through the current advisory and simulate a swap
                        for(Student student1 : advisory1.getStudents()){
                            swap(student0, student1);
                            float simulatedRank0 = advisory0.getScore();
                            float simulatedRank1 = advisory1.getScore();

                            //if swap is mutually benificial, keep swap, else swap back
                            if (simulatedRank0 > currentRank0 && simulatedRank1 > currentRank1){
                                break;
                            }else{
                                swap(student0, student1);
                            }

                        }
                    }
                }
            }
        }
    }

    public static Algorithm getInstance() {
        if (algorithm == null) {
            return new Algorithm();
        }
        return algorithm;
    }


}

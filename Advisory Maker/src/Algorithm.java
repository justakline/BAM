import java.util.Vector;

public class Algorithm {
    private boolean viableSwapsExist;
    private Vector <Student> students;
    private Vector<Advisory> advisories;


    private Algorithm() {
        viableSwapsExist = false;
        //Don't understand the CSV Parser Class so...
        students = new Vector<>();
        advisories = new Vector();
    }

    //Probably going through the advisory and adding up the total score between all of the members?
    public float getAdvisoryRank() {
        return 0.0f;
    }

    public void swap(Student student0, Student student1) {
        //Go to s0's advisory, look at studentList and remove itself, then add to s1's advisory
        student1.getAdvisory().getStudents().add( student0.getAdvisory().getStudents().remove(
                student0.getAdvisory().getStudents().indexOf(student0))
        );

        //Same but for s1
        student0.getAdvisory().getStudents().add(student1.getAdvisory().getStudents().remove(
                student1.getAdvisory().getStudents().indexOf(student1))
        );
    }

    public void createFloyds() {
        //UHHHHHHH
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




}

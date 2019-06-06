import java.util.Vector;

public class Advisory {

    private Vector<Student> students;
    private String advisor;
    private float score;
    private int totalMale;
    private int totalFemale;

    // TO BE FED FROM USER INPUT, DEFAULT VALUES ARE AS FOLLOWS:
    private float genderBalanceTolerance = 4.0f;
    private int minimumGenderCount = 2;

    public Advisory(Vector<Student> students, String advisor){
        this.students = students;
        this.advisor = advisor;
        for (Student student : students) {
            student.setAdvisory(this);
            if(student.getGender() == Student.Gender.M){
                totalMale++;
            }else if(student.getGender() == Student.Gender.F){
                totalFemale++;
            }
        }

    }

    public void addStudent(Student s) {
        students.add(s);
        if (s.getGender() == Student.Gender.M){
            totalMale++;
        }else if (s.getGender() == Student.Gender.F){
            totalFemale++;
        }
        s.setAdvisory(this);
    }

    public void removeStudent(Student s) {
        students.remove(s);
        if (s.getGender() == Student.Gender.M){
            totalMale--;
        }else if (s.getGender() == Student.Gender.F){
            totalFemale--;
        }
        s.setAdvisory(null);
    }

	public void setName(String name) {
		this.advisor = name;
	}

	 public String getAdvisor() {
        return advisor;
    }

    public Vector<Student> getStudents() {
        return students;
    }

    private float genderBalanceMultiplier(){
        double n = students.size();
        double m = minimumGenderCount - 1;
        double p = genderBalanceTolerance;
        double x = totalFemale;

        return (float)(1 - Math.pow((2*x - n)/(2*m - n),p));

    }

    public float friendBalanceMultiplier(){
        float satisfied = 1;
        for (Student student0 : students) {
            boolean hasFriend = false;
            for (Student student1 : students){
                if(student0.isFriend(student1)){
                    hasFriend = true;
                    break;
                }
            }
            if(hasFriend) satisfied++;
        }
        return satisfied;
    }

    public float getScore() {
        return score;
    }

    public float getBalancedScore(){
        return (float)Math.pow(friendBalanceMultiplier(),2)*genderBalanceMultiplier()*score;
    }

    public float setScore(float score){
        return this.score = score;
    }


    //Creates a string that represents an advisory giving it a score and the students in it
    public String toString()
    {
        String output = advisor + " Advisory | Score : " + score + " | Students : " + students.get(0).getName() + " " + students.get(0).getGender();
        for (int i = 1; i < students.size(); i++)
            output += ", " + students.get(i).getName() + " " + students.get(i).getGender();
        return output;
    }
//prints out connected friends and interests in the advisory
    public void analyze(){
        System.out.println("FRIENDS");
        for (Student s0 : students)
            for (Student s1 : students)
                if(s0.isFriend(s1))
                    System.out.println("\t"+ s0.getName() + " --> " +s1.getName());

        System.out.println("INTERESTS");
        for (Student s0 : students)
            for (Student s1 : students)
                if(s0 != s1 && s0.interestCount(s1) > 0)
                    System.out.println("\t"+ s0.getName() + " --> " +s1.getName() + " = " + s0.interestCount(s1));

    }

    public Vector<String>getStudentsToString() {
        Vector<String> s = new Vector<>();
        students.forEach(Student -> s.add(Student.getName()));
        return s;
    }

    public int getTotalFemale() {
        return totalFemale;
    }

    public int getTotalMale() {
        return totalMale;
    }
}

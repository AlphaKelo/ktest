package BobsCircus;
public class Clerk extends Person {
    private String job;

    public Clerk(String name, int age, int yearsWorked, String job, String imagePath) {
        //I added an imagePath to support the Person class
    	super(name, age, yearsWorked, job, imagePath);
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    
    @Override
    public String toString() {
        return String.format("Name: %s, Age: %d, Job: %s, Years Worked: %d", getName(), getAge(), getJob(), getYearsWorked());      
    }   
     
}

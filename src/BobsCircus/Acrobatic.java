package BobsCircus;


public class Acrobatic extends Person {
    private String job;

    public Acrobatic(String name, int age, int yearsWorked, String job, String imagePath) {
        //similar to the clerk class, I added imagePath to the constructor to fit with the Person class
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

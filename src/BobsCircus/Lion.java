package BobsCircus;
import java.util.Objects;

public class Lion implements Animal {
	//instance variables
    private String name;
    private int age;
    private String species;
    private String color;
    private String imagePath;

    //constructor
    public Lion (String name, int age, String species, String color, String imagePath) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.color = color;
        this.imagePath = imagePath;

    }

    @Override
    public void move() {
        System.out.println("Pounces!\n");
    }
    
    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }
    
    @Override
    public String getName() { return name; }

    @Override
    public int getAge() { return age; }

    @Override
    public String getSpecies() { return species; }

    @Override
    public String getColor() { return color; }

    @Override
    public String getImagePath() { return imagePath; }

    @Override
	public boolean equals(Object object) {
    	if(this == object) {
    		return true;
    	}
    	
    	if (object == null || getClass() != object.getClass()) {
    		return false;
    	}
    	
    	Lion other = (Lion) object;
    	return age == other.age && Objects.equals(name, other.name) && Objects.equals(species, other.species) && Objects.equals(color, other.color) && Objects.equals(imagePath, other.imagePath);	
    }
    
    @Override
    public String toString() {
        return String.format("Lion [Name: %s, Age: %d, Species: %s, Color: %s]", name, age, species, color);
    }
}
	

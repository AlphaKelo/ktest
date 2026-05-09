package BobsCircus;
import java.util.ArrayList;
import java.util.List;

public class Circus {
    private List<Animal> animals;
    private List<Person> persons;
    private List<Building> buildings;
    private List<Ticket> tickets;

    public Circus() {
        animals = new ArrayList<>();
        persons = new ArrayList<>();
        buildings = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    // Add building
    public void addBuilding(Building building) {
    	buildings.add(building);
    }

    // Display building
    public void displayBuildings() {
    	for (Building building : buildings) {
    		System.out.println(building.toString());
    	}
    }

    // Add person
    public void addPerson(Person person) {
    	persons.add(person);
    }

    // Display person
    public void displayPersons() {
    	for (Person person : persons) {
    		System.out.println(person.toString());
    	}
    }

    // Add animal
    public void addAnimal(Animal animal) {
    	animals.add(animal);
    }

    // Display animal using toString() method
    public void displayAnimals() {
    	for (Animal animal : animals) {
    		System.out.println(animal.toString());
    	}
    }

    // Selection sort to sort animals by age
    public void sortAnimalsByAge() {
    	for(int startScan = 0; startScan < animals.size() - 1; startScan++) {
    		int minIndex = startScan;
    		
    		for (int index = startScan + 1; index < animals.size(); index++) {
    			if(animals.get(index).getAge() < animals.get(minIndex).getAge()) {
    				minIndex = index;
    			}
    		}
    		
    		Animal temp = animals.get(startScan);
    		animals.set(startScan, animals.get(minIndex));
    		animals.set(minIndex, temp);
    	}
    }
    
    // Selection sort to sort animals by name
    public void sortAnimalsByName() {
    	for(int startScan = 0; startScan < animals.size() - 1; startScan++) {
    		int minIndex = startScan;
    		
    		for (int index = startScan + 1; index < animals.size(); index++) {
    			if(animals.get(index).getName().compareToIgnoreCase(animals.get(minIndex).getName()) < 0) {
    				minIndex = index;
    			}
    		}
    		
    		Animal temp = animals.get(startScan);
    		animals.set(startScan, animals.get(minIndex));
    		animals.set(minIndex, temp);
    	}
    }
    
    // Search for an animal by name
    public Animal searchAnimalByName(String name) {
    	for(Animal animal : animals) {
    		if (animal.getName().equalsIgnoreCase(name)) {
    			return animal;
    		}
    	}
    	return null;
    }
    
    // Add ticket
    public void addTicket(Ticket ticket) {
    	tickets.add(ticket);
    }

    // Generate ticket
    public Ticket generateTicket(String dayOfWeek, double basePrice, int age) {
        Ticket ticket = new Ticket(dayOfWeek, basePrice, age);  // Pass dayOfWeek, basePrice, age to Ticket constructor
        addTicket(ticket);
        return ticket;
    }
}

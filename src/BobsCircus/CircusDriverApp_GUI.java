package BobsCircus;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;	
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class CircusDriverApp_GUI extends Application{
	
	private Circus circus;
	private TextArea outputArea;
	private ImageView imageView;
	private final List<Animal> animalList = new ArrayList<>();
	private final List<Person> personList = new ArrayList<>();
	private final List<Building> buildingList = new ArrayList<>();
	
	
	public void start(Stage stage) {
		
		circus = new Circus();
		
		outputArea = new TextArea();
		outputArea.setEditable(false);
		outputArea.setLayoutX(25);
		outputArea.setLayoutY(228);
		outputArea.setPrefSize(900, 695);
		
		imageView = new ImageView();
		imageView.setFitWidth(126);
		imageView.setFitHeight(780);
		imageView.setPreserveRatio(true);
		
		Label rightLabel = new Label("Image + Details");
        rightLabel.setStyle("-fx-font-size: 18px;");

        StackPane imageBox = new StackPane(imageView);
        imageBox.setPrefSize(126, 780);
        imageBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");

        VBox rightPanel = new VBox(16, rightLabel, imageBox);
        rightPanel.setLayoutX(948);
        rightPanel.setLayoutY(68);
        rightPanel.setPrefSize(159, 854);
        rightPanel.setPadding(new Insets(18, 15, 15, 15));
        rightPanel.setStyle("-fx-border-color: black; -fx-border-width: 1;");

				
		
		//buttons for Animal
		Button addAnimalButton = new Button("Add Animal");
		Button displayAnimalsButton = new Button("Display All Animals");
		Button sortAgeButton = new Button("Sort Animals by Age");
		Button sortNameButton = new Button("Sort Animals by Name");
		Button searchAnimalButton = new Button("Search Animal By Name");
		
		//buttons for Person
		Button addPersonButton = new Button("Add Person");
		Button displayPersonsButton = new Button("Display All Persons");
		
		//buttons for Buildings
		Button addBuildingButton = new Button("Add Building");
		Button displayBuildingsButton = new Button("Display All Buildings");
		
		//ticket button
		Button generateTicketButton = new Button("Generate Ticket");
		
		//exit button
		Button exitButton = new Button("Exit");

		Button[] buttons = {
				addAnimalButton, addPersonButton, addBuildingButton, generateTicketButton,
				displayAnimalsButton, displayPersonsButton, displayBuildingsButton,
				sortAgeButton, sortNameButton, searchAnimalButton, exitButton
		};
		for(Button button : buttons) {
			button.setStyle("-fx-font-size: 18px; -fx-padding: 6 12 6 12;");
		}
		
		addAnimalButton.setOnAction(e -> {
			ChoiceDialog<String> typeDialog =
					new ChoiceDialog<>("Dog", "Dog", "Horse", "Bird", "Lion");
			typeDialog.setHeaderText("Select Animal Type");
			Optional<String> typeResult = typeDialog.showAndWait();
			if(!typeResult.isPresent()) return;
			
			String type = typeResult.get();
			
			TextInputDialog level1 = new TextInputDialog();
			level1.setHeaderText("Enter name");
			Optional<String> op1 = level1.showAndWait();
			if(!op1.isPresent()) return;
			String name = op1.get();
			
			TextInputDialog level2 = new TextInputDialog();
			level2.setHeaderText("Enter age");
			Optional<String> op2 = level2.showAndWait();
			if(!op2.isPresent()) return;
			int age = Integer.parseInt(op2.get());
			
			TextInputDialog level3 = new TextInputDialog();
			level3.setHeaderText("Enter species");
			Optional<String> op3 = level3.showAndWait();
			if(!op3.isPresent()) return;
			String species = op3.get();
			
			TextInputDialog level4 = new TextInputDialog();
			level4.setHeaderText("Enter color");
			Optional<String> op4 = level4.showAndWait();
			if(!op4.isPresent()) return;
			String color = op4.get();
			
			Animal animal = null;
			String image = null;
			
			switch(type) {
			case "Dog":
				image = "/images/dog.jpg";
				animal = new Dog(name, age, species, color, image);
				break;
			case "Horse":
				image = "/images/horse.jpg";
				animal = new Horse(name, age, species, color, image);
				break;
			case "Bird":
				image = "/images/bird.jpg";
				animal = new Bird(name, age, species, color, image);
				break;
			case "Lion":
				image = "/images/lion.png";
				animal = new Lion(name, age, species, color, image);
				break;
			}
			
			circus.addAnimal(animal);
			animalList.add(animal);
			appendAnimalSummary(animal);
					
			setImage(image);
		
		});
		
		displayAnimalsButton.setOnAction(e -> {
		    displayAllAnimals();
		});
		
		sortAgeButton.setOnAction(e -> {
			circus.sortAnimalsByAge();
			animalList.sort(Comparator.comparingInt(Animal::getAge));
			displayAllAnimals();
		});
		sortNameButton.setOnAction(e -> {
			circus.sortAnimalsByName();
			animalList.sort(Comparator.comparing(animal -> animal.getName().toLowerCase()));
			displayAllAnimals();
		});
		
		searchAnimalButton.setOnAction(e -> {
			TextInputDialog level1 = new TextInputDialog();
			level1.setHeaderText("Enter animal name:");
			Optional<String> op1 = level1.showAndWait();
			if(!op1.isPresent()) return;
			String name = op1.get();
			
			Animal found = circus.searchAnimalByName(name);
			
			if(found != null) {
				outputArea.setText(formatAnimalDetails(found));
				setImage(found.getImagePath());
			}
			else {
				outputArea.setText("No animal found with the name: " + name + "\n");
			}
			
			
		});

		addPersonButton.setOnAction(e -> {
			ChoiceDialog<String> typeDialog =
					new ChoiceDialog<>("Clerk", "Clerk", "Acrobatic");
			typeDialog.setHeaderText("Select Person Type");
			Optional<String> typeResult = typeDialog.showAndWait();
			if(!typeResult.isPresent()) return;
			
			String type = typeResult.get();
			
			TextInputDialog level1 = new TextInputDialog();
			level1.setHeaderText("Enter name");
			Optional<String> op1 = level1.showAndWait();
			if(!op1.isPresent()) return;
			String name = op1.get();
			
			TextInputDialog level2 = new TextInputDialog();
			level2.setHeaderText("Enter age");
			Optional<String> op2 = level2.showAndWait();
			if(!op2.isPresent()) return;
			int age = Integer.parseInt(op2.get());
			
			TextInputDialog level3 = new TextInputDialog();
			level3.setHeaderText("Enter years worked");
			Optional<String> op3 = level3.showAndWait();
			if(!op3.isPresent()) return;
			int years =  Integer.parseInt(op3.get());
			
			TextInputDialog level4 = new TextInputDialog();
			level4.setHeaderText("Enter job");
			Optional<String> op4 = level4.showAndWait();
			if(!op4.isPresent()) return;
			String job = op4.get();
			
			Person person = null;
			String image = null;
			
			if(type.equals("Clerk")) {
				image = "/images/clerk.jpg";
				person = new Clerk(name, age, years, job, image);
			}
			else {
				image = "/images/acrobat.jpg";
				person = new Acrobatic(name, age, years, job, image);
			}
			
			circus.addPerson(person);
			personList.add(person);
			outputArea.appendText(formatPerson(person) + "\n");
			setImage(image);
		
		});
		
		displayPersonsButton.setOnAction(e -> {
		    displayAllPersons();
		});
		
		addBuildingButton.setOnAction(e -> {
			ChoiceDialog<String> typeDialog =
					new ChoiceDialog<>("Arena", "Arena", "Ticketing Office");
			typeDialog.setHeaderText("Select Building Type");
			Optional<String> typeResult = typeDialog.showAndWait();
			if(!typeResult.isPresent()) return;
			
			String type = typeResult.get();
			
			TextInputDialog level1 = new TextInputDialog();
			level1.setHeaderText("Enter color");
			Optional<String> op1 = level1.showAndWait();
			if(!op1.isPresent()) return;
			String color = op1.get();
			
			TextInputDialog level2 = new TextInputDialog();
			level2.setHeaderText("Enter length");
			Optional<String> op2 = level2.showAndWait();
			if(!op2.isPresent()) return;
			double length = Double.parseDouble(op2.get());
			
			TextInputDialog level3 = new TextInputDialog();
			level3.setHeaderText("Enter width");
			Optional<String> op3 = level3.showAndWait();
			if(!op3.isPresent()) return;
			double width =  Double.parseDouble(op3.get());
			
			Building building = null;
			String image = null;
			
			if(type.equals("Arena")) {
				image = "/images/arena.jpg";
				building = new Arena(color, length, width, image);
			}
			else {
				image = "/images/ticketoffice.jpg";
				building = new TicketingOffice(color, length, width, image);
			}
			
			circus.addBuilding(building);
			buildingList.add(building);
			outputArea.setText(formatBuilding(building));
			setImage(image);
		
		});
		
		displayBuildingsButton.setOnAction(e -> {
		    displayAllBuildings();
		});
		
		generateTicketButton.setOnAction(e -> {
			List<String> ticketLines = new ArrayList<>();
			boolean buyMore = true;
			while(buyMore) {
				String dayChoice = showChoice("Select Day of Week",
						"Monday (10% discount)",
						"Monday (10% discount)",
						"Tuesday (10% discount)",
						"Wednesday (10% discount)",
						"Thursday (10% discount)",
						"Friday (10% discount)",
						"Saturday (0% discount)",
						"Sunday (0% discount)");
				if(dayChoice == null) return;
				
				String customerChoice = showChoice("Select Customer Type",
						"Child (10% discount)",
						"Child (10% discount)",
						"Student (10% discount)",
						"Adult",
						"Senior (5% discount)");
				if(customerChoice == null) return;
				
				String seatChoice = showChoice("Select Seat Level",
						"Upper Level (5% discount)",
						"Lower Level (No extra charge)",
						"T-Level (x2 ticket price)",
						"Upper Level (5% discount)");
				if(seatChoice == null) return;
				
				TextInputDialog priceDialog = new TextInputDialog();
				priceDialog.setHeaderText("Enter base price:");
				Optional<String> priceResult = priceDialog.showAndWait();
				if(!priceResult.isPresent()) return;
				double basePrice = Double.parseDouble(priceResult.get());
				
				TextInputDialog ticketsDialog = new TextInputDialog();
				ticketsDialog.setHeaderText("Enter number of tickets:");
				Optional<String> ticketsResult = ticketsDialog.showAndWait();
				if(!ticketsResult.isPresent()) return;
				int ticketCount = Integer.parseInt(ticketsResult.get());
				
				ticketLines.add(formatTicketLine(ticketCount, customerChoice, basePrice, dayChoice, seatChoice));
				
				Alert moreTicketsDialog = new Alert(Alert.AlertType.CONFIRMATION);
				moreTicketsDialog.setTitle("Confirmation");
				moreTicketsDialog.setHeaderText("Do you want to buy more tickets?");
				moreTicketsDialog.setContentText("Click OK for Yes, Cancel for No");
				Optional<ButtonType> moreTicketsResult = moreTicketsDialog.showAndWait();
				buyMore = moreTicketsResult.isPresent() && moreTicketsResult.get() == ButtonType.OK;
			}
			
			outputArea.setText(formatTicketDetails(ticketLines));
			setImage("/images/ticketoffice.jpg");
		
		});
		
		exitButton.setOnAction(e -> stage.close());
		
		HBox row1 = new HBox(16, addAnimalButton, addPersonButton, addBuildingButton, generateTicketButton, displayAnimalsButton);
		HBox row2 = new HBox(16, displayPersonsButton, displayBuildingsButton, sortAgeButton, sortNameButton);
		HBox row3 = new HBox(16, searchAnimalButton, exitButton);

			
		row1.setAlignment(Pos.CENTER);
		row2.setAlignment(Pos.CENTER);
		row3.setAlignment(Pos.CENTER);
			
		VBox topBar = new VBox(15, row1, row2, row3);
		topBar.setLayoutX(25);
		topBar.setLayoutY(68);
		topBar.setPrefWidth(900);
			
		Pane root = new Pane(topBar, outputArea, rightPanel);
		root.setPrefSize(1200, 915);
			
		Scene scene = new Scene(root, 1200, 915);
		stage.setTitle("Bob's Circus Management System");
		stage.setScene(scene);
		stage.show();
	}

	private void appendAnimalSummary(Animal animal) {
		outputArea.appendText(animal.toString() + "\n");
		outputArea.appendText(getAnimalSound(animal) + "\n");
		outputArea.appendText(getAnimalMovement(animal) + "\n\n");
	}
	
	private void displayAllAnimals() {
		outputArea.clear();
		for(Animal animal : animalList) {
			appendAnimalSummary(animal);
		}
	}
	
	private String formatAnimalDetails(Animal animal) {
		return "Animal Details:\n"
				+ "---------------\n"
				+ "Name: " + animal.getName() + "\n"
				+ "Age: " + animal.getAge() + "\n"
				+ "Species: " + animal.getSpecies() + "\n"
				+ "Color: " + animal.getColor() + "\n"
				+ getAnimalSound(animal) + "\n"
				+ getAnimalMovement(animal) + "\n";
	}
	
	private String getAnimalSound(Animal animal) {
		if(animal instanceof Dog) {
			return "Bark!";
		}
		else if(animal instanceof Horse) {
			return "Neigh!";
		}
		else if(animal instanceof Bird) {
			return "Squawk!";
		}
		else if(animal instanceof Lion) {
			return "Roar!";
		}
		return "";
	}
	
	private String getAnimalMovement(Animal animal) {
		if(animal instanceof Dog) {
			return "Walks around.";
		}
		else if(animal instanceof Horse) {
			return "Gallops.";
		}
		else if(animal instanceof Bird) {
			return "Flies.";
		}
		else if(animal instanceof Lion) {
			return "Pounces!";
		}
		return "";
	}
	
	private void displayAllPersons() {
		outputArea.clear();
		for(Person person : personList) {
			outputArea.appendText(formatPerson(person) + "\n");
		}
	}
	
	private String formatPerson(Person person) {
		String personType = person instanceof Clerk ? "Clerk" : "Acrobatic";
		return personType + " - " + person.toString();
	}
	
	private void displayAllBuildings() {
		outputArea.clear();
		for(Building building : buildingList) {
			outputArea.appendText(formatBuilding(building));
		}
	}
	
	private String formatBuilding(Building building) {
		return "Building Type: " + building.getBuildingType() + "\n"
				+ "Color: " + building.getColor() + "\n"
				+ String.format("Size: %.1f x %.1f%n", building.getLength(), building.getWidth());
	}
	
	private String showChoice(String header, String defaultChoice, String... choices) {
		ChoiceDialog<String> dialog = new ChoiceDialog<>(defaultChoice, choices);
		dialog.setTitle("Confirmation");
		dialog.setHeaderText(header);
		Optional<String> result = dialog.showAndWait();
		return result.orElse(null);
	}
	
	private String formatTicketLine(int ticketCount, String customerChoice, double basePrice,
			String dayChoice, String seatChoice) {
		String customerType = customerChoice.split(" ")[0];
		String day = dayChoice.split(" ")[0].toUpperCase();
		int dayDiscount = dayChoice.contains("10%") ? 10 : 0;
		int customerDiscount = customerChoice.contains("10%") ? 10 : customerChoice.contains("5%") ? 5 : 0;
		double total = ticketCount * basePrice;
		
		total *= (1 - dayDiscount / 100.0);
		total *= (1 - customerDiscount / 100.0);
		
		if(seatChoice.startsWith("Upper Level")) {
			total -= ticketCount * basePrice * 0.05;
		}
		else if(seatChoice.startsWith("T-Level")) {
			total *= 2;
		}
		
		return String.format("%d %s $%.2f (Day: %s, Day Discount: %d%%, Customer Type Discount: %d%%, Seat: %s)",
				ticketCount, customerType, total, day, dayDiscount, customerDiscount, seatChoice);
	}
	
	private String formatTicketDetails(List<String> ticketLines) {
		StringBuilder details = new StringBuilder();
		details.append("Ticket Calculation Details:\n");
		details.append("---------------------------\n");
		for(String ticketLine : ticketLines) {
			details.append(ticketLine).append("\n");
		}
		details.append("\nEnjoy the show!\n");
		return details.toString();
	}
	
	private void setImage(String imagePath) {
		InputStream imageStream = getClass().getResourceAsStream(imagePath);
		if(imageStream != null) {
			imageView.setImage(new Image(imageStream));
		}
		else {
			imageView.setImage(null);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
	
	

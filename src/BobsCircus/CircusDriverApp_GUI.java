package BobsCircus;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;	
import javafx.geometry.Insets;


public class CircusDriverApp_GUI extends Application{
	
	private Circus circus;
	private TextArea outputArea;
	private ImageView imageView;
	
	
	public void start(Stage stage) {
		
		circus = new Circus();
		
		outputArea = new TextArea();
		outputArea.setEditable(false);
		
		Label outputLabel = new Label("Output");
		outputLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
		VBox centerBox = new VBox(5, outputLabel, outputArea);
		centerBox.setPadding(new Insets(10));
		centerBox.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		
		imageView = new ImageView();
		imageView.setFitWidth(250);
		imageView.setPreserveRatio(true);
		
		Label rightLabel = new Label("Image + Details");
        rightLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        VBox rightPanel = new VBox(10, rightLabel, imageView);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setStyle("-fx-border-color: black; -fx-border-width: 1;");

				
		
		//buttons for Animal
		Button addAnimalButton = new Button("Add Animal");
		Button displayAnimalsButton = new Button("Display All Animals");
		Button sortAgeButton = new Button("Sort Animals By Age");
		Button sortNameButton = new Button("Sort Animals By Name");
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
		
		addAnimalButton.setOnAction(e -> {
			outputArea.appendText("Add Animal\n");
			
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
			outputArea.appendText(animal.toString() +"\n");
			imageView.setImage(new Image(getClass().getResourceAsStream(image)));
		
		});
		
		displayAnimalsButton.setOnAction(e -> {
		    outputArea.appendText("Display All Animals\n");
		    circus.displayAnimals();
		});
		
		sortAgeButton.setOnAction(e -> outputArea.appendText("Sort Animals By Age\n"));
		sortNameButton.setOnAction(e -> outputArea.appendText("Sort Animals By Name\n"));
		
		searchAnimalButton.setOnAction(e -> {
			outputArea.appendText("Search Animal By Name\n");
			
			
			TextInputDialog level1 = new TextInputDialog();
			level1.setHeaderText("Enter animal name");
			Optional<String> op1 = level1.showAndWait();
			if(!op1.isPresent()) return;
			String name = op1.get();
			
			Animal found = circus.searchAnimalByName(name);
			
			if(found != null) {
				outputArea.appendText("Found: " + found.toString() + "\n");
				imageView.setImage(new Image(getClass().getResourceAsStream(found.getImagePath())));				}
			else {
				outputArea.appendText("Animal not found\n");
				}
			
			
		});

		addPersonButton.setOnAction(e -> {
			outputArea.appendText("Add Person\n");
			
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
			outputArea.appendText(person.toString() +"\n");
			imageView.setImage(new Image(getClass().getResourceAsStream(image)));
		
		});
		
		displayPersonsButton.setOnAction(e -> { outputArea.appendText("Display All Persons\n");
		    circus.displayPersons();
		});
		
		addBuildingButton.setOnAction(e -> {
			outputArea.appendText("Add Building\n");
			
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
			outputArea.appendText(building.toString() +"\n");
			imageView.setImage(new Image(getClass().getResourceAsStream(image)));
		
		});
		
		displayBuildingsButton.setOnAction(e -> {
		    outputArea.appendText("Display All Buildings\n");
		    circus.displayBuildings();
		});
		
		generateTicketButton.setOnAction(e -> {
			outputArea.appendText("Generate Ticket\n");
			
			
			TextInputDialog level1 = new TextInputDialog();
			level1.setHeaderText("Enter day of week");
			Optional<String> op1 = level1.showAndWait();
			if(!op1.isPresent()) return;
			String day = op1.get();
			
			TextInputDialog level2 = new TextInputDialog();
			level2.setHeaderText("Enter base price");
			Optional<String> op2 = level2.showAndWait();
			if(!op2.isPresent()) return;
			double basePrice = Double.parseDouble(op2.get());
			
			TextInputDialog level3 = new TextInputDialog();
			level3.setHeaderText("Enter age");
			Optional<String> op3 = level3.showAndWait();
			if(!op3.isPresent()) return;
			int age =  Integer.parseInt(op3.get());
			
			Ticket ticket = circus.generateTicket(day, basePrice, age);
			
			outputArea.appendText(ticket.toString() + "\n");
			outputArea.appendText("Do you want to buy more tickets? YES\n");
			imageView.setImage(new Image(getClass().getResourceAsStream("/images/ticketoffice.jpg")));
		
		});
		
		exitButton.setOnAction(e -> stage.close());
		
		HBox row1 = new HBox(10, addAnimalButton, addPersonButton, addBuildingButton, generateTicketButton);
		HBox row2 = new HBox(10, displayAnimalsButton, displayPersonsButton, displayBuildingsButton, sortAgeButton, sortNameButton, searchAnimalButton, exitButton);

		
		row1.setPadding(new Insets(5));
		row2.setPadding(new Insets(5));
		
		VBox topBar = new VBox(15, row1, row2);
		topBar.setPadding(new Insets(10));
		
		BorderPane root = new BorderPane();
		root.setTop(topBar);
		root.setCenter(centerBox);
		root.setRight(rightPanel);
		
		Scene scene = new Scene(root, 1100, 600);
		stage.setTitle("Bob's Circus GUI");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
	
	


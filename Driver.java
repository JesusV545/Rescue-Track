import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<>(); //initializing the new monkey array list
    // Instance variables (if needed)

    public static void main(String[] args) {
    	
        initializeDogList();
        initializeMonkeyList();
        
        Scanner scnr = new Scanner(System.in);
        String choice;
        
        //menu loop for user interaction
        do {
        	displayMenu(); //displays menu
        	choice = scnr.nextLine();
        	
            // Handle user input using if-else statements
            if (choice.equals("1")) {
                intakeNewDog(scnr); // Option to in take a new dog
            } else if (choice.equals("2")) {
                intakeNewMonkey(scnr); // Option to in take a new monkey
            } else if (choice.equals("3")) {
                reserveAnimal(scnr); // Option to reserve an animal
            } else if (choice.equals("4")) {
                printAnimals("dog"); // Print a list of all dogs
            } else if (choice.equals("5")) {
                printAnimals("monkey"); // Print a list of all monkeys
            } else if (choice.equals("6")) {
                printAnimals("available"); // Print a list of all available animals
            } else if (choice.equals("q")) {
                System.out.println("Exiting application."); // Exit the program
            } else {
                System.out.println("Invalid input. Please try again."); // Handle invalid input
            }
        	
        } while(!choice.equals("q")); //exits the loop when user inputs 'q'

    }

    // This method prints the menu options
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }


    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }


    // Adds monkeys to a list for testing
    public static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("Ceaser", "male", "5", "19.5", "02-04-2000", "Africa", "in service", false, "United States", 24.5, 40.0, 60.0, "Guenon");
        monkeyList.add(monkey1);
    }


    // lets user add dog into the system
    public static void intakeNewDog(Scanner scanner) {
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine();
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                return; //returns to menu
            }
        }
        
        //obtain more information about dog with appropriate data types
        System.out.println("Enter dog's breed:");
        String breed = scanner.nextLine();
        System.out.println("Enter gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter age:");
        String age = scanner.nextLine();
        System.out.println("Enter weight:");
        String weight = scanner.nextLine();
        System.out.println("Enter acquisition date:");
        String acquisitionDate = scanner.nextLine();
        System.out.println("Enter acquisition country:");
        String acquisitionCountry = scanner.nextLine();
        System.out.println("Enter training status:");
        String trainingStatus = scanner.nextLine();
        
        // creates new Dog object and then adds it to the list
        Dog newDog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, false, "N/A");
        dogList.add(newDog);
        System.out.println("Dog added.");
    }

    
    public static void intakeNewMonkey(Scanner scanner) {
        System.out.println("Enter monkey name:");
        String name = scanner.nextLine();

        // checking if the monkey already exists in the system
        for (Monkey monkey : monkeyList) {
            if (monkey.getName().equalsIgnoreCase(name)) {
                System.out.println("This monkey is already in our system.");
                return;
            }
        }
        
        //need to get monkey species that are valid 
        System.out.println("Enter species (Capuchin, Guenon, Macaque, Marmoset, Squirrel monkey, Tamarin):");
        String species = scanner.nextLine();
        
        //array of valid species for validation
        String[] validSpecies = {"Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel monkey", "Tamarin"};
        
        //need to make sure user input for species is valid
        if (!Arrays.asList(validSpecies).contains(species)) {
            System.out.println("Invalid species. Intake failed.");
            return;
        }
        
        //obtain more information about monkey with appropriate data types
        System.out.println("Enter gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter age:");
        String age = scanner.nextLine();
        System.out.println("Enter weight:");
        String weight = scanner.nextLine();
        System.out.println("Enter acquisition date:");
        String acquisitionDate = scanner.nextLine();
        System.out.println("Enter acquisition country:");
        String acquisitionCountry = scanner.nextLine();
        System.out.println("Enter tail length:");
        double tailLength = scanner.nextDouble();
        System.out.println("Enter height:");
        double height = scanner.nextDouble();
        System.out.println("Enter body length:");
        double bodyLength = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.println("Enter training status:");
        String trainingStatus = scanner.nextLine();
        
        //creates a new Monkey object and adds it to the list
        Monkey newMonkey = new Monkey(name, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, false, "N/A", tailLength, height, bodyLength, species);
        monkeyList.add(newMonkey);
        System.out.println("Monkey added.");
    }

    // find the animal by animal type and in service country
    public static void reserveAnimal(Scanner scanner) {
        System.out.println("Enter animal type (dog/monkey):"); //prompt user to establish what animal they want to reserve
        String type = scanner.nextLine();
        System.out.println("Enter in-service country:"); // enter the country where animal is in service
        String country = scanner.nextLine();
        
        if (type.equals("dog")) {
            // Search for an available dog
            for (Dog dog : dogList) {
                if (dog.getInServiceLocation().equalsIgnoreCase(country) && !dog.getReserved()) {
                    dog.setReserved(true); // marking the dog reserved
                    System.out.println("Dog reserved.");
                    return; //exit after reserving
                }
            }
            
            System.out.println("No available dogs found in " + country + "."); //if no dogs are found
            
        } else if (type.equals("monkey")) {
            // Search for an available monkey
            for (Monkey monkey : monkeyList) {
                if (monkey.getInServiceLocation().equalsIgnoreCase(country) && !monkey.getReserved()) {
                    monkey.setReserved(true); //marking the monkey reserved
                    System.out.println("Monkey reserved successfully!");
                    return; //exit after reserving
                }
            }
            
            System.out.println("No available monkeys found in " + country + "."); // if no monkeys are found
            
        } else {
            // Invalid animal type
            System.out.println("Invalid animal type. Please choose 'dog' or 'monkey'.");
        }

    }

    //prints the animals that are requested
    public static void printAnimals(String listType) {
        if (listType.equalsIgnoreCase("dog")) {
            for (Dog dog : dogList) {
                System.out.println(dog.getName() + " | " + dog.getTrainingStatus() + " | " +
                                   dog.getAcquisitionLocation() + " | Reserved: " + dog.getReserved());
            }
        } else if (listType.equalsIgnoreCase("monkey")) {
            for (Monkey monkey : monkeyList) {
                System.out.println(monkey.getName() + " | " + monkey.getTrainingStatus() + " | " +
                                   monkey.getAcquisitionLocation() + " | Reserved: " + monkey.getReserved());
            }
        } else if (listType.equalsIgnoreCase("available")) {
            boolean foundAvailable = false; // tracks if any available animals are printed

            // check dogs
            for (Dog dog : dogList) {
                if (dog.getTrainingStatus().equalsIgnoreCase("in service") && !dog.getReserved()) {
                    System.out.println("Dog: " + dog.getName() + " | Location: " + dog.getAcquisitionLocation());
                    foundAvailable = true;
                }
            }

            // check monkeys
            for (Monkey monkey : monkeyList) {
                if (monkey.getTrainingStatus().equalsIgnoreCase("in service") && !monkey.getReserved()) {
                    System.out.println("Monkey: " + monkey.getName() + " | Location: " + monkey.getAcquisitionLocation() +
                                       " | Species: " + monkey.getSpecies());
                    foundAvailable = true;
                }
            }

            if (!foundAvailable) {
                System.out.println("No available animals found.");
            }
        } else {
            System.out.println("Invalid list type.");
        }
    }



}


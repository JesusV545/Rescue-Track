import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Provides the console interface for the Rescue-Track application.
 *
 * Animal storage and business rules are handled by AnimalService,
 * while InputValidator handles user-input validation.
 */
public class Driver {

    private static final AnimalService ANIMAL_SERVICE =
            new AnimalService();

    private static final String[] ANIMAL_TYPES = {
        "dog", "monkey"
    };

    private static final String[] MONKEY_SPECIES = {
        "Capuchin",
        "Guenon",
        "Macaque",
        "Marmoset",
        "Squirrel monkey",
        "Tamarin"
    };

    private static final String[] GENDERS = {
    "male",
    "female"
    };

    private static final String[] TRAINING_STATUSES = {
        "intake",
        "Phase I",
        "Phase II",
        "Phase III",
        "Phase IV",
        "Phase V",
        "in service",
        "farm"
    };

    /**
     * Starts the Rescue-Track application.
     */
    public static void main(String[] args) {
        initializeAnimalData();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                displayMenu();
                String choice = scanner.nextLine().trim().toLowerCase();

                switch (choice) {
                    case "1":
                        intakeNewDog(scanner);
                        break;

                    case "2":
                        intakeNewMonkey(scanner);
                        break;

                    case "3":
                        reserveAnimal(scanner);
                        break;

                    case "4":
                        printAnimals(ANIMAL_SERVICE.getDogs());
                        break;

                    case "5":
                        printAnimals(ANIMAL_SERVICE.getMonkeys());
                        break;

                    case "6":
                        printAnimals(
                                ANIMAL_SERVICE.getAvailableAnimals());
                        break;

                    case "q":
                        running = false;
                        System.out.println(
                                "Exiting Rescue-Track.");
                        break;

                    default:
                        System.out.println(
                                "Invalid selection. Enter 1-6 or q.");
                        break;
                }
            }
        }
    }

    /**
     * Displays the main application menu.
     */
    private static void displayMenu() {
        System.out.println("\n=== Rescue-Track Menu ===");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Display all dogs");
        System.out.println("[5] Display all monkeys");
        System.out.println(
                "[6] Display available in-service animals");
        System.out.println("[q] Quit");
        System.out.print("Selection: ");
    }

    /**
     * Collects validated information and adds a new dog.
     */
    private static void intakeNewDog(Scanner scanner) {
        String name = InputValidator.readRequiredText(
                scanner, "Dog name: ");

        if (ANIMAL_SERVICE.containsAnimalName(name)) {
            System.out.println(
                    "An animal with that name is already registered.");
            return;
        }

        String breed = InputValidator.readRequiredText(
                scanner, "Breed: ");

        String gender = InputValidator.readOption(
                scanner,
                "Gender: ",
                GENDERS);

        int age = InputValidator.readNonNegativeInt(
                scanner, "Age: ");

        double weight = InputValidator.readPositiveDouble(
                scanner, "Weight: ");

        LocalDate acquisitionDate = InputValidator.readDate(
                scanner, "Acquisition date");

        String acquisitionCountry =
                InputValidator.readRequiredText(
                        scanner, "Acquisition country: ");

        String trainingStatus =
                InputValidator.readOption(
                        scanner,
                        "Training status: ",
                        TRAINING_STATUSES);

        String inServiceCountry =
                InputValidator.readRequiredText(
                        scanner, "In-service country: ");

        try {
            Dog newDog = new Dog(
                    name,
                    breed,
                    gender,
                    age,
                    weight,
                    acquisitionDate,
                    acquisitionCountry,
                    trainingStatus,
                    false,
                    inServiceCountry);

            if (ANIMAL_SERVICE.addDog(newDog)) {
                System.out.println("Dog added successfully.");
            } else {
                System.out.println(
                        "The dog could not be added.");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(
                    "Dog intake failed: "
                            + exception.getMessage());
        }
    }

    /**
     * Collects validated information and adds a new monkey.
     */
    private static void intakeNewMonkey(Scanner scanner) {
        String name = InputValidator.readRequiredText(
                scanner, "Monkey name: ");

        if (ANIMAL_SERVICE.containsAnimalName(name)) {
            System.out.println(
                    "An animal with that name is already registered.");
            return;
        }

        String species = InputValidator.readOption(
                scanner,
                "Species: ",
                MONKEY_SPECIES);
        String gender = InputValidator.readOption(
                scanner,
                "Gender: ",
                GENDERS);

        int age = InputValidator.readNonNegativeInt(
                scanner, "Age: ");

        double weight = InputValidator.readPositiveDouble(
                scanner, "Weight: ");

        LocalDate acquisitionDate = InputValidator.readDate(
                scanner, "Acquisition date");

        String acquisitionCountry =
                InputValidator.readRequiredText(
                        scanner, "Acquisition country: ");

        String trainingStatus =
                InputValidator.readOption(
                        scanner,
                        "Training status: ",
                        TRAINING_STATUSES);

        String inServiceCountry =
                InputValidator.readRequiredText(
                        scanner, "In-service country: ");

        double tailLength = InputValidator.readPositiveDouble(
                scanner, "Tail length: ");

        double height = InputValidator.readPositiveDouble(
                scanner, "Height: ");

        double bodyLength = InputValidator.readPositiveDouble(
                scanner, "Body length: ");

        try {
            Monkey newMonkey = new Monkey(
                    name,
                    gender,
                    age,
                    weight,
                    acquisitionDate,
                    acquisitionCountry,
                    trainingStatus,
                    false,
                    inServiceCountry,
                    tailLength,
                    height,
                    bodyLength,
                    species);

            if (ANIMAL_SERVICE.addMonkey(newMonkey)) {
                System.out.println(
                        "Monkey added successfully.");
            } else {
                System.out.println(
                        "The monkey could not be added.");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(
                    "Monkey intake failed: "
                            + exception.getMessage());
        }
    }

    /**
     * Reserves the first matching available animal.
     */
    private static void reserveAnimal(Scanner scanner) {
        String animalType = InputValidator.readOption(
                scanner,
                "Animal type (dog/monkey): ",
                ANIMAL_TYPES);

        String country = InputValidator.readRequiredText(
                scanner, "In-service country: ");

        boolean reserved =
                ANIMAL_SERVICE.reserveFirstAvailable(
                        animalType, country);

        if (reserved) {
            System.out.println(
                    "Animal reserved successfully.");
        } else {
            System.out.println(
                    "No matching in-service animal is available.");
        }
    }

    /**
     * Displays a collection of animals in a consistent format.
     */
    private static void printAnimals(
            List<? extends RescueAnimal> animals) {

        if (animals.isEmpty()) {
            System.out.println("No matching animals found.");
            return;
        }

        System.out.println(
                "\nType | Name | Training Status"
                        + " | Service Country | Reserved");

        for (RescueAnimal animal : animals) {
            System.out.println(
                    animal.getAnimalType()
                            + " | " + animal.getName()
                            + " | " + animal.getTrainingStatus()
                            + " | "
                            + animal.getInServiceCountry()
                            + " | " + animal.isReserved());
        }
    }

    /**
     * Adds sample records for application testing.
     */
    private static void initializeAnimalData() {
        ANIMAL_SERVICE.addDog(
                new Dog(
                        "Spot",
                        "German Shepherd",
                        "male",
                        1,
                        25.6,
                        LocalDate.of(2019, 5, 12),
                        "United States",
                        "intake",
                        false,
                        "United States"));

        ANIMAL_SERVICE.addDog(
                new Dog(
                        "Rex",
                        "Great Dane",
                        "male",
                        3,
                        35.2,
                        LocalDate.of(2020, 2, 3),
                        "United States",
                        "Phase I",
                        false,
                        "United States"));

        ANIMAL_SERVICE.addDog(
                new Dog(
                        "Bella",
                        "Chihuahua",
                        "female",
                        4,
                        25.6,
                        LocalDate.of(2019, 12, 12),
                        "Canada",
                        "in service",
                        true,
                        "Canada"));

        ANIMAL_SERVICE.addMonkey(
                new Monkey(
                        "Caesar",
                        "male",
                        5,
                        19.5,
                        LocalDate.of(2020, 2, 4),
                        "South Africa",
                        "in service",
                        false,
                        "United States",
                        24.5,
                        40.0,
                        60.0,
                        "Guenon"));
    }
}
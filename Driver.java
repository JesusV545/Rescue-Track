import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Provides the console interface for the Rescue-Track application.
 *
 * Animal storage and business rules are handled by AnimalService,
 * while AnimalRepository provides persistent SQLite storage.
 */
public class Driver {

    private static final AnimalService ANIMAL_SERVICE =
            new AnimalService();

    private static final DatabaseManager DATABASE_MANAGER =
            new DatabaseManager();

    private static final AnimalRepository ANIMAL_REPOSITORY =
            new AnimalRepository(DATABASE_MANAGER);

    private static final String[] ANIMAL_TYPES = {
        "dog",
        "monkey"
    };

    private static final String[] SEARCH_ANIMAL_TYPES = {
        "all",
        "dog",
        "monkey"
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

    private static final String[] SEARCH_TRAINING_STATUSES = {
        "all",
        "intake",
        "Phase I",
        "Phase II",
        "Phase III",
        "Phase IV",
        "Phase V",
        "in service",
        "farm"
    };

    private static final String[] SORT_OPTIONS = {
        "name",
        "age",
        "date"
    };

    /**
     * Starts the Rescue-Track application.
     */
    public static void main(String[] args) {
        if (!initializeApplicationData()) {
            System.out.println(
                    "Rescue-Track could not access its database.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                displayMenu();

                String choice =
                        scanner.nextLine().trim().toLowerCase();

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
                        printAnimals(
                                ANIMAL_SERVICE.getMonkeys());
                        break;

                    case "6":
                        printAnimals(
                                ANIMAL_SERVICE
                                        .getAvailableAnimals());
                        break;

                    case "7":
                        findAnimalByName(scanner);
                        break;

                    case "8":
                        searchAndSortAnimals(scanner);
                        break;

                    case "q":
                        running = false;
                        System.out.println(
                                "Exiting Rescue-Track.");
                        break;

                    default:
                        System.out.println(
                                "Invalid selection. Enter 1-8 or q.");
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
        System.out.println("[7] Find an animal by name");
        System.out.println("[8] Search and sort animals");
        System.out.println("[q] Quit");
        System.out.print("Selection: ");
    }

    /**
     * Collects validated information and saves a new dog.
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

            // Save first so memory is updated only if SQLite succeeds.
            ANIMAL_REPOSITORY.saveAnimal(newDog);

            if (ANIMAL_SERVICE.addDog(newDog)) {
                System.out.println("Dog added successfully.");
            } else {
                System.out.println(
                        "The dog could not be added.");
            }
        } catch (SQLException | IllegalArgumentException exception) {
            System.out.println(
                    "Dog intake failed: "
                            + exception.getMessage());
        }
    }

    /**
     * Collects validated information and saves a new monkey.
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

        double tailLength =
                InputValidator.readPositiveDouble(
                        scanner, "Tail length: ");

        double height = InputValidator.readPositiveDouble(
                scanner, "Height: ");

        double bodyLength =
                InputValidator.readPositiveDouble(
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

            // Save first so memory is updated only if SQLite succeeds.
            ANIMAL_REPOSITORY.saveAnimal(newMonkey);

            if (ANIMAL_SERVICE.addMonkey(newMonkey)) {
                System.out.println(
                        "Monkey added successfully.");
            } else {
                System.out.println(
                        "The monkey could not be added.");
            }
        } catch (SQLException | IllegalArgumentException exception) {
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

        RescueAnimal animal =
                ANIMAL_SERVICE.findFirstAvailable(
                        animalType, country);

        if (animal == null) {
            System.out.println(
                    "No matching in-service animal is available.");
            return;
        }

        try {
            boolean updated =
                    ANIMAL_REPOSITORY.updateReservation(
                            animal.getName(), true);

            if (updated) {
                animal.setReserved(true);

                System.out.println(
                        "Animal reserved successfully.");
            } else {
                System.out.println(
                        "The animal record was not found "
                                + "in the database.");
            }
        } catch (SQLException exception) {
            System.out.println(
                    "Reservation failed: "
                            + exception.getMessage());
        }
    }

    /**
     * Finds and displays an animal using the HashMap name index.
     */
    private static void findAnimalByName(Scanner scanner) {
        String name = InputValidator.readRequiredText(
                scanner, "Animal name: ");

        RescueAnimal animal =
                ANIMAL_SERVICE.findAnimalByName(name);

        if (animal == null) {
            System.out.println(
                    "No animal was found with that name.");
            return;
        }

        printAnimalHeader();
        printAnimal(animal);
    }

    /**
     * Collects filtering and sorting choices and displays results.
     */
    private static void searchAndSortAnimals(
            Scanner scanner) {

        String animalType = InputValidator.readOption(
                scanner,
                "Animal type (all/dog/monkey): ",
                SEARCH_ANIMAL_TYPES);

        String trainingStatus = InputValidator.readOption(
                scanner,
                "Training status: ",
                SEARCH_TRAINING_STATUSES);

        String serviceCountry =
                InputValidator.readRequiredText(
                        scanner,
                        "Service country (or all): ");

        String sortBy = InputValidator.readOption(
                scanner,
                "Sort by (name/age/date): ",
                SORT_OPTIONS);

        List<RescueAnimal> results =
                ANIMAL_SERVICE.searchAnimals(
                        animalType,
                        trainingStatus,
                        serviceCountry,
                        sortBy);

        printAnimals(results);
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

        printAnimalHeader();

        for (RescueAnimal animal : animals) {
            printAnimal(animal);
        }
    }

    /**
     * Displays the heading used for animal records.
     */
    private static void printAnimalHeader() {
        System.out.println(
                "\nType | Name | Age | Acquisition Date"
                        + " | Training Status"
                        + " | Service Country | Reserved");
    }

    /**
     * Displays one animal record.
     */
    private static void printAnimal(RescueAnimal animal) {
        System.out.println(
                animal.getAnimalType()
                        + " | " + animal.getName()
                        + " | " + animal.getAge()
                        + " | " + animal.getAcquisitionDate()
                        + " | " + animal.getTrainingStatus()
                        + " | " + animal.getInServiceCountry()
                        + " | " + animal.isReserved());
    }

    /**
     * Initializes the database and loads persistent records.
     *
     * Sample animals are inserted only when the database is empty.
     *
     * @return true if initialization succeeds
     */
    private static boolean initializeApplicationData() {
        try {
            DATABASE_MANAGER.initializeDatabase();

            List<RescueAnimal> savedAnimals =
                    ANIMAL_REPOSITORY.getAllAnimals();

            if (savedAnimals.isEmpty()) {
                addInitialAnimalData();
            } else {
                for (RescueAnimal animal : savedAnimals) {
                    addAnimalToService(animal);
                }
            }

            System.out.println(
                    "Animal records loaded from the database.");
            return true;
        } catch (SQLException | IllegalArgumentException exception) {
            System.out.println(
                    "Database initialization failed: "
                            + exception.getMessage());
            return false;
        }
    }

    /**
     * Adds a database animal to the correct in-memory collection.
     */
    private static void addAnimalToService(
            RescueAnimal animal) {

        boolean added;

        if (animal instanceof Dog) {
            added = ANIMAL_SERVICE.addDog((Dog) animal);
        } else if (animal instanceof Monkey) {
            added = ANIMAL_SERVICE.addMonkey(
                    (Monkey) animal);
        } else {
            throw new IllegalArgumentException(
                    "Unsupported animal type.");
        }

        if (!added) {
            throw new IllegalArgumentException(
                    "Duplicate or invalid animal record: "
                            + animal.getName());
        }
    }

    /**
     * Creates and saves the original sample records.
     */
    private static void addInitialAnimalData()
            throws SQLException {

        saveInitialAnimal(
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

        saveInitialAnimal(
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

        saveInitialAnimal(
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

        saveInitialAnimal(
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

    /**
     * Saves one initial animal and adds it to memory.
     */
    private static void saveInitialAnimal(
            RescueAnimal animal) throws SQLException {

        ANIMAL_REPOSITORY.saveAnimal(animal);
        addAnimalToService(animal);
    }
}
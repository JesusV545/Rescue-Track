import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Manages animal records, searches, and business rules.
 */
public class AnimalService {

    // Lists preserve the order in which animals were added.
    private final List<Dog> dogList;
    private final List<Monkey> monkeyList;

    // Provides fast animal lookup using a normalized name.
    private final Map<String, RescueAnimal> animalsByName;

    /**
     * Creates an animal service with empty collections.
     */
    public AnimalService() {
        dogList = new ArrayList<>();
        monkeyList = new ArrayList<>();
        animalsByName = new HashMap<>();
    }

    /**
     * Adds a dog when its name is valid and not already registered.
     *
     * @return true if the dog was added; otherwise, false
     */
    public boolean addDog(Dog dog) {
        if (dog == null
                || !isValidName(dog.getName())
                || containsAnimalName(dog.getName())) {
            return false;
        }

        dogList.add(dog);
        animalsByName.put(normalizeName(dog.getName()), dog);
        return true;
    }

    /**
     * Adds a monkey when its name is valid and not already registered.
     *
     * @return true if the monkey was added; otherwise, false
     */
    public boolean addMonkey(Monkey monkey) {
        if (monkey == null
                || !isValidName(monkey.getName())
                || containsAnimalName(monkey.getName())) {
            return false;
        }

        monkeyList.add(monkey);
        animalsByName.put(normalizeName(monkey.getName()), monkey);
        return true;
    }

    /**
     * Checks the HashMap for a registered animal name.
     *
     * Average lookup time is O(1).
     */
    public boolean containsAnimalName(String name) {
        if (!isValidName(name)) {
            return false;
        }

        return animalsByName.containsKey(normalizeName(name));
    }

    /**
     * Finds an animal by name using the HashMap index.
     *
     * @return the matching animal or null if no match exists
     */
    public RescueAnimal findAnimalByName(String name) {
        if (!isValidName(name)) {
            return null;
        }

        return animalsByName.get(normalizeName(name));
    }

    /**
     * Filters and sorts animal records.
     *
     * Entering "all" for a filter includes every value in that category.
     * Valid sort options are name, age, and date.
     *
     * @return a new list containing the matching animals
     */
    public List<RescueAnimal> searchAnimals(
            String animalType,
            String trainingStatus,
            String serviceCountry,
            String sortBy) {

        List<RescueAnimal> results = new ArrayList<>();

        addMatchingAnimals(
                results,
                dogList,
                animalType,
                trainingStatus,
                serviceCountry);

        addMatchingAnimals(
                results,
                monkeyList,
                animalType,
                trainingStatus,
                serviceCountry);

        // Sort the copied results without changing stored list order.
        sortAnimals(results, sortBy);

        return results;
    }

    /**
     * Adds matching animals from one collection to the result list.
     */
    private void addMatchingAnimals(
            List<RescueAnimal> results,
            List<? extends RescueAnimal> animals,
            String animalType,
            String trainingStatus,
            String serviceCountry) {

        for (RescueAnimal animal : animals) {
            if (matchesFilter(
                    animal,
                    animalType,
                    trainingStatus,
                    serviceCountry)) {

                results.add(animal);
            }
        }
    }

    /**
     * Checks whether an animal matches all selected filters.
     */
    private boolean matchesFilter(
            RescueAnimal animal,
            String animalType,
            String trainingStatus,
            String serviceCountry) {

        boolean matchesType =
                isAllOption(animalType)
                        || animal.getAnimalType().equalsIgnoreCase(
                                animalType.trim());

        boolean matchesStatus =
                isAllOption(trainingStatus)
                        || animal.getTrainingStatus().equalsIgnoreCase(
                                trainingStatus.trim());

        boolean matchesCountry =
                isAllOption(serviceCountry)
                        || animal.getInServiceCountry().equalsIgnoreCase(
                                serviceCountry.trim());

        return matchesType && matchesStatus && matchesCountry;
    }

    /**
     * Sorts animals using the selected field.
     */
    private void sortAnimals(
            List<RescueAnimal> animals,
            String sortBy) {

        if (sortBy == null) {
            return;
        }

        switch (sortBy.trim().toLowerCase(Locale.ROOT)) {
            case "name":
                animals.sort(
                        Comparator.comparing(
                                RescueAnimal::getName,
                                String.CASE_INSENSITIVE_ORDER));
                break;

            case "age":
                animals.sort(
                        Comparator.comparingInt(
                                RescueAnimal::getAge));
                break;

            case "date":
                animals.sort(
                        Comparator.comparing(
                                RescueAnimal::getAcquisitionDate));
                break;

            default:
                // Keep insertion order for an invalid sort option.
                break;
        }
    }

    /**
     * Finds the first matching animal that is in service and available.
     *
     * @return the matching animal, or null if none is available
     */
    public RescueAnimal findFirstAvailable(
            String animalType,
            String country) {

        if (animalType == null
                || country == null
                || country.trim().isEmpty()) {
            return null;
        }

        if (animalType.equalsIgnoreCase("dog")) {
            return findAvailableDog(country);
        }

        if (animalType.equalsIgnoreCase("monkey")) {
            return findAvailableMonkey(country);
        }

        return null;
    }

    /**
     * Finds the first available dog in the requested country.
     */
    private Dog findAvailableDog(String country) {
        for (Dog dog : dogList) {
            if (isAvailableInCountry(dog, country)) {
                return dog;
            }
        }

        return null;
    }

    /**
     * Finds the first available monkey in the requested country.
     */
    private Monkey findAvailableMonkey(String country) {
        for (Monkey monkey : monkeyList) {
            if (isAvailableInCountry(monkey, country)) {
                return monkey;
            }
        }

        return null;
    }

    /**
     * Reserves the first matching animal.
     *
     * This method is retained for compatibility with existing tests.
     *
     * @return true if an animal was reserved; otherwise, false
     */
    public boolean reserveFirstAvailable(
            String animalType,
            String country) {

        RescueAnimal animal =
                findFirstAvailable(animalType, country);

        if (animal == null) {
            return false;
        }

        animal.setReserved(true);
        return true;
    }

    /**
     * Applies the shared reservation requirements.
     */
    private boolean isAvailableInCountry(
            RescueAnimal animal,
            String country) {

        return animal.getInServiceCountry().equalsIgnoreCase(
                        country.trim())
                && animal.getTrainingStatus().equalsIgnoreCase(
                        "in service")
                && !animal.isReserved();
    }

    /**
     * Returns a read-only view of the dog collection.
     */
    public List<Dog> getDogs() {
        return Collections.unmodifiableList(dogList);
    }

    /**
     * Returns a read-only view of the monkey collection.
     */
    public List<Monkey> getMonkeys() {
        return Collections.unmodifiableList(monkeyList);
    }

    /**
     * Returns all trained and unreserved animals.
     */
    public List<RescueAnimal> getAvailableAnimals() {
        List<RescueAnimal> availableAnimals = new ArrayList<>();

        for (Dog dog : dogList) {
            if (isAvailableForService(dog)) {
                availableAnimals.add(dog);
            }
        }

        for (Monkey monkey : monkeyList) {
            if (isAvailableForService(monkey)) {
                availableAnimals.add(monkey);
            }
        }

        return availableAnimals;
    }

    /**
     * Checks whether an animal is trained and unreserved.
     */
    private boolean isAvailableForService(RescueAnimal animal) {
        return animal.getTrainingStatus().equalsIgnoreCase(
                        "in service")
                && !animal.isReserved();
    }

    /**
     * Checks whether a filter should include every value.
     */
    private boolean isAllOption(String value) {
        return value == null
                || value.trim().isEmpty()
                || value.trim().equalsIgnoreCase("all");
    }

    /**
     * Determines whether a name contains usable text.
     */
    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Creates a consistent key for case-insensitive name searches.
     */
    private String normalizeName(String name) {
        return name.trim().toLowerCase(Locale.ROOT);
    }
}
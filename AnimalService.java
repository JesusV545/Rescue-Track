import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages animal records and related business rules.
 *
 * Separating these responsibilities from Driver allows Driver
 * to focus on user interaction and menu navigation.
 */
public class AnimalService {

    private final List<Dog> dogList;
    private final List<Monkey> monkeyList;

    /**
     * Creates an animal service with empty collections.
     */
    public AnimalService() {
        dogList = new ArrayList<>();
        monkeyList = new ArrayList<>();
    }

    /**
     * Adds a dog if its name is not already registered.
     *
     * @return true if the dog was added; otherwise, false
     */
    public boolean addDog(Dog dog) {
        if (dog == null || containsAnimalName(dog.getName())) {
            return false;
        }

        dogList.add(dog);
        return true;
    }

    /**
     * Adds a monkey if its name is not already registered.
     *
     * @return true if the monkey was added; otherwise, false
     */
    public boolean addMonkey(Monkey monkey) {
        if (monkey == null || containsAnimalName(monkey.getName())) {
            return false;
        }

        monkeyList.add(monkey);
        return true;
    }

    /**
     * Checks both collections for a duplicate animal name.
     */
    public boolean containsAnimalName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }

        for (Monkey monkey : monkeyList) {
            if (monkey.getName().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Reserves the first matching animal that is in service
     * and currently available.
     *
     * @return true if an animal was reserved; otherwise, false
     */
    public boolean reserveFirstAvailable(
            String animalType, String country) {

        if (animalType == null || country == null) {
            return false;
        }

        if (animalType.equalsIgnoreCase("dog")) {
            return reserveDog(country);
        }

        if (animalType.equalsIgnoreCase("monkey")) {
            return reserveMonkey(country);
        }

        return false;
    }

    private boolean reserveDog(String country) {
        for (Dog dog : dogList) {
            if (isAvailableInCountry(dog, country)) {
                dog.setReserved(true);
                return true;
            }
        }

        return false;
    }

    private boolean reserveMonkey(String country) {
        for (Monkey monkey : monkeyList) {
            if (isAvailableInCountry(monkey, country)) {
                monkey.setReserved(true);
                return true;
            }
        }

        return false;
    }

    /**
     * Applies the shared reservation requirements to any animal.
     */
    private boolean isAvailableInCountry(
            RescueAnimal animal, String country) {

        return animal.getInServiceLocation().equalsIgnoreCase(
                        country.trim())
                && animal.getTrainingStatus().equalsIgnoreCase(
                        "in service")
                && !animal.getReserved();
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
     * Returns all unreserved animals that have completed training.
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

    private boolean isAvailableForService(RescueAnimal animal) {
        return animal.getTrainingStatus().equalsIgnoreCase(
                        "in service")
                && !animal.getReserved();
    }
}
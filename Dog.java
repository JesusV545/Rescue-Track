import java.time.LocalDate;

/**
 * Represents a rescue dog and its breed information.
 */
public class Dog extends RescueAnimal {

    private final String breed;

    public Dog(
            String name,
            String breed,
            String gender,
            int age,
            double weight,
            LocalDate acquisitionDate,
            String acquisitionCountry,
            String trainingStatus,
            boolean reserved,
            String inServiceCountry) {

        super(
                name,
                gender,
                age,
                weight,
                acquisitionDate,
                acquisitionCountry,
                trainingStatus,
                reserved,
                inServiceCountry);

        this.breed = requireText(breed, "Breed");
    }

    @Override
    public String getAnimalType() {
        return "Dog";
    }

    public String getBreed() {
        return breed;
    }
}
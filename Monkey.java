import java.time.LocalDate;

/**
 * Represents a rescue monkey and its physical measurements.
 */
public class Monkey extends RescueAnimal {

    private static final String[] VALID_SPECIES = {
        "Capuchin",
        "Guenon",
        "Macaque",
        "Marmoset",
        "Squirrel monkey",
        "Tamarin"
    };

    private final double tailLength;
    private final double height;
    private final double bodyLength;
    private final String species;

    public Monkey(
            String name,
            String gender,
            int age,
            double weight,
            LocalDate acquisitionDate,
            String acquisitionCountry,
            String trainingStatus,
            boolean reserved,
            String inServiceCountry,
            double tailLength,
            double height,
            double bodyLength,
            String species) {

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

        this.tailLength = requirePositive(
                tailLength, "Tail length");

        this.height = requirePositive(
                height, "Height");

        this.bodyLength = requirePositive(
                bodyLength, "Body length");

        this.species = validateSpecies(species);
    }

    private static double requirePositive(
            double value, String fieldName) {

        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException(
                    fieldName + " must be greater than zero.");
        }

        return value;
    }

    private static String validateSpecies(String species) {
        if (species != null) {
            for (String validSpecies : VALID_SPECIES) {
                if (validSpecies.equalsIgnoreCase(
                        species.trim())) {

                    return validSpecies;
                }
            }
        }

        throw new IllegalArgumentException(
                "Unsupported monkey species.");
    }

    @Override
    public String getAnimalType() {
        return "Monkey";
    }

    public double getTailLength() {
        return tailLength;
    }

    public double getHeight() {
        return height;
    }

    public double getBodyLength() {
        return bodyLength;
    }

    public String getSpecies() {
        return species;
    }
}
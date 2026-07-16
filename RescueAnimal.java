import java.time.LocalDate;
import java.util.Objects;

/**
 * Defines the shared information and behavior for rescue animals.
 */
public abstract class RescueAnimal {

    private final String name;
    private final String gender;
    private final int age;
    private final double weight;
    private final LocalDate acquisitionDate;
    private final String acquisitionCountry;

    private String trainingStatus;
    private boolean reserved;
    private String inServiceCountry;

    /**
     * Creates a rescue animal after validating its shared fields.
     */
    protected RescueAnimal(
            String name,
            String gender,
            int age,
            double weight,
            LocalDate acquisitionDate,
            String acquisitionCountry,
            String trainingStatus,
            boolean reserved,
            String inServiceCountry) {

        this.name = requireText(name, "Name");
        this.gender = requireText(gender, "Gender");

        if (age < 0) {
            throw new IllegalArgumentException(
                    "Age cannot be negative.");
        }

        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException(
                    "Weight must be greater than zero.");
        }

        this.age = age;
        this.weight = weight;

        this.acquisitionDate = Objects.requireNonNull(
                acquisitionDate,
                "Acquisition date is required.");

        this.acquisitionCountry = requireText(
                acquisitionCountry,
                "Acquisition country");

        this.trainingStatus = requireText(
                trainingStatus,
                "Training status");

        this.reserved = reserved;

        this.inServiceCountry = requireText(
                inServiceCountry,
                "In-service country");
    }

    /**
     * Validates required text before storing it.
     */
    protected static String requireText(
            String value, String fieldName) {

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    fieldName + " is required.");
        }

        return value.trim();
    }

    public abstract String getAnimalType();

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public String getAcquisitionCountry() {
        return acquisitionCountry;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = requireText(
                trainingStatus,
                "Training status");
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getInServiceCountry() {
        return inServiceCountry;
    }

    public void setInServiceCountry(String inServiceCountry) {
        this.inServiceCountry = requireText(
                inServiceCountry,
                "In-service country");
    }
}
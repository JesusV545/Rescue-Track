import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Tests saving, retrieving, and updating animal records.
 */
public class AnimalRepositoryTest {

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        AnimalRepository repository =
                new AnimalRepository(databaseManager);

        try {
            databaseManager.initializeDatabase();

            Dog dog = new Dog(
                    "DatabaseDog",
                    "German Shepherd",
                    "Male",
                    4,
                    72.5,
                    LocalDate.of(2026, 7, 28),
                    "United States",
                    "in service",
                    false,
                    "United States");

            Monkey monkey = new Monkey(
                    "DatabaseMonkey",
                    "Female",
                    3,
                    18.0,
                    LocalDate.of(2026, 7, 28),
                    "Brazil",
                    "Phase IV",
                    false,
                    "United States",
                    12.5,
                    19.0,
                    16.5,
                    "Capuchin");

            repository.saveAnimal(dog);
            repository.saveAnimal(monkey);

            System.out.println("Animal records saved.");

            List<RescueAnimal> animals =
                    repository.getAllAnimals();

            System.out.println(
                    "Records retrieved: " + animals.size());

            for (RescueAnimal animal : animals) {
                System.out.println(
                        animal.getAnimalType()
                        + ": " + animal.getName()
                        + ", reserved: "
                        + animal.isReserved());
            }

            boolean updated = repository.updateReservation(
                    "DatabaseDog", true);

            System.out.println(
                    "Reservation updated: " + updated);

            List<RescueAnimal> updatedAnimals =
                    repository.getAllAnimals();

            for (RescueAnimal animal : updatedAnimals) {
                if ("DatabaseDog".equalsIgnoreCase(
                        animal.getName())) {

                    System.out.println(
                            "DatabaseDog reserved after update: "
                            + animal.isReserved());
                }
            }
        } catch (SQLException | IllegalArgumentException exception) {
            System.out.println(
                    "Repository test failed: "
                    + exception.getMessage());
        }
    }
}
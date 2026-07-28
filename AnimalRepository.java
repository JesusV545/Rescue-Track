import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.sqlite.SQLiteErrorCode;
import org.sqlite.SQLiteException;

/**
 * Performs database operations for Rescue-Track animal records.
 */
public class AnimalRepository {

    private final DatabaseManager databaseManager;

    public AnimalRepository(DatabaseManager databaseManager) {
        if (databaseManager == null) {
            throw new IllegalArgumentException(
                    "Database manager is required.");
        }

        this.databaseManager = databaseManager;
    }

    /**
     * Saves a dog or monkey in the database.
     *
     * @param animal animal to save
     * @throws SQLException if the record cannot be saved
     */
    public void saveAnimal(RescueAnimal animal)
            throws SQLException {

        if (animal == null) {
            throw new IllegalArgumentException(
                    "Animal is required.");
        }

        String insertSql =
                "INSERT INTO animal_records ("
                + "name, animal_type, gender, age, weight, "
                + "acquisition_date, acquisition_country, "
                + "training_status, reserved, "
                + "in_service_country, breed, species, "
                + "tail_length, height, body_length"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?)";

        try (Connection connection =
                     databaseManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(insertSql)) {

            statement.setString(1, animal.getName());
            statement.setString(2, animal.getAnimalType());
            statement.setString(3, animal.getGender());
            statement.setInt(4, animal.getAge());
            statement.setDouble(5, animal.getWeight());
            statement.setString(
                    6, animal.getAcquisitionDate().toString());
            statement.setString(
                    7, animal.getAcquisitionCountry());
            statement.setString(
                    8, animal.getTrainingStatus());
            statement.setInt(
                    9, animal.isReserved() ? 1 : 0);
            statement.setString(
                    10, animal.getInServiceCountry());

            setAnimalSpecificFields(statement, animal);
            statement.executeUpdate();
            
        } catch (SQLiteException exception) {
                if (exception.getResultCode()
                        == SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE) {

                    throw new SQLException(
                            "An animal named "
                            + animal.getName()
                            + " already exists.",
                            exception);
                }

                throw exception;
            }
    }

    /**
     * Sets fields that belong specifically to a dog or monkey.
     */
    private void setAnimalSpecificFields(
            PreparedStatement statement,
            RescueAnimal animal) throws SQLException {

        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;

            statement.setString(11, dog.getBreed());
            statement.setNull(12, Types.VARCHAR);
            statement.setNull(13, Types.REAL);
            statement.setNull(14, Types.REAL);
            statement.setNull(15, Types.REAL);
        } else if (animal instanceof Monkey) {
            Monkey monkey = (Monkey) animal;

            statement.setNull(11, Types.VARCHAR);
            statement.setString(12, monkey.getSpecies());
            statement.setDouble(
                    13, monkey.getTailLength());
            statement.setDouble(14, monkey.getHeight());
            statement.setDouble(
                    15, monkey.getBodyLength());
        } else {
            throw new IllegalArgumentException(
                    "Unsupported animal type.");
        }
    }

    /**
     * Retrieves every animal currently stored in the database.
     *
     * @return list containing all saved animals
     * @throws SQLException if records cannot be retrieved
     */
    public List<RescueAnimal> getAllAnimals()
            throws SQLException {

        List<RescueAnimal> animals = new ArrayList<>();

        String selectSql =
                "SELECT * FROM animal_records ORDER BY id";

        try (Connection connection =
                     databaseManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(selectSql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {
                animals.add(createAnimal(resultSet));
            }
        }

        return animals;
    }

    /**
     * Converts one database row into a Dog or Monkey object.
     */
    private RescueAnimal createAnimal(ResultSet resultSet)
            throws SQLException {

        String animalType =
                resultSet.getString("animal_type");

        try {
            LocalDate acquisitionDate = LocalDate.parse(
                    resultSet.getString("acquisition_date"));

            if ("Dog".equalsIgnoreCase(animalType)) {
                return new Dog(
                        resultSet.getString("name"),
                        resultSet.getString("breed"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("weight"),
                        acquisitionDate,
                        resultSet.getString(
                                "acquisition_country"),
                        resultSet.getString(
                                "training_status"),
                        resultSet.getInt("reserved") == 1,
                        resultSet.getString(
                                "in_service_country"));
            }

            if ("Monkey".equalsIgnoreCase(animalType)) {
                return new Monkey(
                        resultSet.getString("name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("weight"),
                        acquisitionDate,
                        resultSet.getString(
                                "acquisition_country"),
                        resultSet.getString(
                                "training_status"),
                        resultSet.getInt("reserved") == 1,
                        resultSet.getString(
                                "in_service_country"),
                        resultSet.getDouble("tail_length"),
                        resultSet.getDouble("height"),
                        resultSet.getDouble("body_length"),
                        resultSet.getString("species"));
            }

            throw new SQLException(
                    "Unsupported animal type in database: "
                    + animalType);
        } catch (IllegalArgumentException
                | DateTimeParseException exception) {

            throw new SQLException(
                    "Invalid animal record in database.",
                    exception);
        }
    }

    /**
     * Updates the reservation status of a named animal.
     *
     * @return true if a record was updated
     * @throws SQLException if the update cannot be completed
     */
    public boolean updateReservation(
            String name,
            boolean reserved) throws SQLException {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Animal name is required.");
        }

        String updateSql =
                "UPDATE animal_records "
                + "SET reserved = ? "
                + "WHERE LOWER(name) = LOWER(?)";

        try (Connection connection =
                     databaseManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(updateSql)) {

            statement.setInt(1, reserved ? 1 : 0);
            statement.setString(2, name.trim());

            return statement.executeUpdate() > 0;
        }
    }
}
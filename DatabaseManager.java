import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manages the SQLite database connection and initializes
 * the database structure used by Rescue-Track.
 */
public class DatabaseManager {

    private static final String DATABASE_URL =
            "jdbc:sqlite:rescue_track.db";

    /**
     * Opens and returns a connection to the Rescue-Track database.
     *
     * @return an active SQLite database connection
     * @throws SQLException if the connection cannot be opened
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    /**
     * Creates the animal_records table if it does not already exist.
     *
     * @throws SQLException if the table cannot be created
     */
    public void initializeDatabase() throws SQLException {
        String createTableSql =
                "CREATE TABLE IF NOT EXISTS animal_records ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL UNIQUE COLLATE NOCASE, "
                + "animal_type TEXT NOT NULL "
                + "CHECK (animal_type IN ('Dog', 'Monkey')), "
                + "gender TEXT NOT NULL, "
                + "age INTEGER NOT NULL CHECK (age >= 0), "
                + "weight REAL NOT NULL CHECK (weight >= 0), "
                + "acquisition_date TEXT NOT NULL, "
                + "acquisition_country TEXT NOT NULL, "
                + "training_status TEXT NOT NULL, "
                + "reserved INTEGER NOT NULL DEFAULT 0 "
                + "CHECK (reserved IN (0, 1)), "
                + "in_service_country TEXT NOT NULL, "
                + "breed TEXT, "
                + "species TEXT, "
                + "tail_length REAL, "
                + "height REAL, "
                + "body_length REAL"
                + ");";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(createTableSql);
        }
    }
}
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();

        try {
            databaseManager.initializeDatabase();

            try (Connection connection = databaseManager.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "SELECT name FROM sqlite_master "
                         + "WHERE type = 'table' "
                         + "AND name = 'animal_records'")) {

                if (resultSet.next()) {
                    System.out.println(
                            "Database initialized successfully.");
                    System.out.println(
                            "animal_records table exists.");
                } else {
                    System.out.println(
                            "animal_records table was not found.");
                }
            }
        } catch (SQLException exception) {
            System.out.println("Database setup failed: "
                    + exception.getMessage());
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        String databaseUrl = "jdbc:sqlite:rescue_track.db";

        try (Connection connection = DriverManager.getConnection(databaseUrl)) {
            System.out.println("SQLite connection successful.");
        } catch (SQLException exception) {
            System.out.println("SQLite connection failed: "
                    + exception.getMessage());
        }
    }
}
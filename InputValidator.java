import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Provides reusable methods for safely validating console input.
 */
public final class InputValidator {

    // Prevents this utility class from being instantiated.
    private InputValidator() {
    }

    /**
     * Reads a required text value and rejects blank input.
     */
    public static String readRequiredText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("A value is required. Please try again.");
        }
    }

    /**
     * Reads a whole number that is zero or greater.
     */
    public static int readNonNegativeInt(Scanner scanner, String prompt) {
        while (true) {
            String value = readRequiredText(scanner, prompt);

            try {
                int number = Integer.parseInt(value);

                if (number >= 0) {
                    return number;
                }
            } catch (NumberFormatException exception) {
                // The validation message below handles the error.
            }

            System.out.println(
                    "Enter a whole number that is zero or greater.");
        }
    }

    /**
     * Reads a decimal number that is greater than zero.
     */
    public static double readPositiveDouble(
            Scanner scanner, String prompt) {

        while (true) {
            String value = readRequiredText(scanner, prompt);

            try {
                double number = Double.parseDouble(value);

                if (Double.isFinite(number) && number > 0) {
                    return number;
                }
            } catch (NumberFormatException exception) {
                // The validation message below handles the error.
            }

            System.out.println("Enter a number greater than zero.");
        }
    }

    /**
     * Reads and validates a date in YYYY-MM-DD format.
     */
    public static LocalDate readDate(
            Scanner scanner, String prompt) {

        while (true) {
            String value = readRequiredText(
                    scanner, prompt + " (YYYY-MM-DD): ");

            try {
                return LocalDate.parse(value);
            } catch (DateTimeParseException exception) {
                System.out.println(
                        "Enter a valid date in YYYY-MM-DD format.");
            }
        }
    }
}
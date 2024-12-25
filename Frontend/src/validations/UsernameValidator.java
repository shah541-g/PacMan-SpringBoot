package validations;

public class UsernameValidator {

    /**
     * Validates the username according to the following rules:
     * - At least 3 characters long
     * - Only contains letters, digits, and underscores (no spaces or special characters)
     *
     * @param username The username to validate
     * @return true if the username is valid, false otherwise
     */
    public static boolean validateUsername(String username) {
        if (username == null) {
            return false;
        }

        // Check that the username has at least 3 characters
        if (username.length() < 3) {
            return false;
        }

        // Check that the username matches the allowed pattern: letters, digits, and underscores
        return username.matches("^[a-zA-Z0-9_]+$");
    }


}

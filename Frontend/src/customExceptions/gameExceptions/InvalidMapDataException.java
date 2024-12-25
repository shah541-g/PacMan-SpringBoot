package customExceptions.gameExceptions;


public class InvalidMapDataException extends RuntimeException {
    public InvalidMapDataException(String message) {
        super(message);
    }
}
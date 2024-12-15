package CustomExceptions.GameExceptions;


public class InvalidMapDataException extends RuntimeException {
    public InvalidMapDataException(String message) {
        super(message);
    }
}
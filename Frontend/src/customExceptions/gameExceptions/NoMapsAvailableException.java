package customExceptions.gameExceptions;

public class NoMapsAvailableException extends RuntimeException {
    public NoMapsAvailableException(String message) {
        super(message);
    }
}
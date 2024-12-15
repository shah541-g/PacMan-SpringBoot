package CustomExceptions.GameExceptions;

public class NoMapsAvailableException extends RuntimeException {
    public NoMapsAvailableException(String message) {
        super(message);
    }
}
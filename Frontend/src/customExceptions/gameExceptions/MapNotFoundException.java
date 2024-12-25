package customExceptions.gameExceptions;

public class MapNotFoundException extends RuntimeException {
    public MapNotFoundException(String message) {
        super(message);
    }
}
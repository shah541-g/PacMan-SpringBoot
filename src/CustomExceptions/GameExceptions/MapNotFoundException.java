package CustomExceptions.GameExceptions;

public class MapNotFoundException extends RuntimeException {
    public MapNotFoundException(String message) {
        super(message);
    }
}
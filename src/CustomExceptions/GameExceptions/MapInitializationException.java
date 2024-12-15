package CustomExceptions.GameExceptions;


 public class MapInitializationException extends RuntimeException {
    public MapInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}


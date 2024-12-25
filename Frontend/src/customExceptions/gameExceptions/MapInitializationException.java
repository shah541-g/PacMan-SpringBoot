package customExceptions.gameExceptions;


 public class MapInitializationException extends RuntimeException {
    public MapInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}


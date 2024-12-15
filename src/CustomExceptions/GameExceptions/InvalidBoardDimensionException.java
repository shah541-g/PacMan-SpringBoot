package CustomExceptions.GameExceptions;

public class InvalidBoardDimensionException extends Exception {
    public InvalidBoardDimensionException(String message) {
        super(message);
    }
}

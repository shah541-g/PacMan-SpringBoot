package CustomExceptions.GameExceptions;

public class InvalidStateException extends Exception{

    public InvalidStateException(String error) {
        super(error);
    }

}

package exception;

public class InvalidRequestException extends RuntimeException{
    //'super' used to call super-class constructor
    public InvalidRequestException(String message) {
        super(message);
    }
}

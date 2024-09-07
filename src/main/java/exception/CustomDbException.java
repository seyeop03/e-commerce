package exception;

public class CustomDbException extends RuntimeException {

    public CustomDbException() {
    }

    public CustomDbException(String message) {
        super(message);
    }

    public CustomDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomDbException(Throwable cause) {
        super(cause);
    }
}

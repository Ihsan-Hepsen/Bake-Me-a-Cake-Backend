package be.kdg.prog6.thebakery.store.exceptions;

public class UnknownProductException extends IllegalStateException {

    public UnknownProductException(String message) {
        super(message);
    }

    public UnknownProductException(String message, Throwable cause) {
        super(message, cause);
    }

}

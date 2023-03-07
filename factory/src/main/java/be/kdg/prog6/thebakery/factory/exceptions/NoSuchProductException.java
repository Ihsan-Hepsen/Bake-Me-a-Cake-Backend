package be.kdg.prog6.thebakery.factory.exceptions;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(Throwable cause) {
        super(cause);
    }

    public NoSuchProductException(String message) {
        super(message);
    }
}

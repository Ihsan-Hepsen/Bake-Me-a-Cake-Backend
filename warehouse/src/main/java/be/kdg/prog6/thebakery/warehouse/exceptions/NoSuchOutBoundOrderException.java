package be.kdg.prog6.thebakery.warehouse.exceptions;

public class NoSuchOutBoundOrderException extends RuntimeException {

    public NoSuchOutBoundOrderException(String message) {
        super(message);
    }

    public NoSuchOutBoundOrderException(String message, Throwable cause) {
        super(message, cause);
    }

}

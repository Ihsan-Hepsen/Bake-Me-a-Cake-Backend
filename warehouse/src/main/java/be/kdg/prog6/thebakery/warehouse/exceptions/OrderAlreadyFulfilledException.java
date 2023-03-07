package be.kdg.prog6.thebakery.warehouse.exceptions;

public class OrderAlreadyFulfilledException extends RuntimeException {

    public OrderAlreadyFulfilledException(String message) {
        super(message);
    }

    public OrderAlreadyFulfilledException(String message, Throwable cause) {
        super(message, cause);
    }

}

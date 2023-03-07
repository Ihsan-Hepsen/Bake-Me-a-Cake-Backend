package be.kdg.prog6.thebakery.warehouse.exceptions;

public class StockItemCouldNotBeFoundException extends RuntimeException {

    public StockItemCouldNotBeFoundException(String message) {
        super(message);
    }

    public StockItemCouldNotBeFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

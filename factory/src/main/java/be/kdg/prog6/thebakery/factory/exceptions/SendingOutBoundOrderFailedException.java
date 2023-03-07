package be.kdg.prog6.thebakery.factory.exceptions;

public class SendingOutBoundOrderFailedException extends RuntimeException {

    public SendingOutBoundOrderFailedException(Throwable cause) {
        super(cause);
    }

    public SendingOutBoundOrderFailedException(String message) {
        super(message);
    }

}

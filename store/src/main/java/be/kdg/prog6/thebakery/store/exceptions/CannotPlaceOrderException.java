package be.kdg.prog6.thebakery.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Can't process request body.")
public class CannotPlaceOrderException extends RuntimeException {

    public CannotPlaceOrderException(String message) {
        super(message);
    }

    public CannotPlaceOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}

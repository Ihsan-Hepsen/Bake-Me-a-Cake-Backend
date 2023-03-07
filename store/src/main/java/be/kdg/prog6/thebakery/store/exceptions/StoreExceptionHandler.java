package be.kdg.prog6.thebakery.store.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class StoreExceptionHandler {

    @ExceptionHandler({ UnknownProductException.class, CannotPlaceOrderException.class })
    public final ResponseEntity<Void> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof UnknownProductException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            UnknownProductException upe = (UnknownProductException) ex;

            return handleUnKnownProductException(upe, headers, status, request);
        } else if (ex instanceof CannotPlaceOrderException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CannotPlaceOrderException cpoe = (CannotPlaceOrderException) ex;

            return handleCannotPlaceOrderException(cpoe, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, headers, status, request);
        }
    }

    protected ResponseEntity<Void> handleUnKnownProductException(UnknownProductException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, headers, status, request);
    }

    protected ResponseEntity<Void> handleCannotPlaceOrderException(CannotPlaceOrderException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, headers, status, request);
    }

    protected ResponseEntity<Void> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(headers, status);
    }
}

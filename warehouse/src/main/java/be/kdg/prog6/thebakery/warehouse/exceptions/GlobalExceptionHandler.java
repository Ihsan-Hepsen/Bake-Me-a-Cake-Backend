package be.kdg.prog6.thebakery.warehouse.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ NoSuchOutBoundOrderException.class, StockItemCouldNotBeFoundException.class })
    public final ResponseEntity<Void> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof NoSuchOutBoundOrderException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NoSuchOutBoundOrderException nsoboe = (NoSuchOutBoundOrderException) ex;

            return handleNoSuchOutBoundOrder(nsoboe, headers, status, request);
        } else if (ex instanceof StockItemCouldNotBeFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            StockItemCouldNotBeFoundException sinfe = (StockItemCouldNotBeFoundException) ex;

            return handleStockItemNotFound(sinfe, headers, status, request);
        }else if (ex instanceof OrderAlreadyFulfilledException) {
            HttpStatus status = HttpStatus.CONFLICT;
            OrderAlreadyFulfilledException oafe = (OrderAlreadyFulfilledException) ex;

            return handleOrderAlreadyFulfilled(oafe, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, headers, status, request);
        }
    }

    protected ResponseEntity<Void> handleNoSuchOutBoundOrder(NoSuchOutBoundOrderException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, headers, status, request);
    }

    protected ResponseEntity<Void> handleStockItemNotFound(StockItemCouldNotBeFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, headers, status, request);
    }

    protected ResponseEntity<Void> handleOrderAlreadyFulfilled(OrderAlreadyFulfilledException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, headers, status, request);
    }

    protected ResponseEntity<Void> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(headers, status);
    }
}


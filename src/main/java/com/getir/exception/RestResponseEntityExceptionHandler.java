package com.getir.exception;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    static Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);


    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> handleThrowable(
            Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("generalError", ex.getMessage());
        LOGGER.error("error= ", ex);
        return new ResponseEntity<>(
                errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotEnoughBooksException.class})
    public ResponseEntity<Object> handleNotEnoughBooksException(
            NotEnoughBooksException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("NotEnoughBooksException", ex.getMessage());
        LOGGER.error("error= ", ex);
        return new ResponseEntity<>(
                errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({StaleObjectStateException.class})
    public ResponseEntity<Object> handleStaleObjectStateException(
            StaleObjectStateException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("StaleObjectStateException", ex.getMessage());
        LOGGER.error("error= ", ex);
        return new ResponseEntity<>(
                errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

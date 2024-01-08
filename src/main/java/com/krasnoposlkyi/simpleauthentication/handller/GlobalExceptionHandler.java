package com.krasnoposlkyi.simpleauthentication.handller;

import com.krasnoposlkyi.simpleauthentication.exception.TableNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        log.error("Unknown error occurred", exception);
        return ResponseEntity.internalServerError().body("Sorry something went wrong");
    }

    @ExceptionHandler(TableNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleProhibitionMovingException(
            TableNotFoundException exception) {
        log.warn("Table does not exist ", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table does not exist");
    }


}

package com.devlife.pf_sql_controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    public static final int FILED_NAME_INDEX = 1;

    @ExceptionHandler(value = {RequestApiException.class})
    ResponseEntity<Object> handleApiException(RequestApiException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiExceptionResponse, badRequest);
    }

    @ExceptionHandler(value = {BusinessLogicException.class})
    ResponseEntity<Object> handleBusinessLogicException(BusinessLogicException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiExceptionResponse, internalServerError);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                e.getBindingResult().getAllErrors().stream()
                        .filter(err -> err != null && err.getCodes() != null)
                        .filter(err -> err.getCodes().length > 1)
                        .map(error -> error.getCodes()[FILED_NAME_INDEX] + " : " + error.getDefaultMessage())
                        .collect(Collectors.joining(", ")),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiExceptionResponse, badRequest);
    }
}

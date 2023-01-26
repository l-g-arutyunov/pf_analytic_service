package com.devlife.pf_sql_controller.exception;

public class RequestApiException extends RuntimeException{
    public RequestApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestApiException(String message) {
        super(message);
    }
}

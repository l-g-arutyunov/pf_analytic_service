package com.devlife.pf_sql_controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class ApiExceptionResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;
}

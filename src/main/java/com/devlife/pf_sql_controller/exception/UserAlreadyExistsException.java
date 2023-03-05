package com.devlife.pf_sql_controller.exception;

public class UserAlreadyExistsException extends RequestApiException {
    public UserAlreadyExistsException(Long externalId) {
        super("User with externalId [" + externalId + "] already exists");
    }
}

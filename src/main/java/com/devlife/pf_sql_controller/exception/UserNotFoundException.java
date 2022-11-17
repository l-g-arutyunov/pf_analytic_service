package com.devlife.pf_sql_controller.exception;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException(String... s) {
        super("User not found \n " + s.toString());
    }
}

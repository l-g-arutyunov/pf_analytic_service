package com.devlife.pf_sql_controller.exception;

import java.util.Arrays;

public class UserNotFoundException extends RequestApiException {
    public UserNotFoundException(String... s) {
        super("User not found \n " + Arrays.toString(s));
    }
}

package com.devlife.pf_sql_controller.exception;

import java.util.Arrays;

public class EmployerNotFoundException extends RequestApiException {
    public EmployerNotFoundException(String... s) {
        super("Employer not found \n " + Arrays.toString(s));
    }
}
